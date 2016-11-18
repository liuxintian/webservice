package com.omt.cms.master.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.IRMenu;
import com.omt.cms.master.entity.IRMenuItem;
import com.omt.cms.master.repo.op.IRMenuItemOperations;
import com.omt.cms.master.repo.op.IRMenuOperations;
import com.omt.cms.master.service.IRMenuService;
import com.omt.cms.master.service.bo.IRMenuBO;
import com.omt.cms.master.service.bo.IRMenuItemBO;

@Service
public class IRMenuServiceImpl implements IRMenuService {
	
	@Autowired private IRMenuOperations irmOps;
	@Autowired private IRMenuItemOperations irmiOps;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		IRMenuBO resultIRMenus = new IRMenuBO();
		Long companyId = request.getRequestData(Long.class);
		if (companyId != null) {
			List<IRMenu> menus = irmOps.findByCompanyId(companyId);
			List<IRMenuItem> irmenus = irmiOps.getActiveMenuItems();
			List<Long> activeGlobalMenuIds = new ArrayList<>();
			for(IRMenuItem irm : irmenus){
				if(irm.isActive()){
					activeGlobalMenuIds.add(irm.getId());
				}
			}

			resultIRMenus.setCompanyId(companyId);
			for(IRMenu menu : menus){
				if(menu.isActive() && activeGlobalMenuIds.contains(menu.getMenuId()) ){
					resultIRMenus.addMenuItem(menu.getMenuId());	
				}
			}
			setGlobalMenuItems(resultIRMenus);
			result = true;
			resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultIRMenus);
		return response;
	}

	private void setGlobalMenuItems(IRMenuBO resultIRMenus) {
		List<IRMenuItem> irmenus = irmiOps.getActiveMenuItems();
		List<IRMenuItemBO> irmenuBOs = new ArrayList<>();
		for(IRMenuItem irm : irmenus){
			IRMenuItemBO mbo = new IRMenuItemBO();
			BeanUtils.copyProperties(irm, mbo);
			irmenuBOs.add(mbo);
		}
		resultIRMenus.setMenuItems(irmenuBOs);
	}

	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		
		IRMenuBO resultIRMenu = null;
		IRMenuBO reqIRMenu = request.getRequestData(IRMenuBO.class);
		
		if (reqIRMenu != null) {
			Long companyId = reqIRMenu.getCompanyId();
			List<Long> menuIds = reqIRMenu.getMenuIds();
			if(menuIds!=null && !menuIds.isEmpty()){
				List<IRMenu> curMenus = irmOps.findByCompanyId(companyId);
				
				List<Long> curMenusIds = new ArrayList<>();
				for(IRMenu menu:curMenus){
					curMenusIds.add(menu.getMenuId());
				}
				
				List<Long> newIds = new ArrayList<>();
				List<Long> curIds = new ArrayList<>();
				List<Long> delIds = new ArrayList<>();
				
				for(Long menuId : menuIds){
					if(curMenusIds.contains(menuId)){
						curIds.add(menuId);
					}else{
						newIds.add(menuId);
					}
				}
				
				for(Long cmId : curMenusIds){
					if(!menuIds.contains(cmId)){
						delIds.add(cmId);
					}
				}

				for(IRMenu menu:curMenus){
					if(curIds.contains(menu.getMenuId())){
						if(!menu.isActive()){
							menu.setStatus(RecordStatus.ACTIVE.getValue());
							menu.setLastUpdated(new Timestamp(System.currentTimeMillis()));
							irmOps.update(menu);
						}
					}else if(delIds.contains(menu.getMenuId())){
						if(menu.isActive()){
							menu.setStatus(RecordStatus.INACTIVE.getValue());
							menu.setLastUpdated(new Timestamp(System.currentTimeMillis()));
							irmOps.update(menu);
						}
					}
				}
				
				for(Long nwmId : newIds){
					IRMenu nwMenu = new IRMenu(companyId, nwmId);
					nwMenu.setStatus(RecordStatus.ACTIVE.getValue());
					irmOps.add(nwMenu);
				}

				resultIRMenu = new IRMenuBO(companyId);
				resultIRMenu.addMenuItems(curIds);
				resultIRMenu.addMenuItems(newIds);
				setGlobalMenuItems(resultIRMenu);

				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();

			}else if((menuIds!=null && menuIds.isEmpty()) || menuIds==null){
				List<IRMenu> curMenus = irmOps.findByCompanyId(companyId);
				for(IRMenu menu:curMenus){
					if(menu.isActive()){
						menu.setStatus(RecordStatus.INACTIVE.getValue());
						menu.setLastUpdated(new Timestamp(System.currentTimeMillis()));
						irmOps.update(menu);
					}
				}				
				resultIRMenu = new IRMenuBO(companyId);
				setGlobalMenuItems(resultIRMenu);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		}
		
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(resultIRMenu);
		return response;
	}

	public ServiceResponse add(ServiceRequest request) {
		return operationNotSupported();
	}

	public ServiceResponse delete(ServiceRequest request) {
		return operationNotSupported();
	}

	private ServiceResponse operationNotSupported() {
		ServiceResponse response = new ServiceResponse(false, ServiceResultCodes.OPERATION_NOT_SUPPORTED.getValue());
		return response;
	}
}