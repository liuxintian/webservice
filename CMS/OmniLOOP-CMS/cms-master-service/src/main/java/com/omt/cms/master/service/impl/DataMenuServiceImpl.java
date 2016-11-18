/**
 * 
 */
package com.omt.cms.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.DataMenu;
import com.omt.cms.master.repo.op.DataMenuOperations;
import com.omt.cms.master.service.DataMenuService;
import com.omt.cms.master.service.bo.DataMenuBO;
import com.omt.cms.master.service.bo.mapper.DataMenuMapper;


/**
 * @author muragesh
 *
 */
@Service
public class DataMenuServiceImpl implements DataMenuService {

	@Autowired private DataMenuOperations dmOp;
	@Autowired private DataMenuMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DataMenuBO dmRslt = null;
		DataMenuBO dm = request.getRequestData(DataMenuBO.class);
		try {
			if (dm != null && dm.getId() != null) {
				DataMenu dmEnt = dmOp.findById(dm.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(dmEnt!=null){
					dmRslt = mapper.fromEntityToBO(dmEnt);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}

		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(dmRslt);
		return response;
	}
	@Override
	public ServiceResponse readByDataMenu(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DataMenuBO dmRslt = null;
		DataMenuBO dm = request.getRequestData(DataMenuBO.class);
		try {
			if (dm != null && dm.getDataMenu() != null) {
				DataMenu dmEnt = dmOp.findByDataMenu(dm.getDataMenu());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(dmEnt!=null){
					dmRslt = mapper.fromEntityToBO(dmEnt);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}

		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(dmRslt);
		return response;
	}




	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DataMenuBO dm = request.getRequestData(DataMenuBO.class);
		DataMenuBO dmRslt = null;
		try {
			if(dm!=null){
				DataMenu dme=dmOp.findByDataMenu(dm.getDataMenu()); 
				if(dme==null){
					dm.setDefaultValidUntil();
					DataMenu dmEnt = mapper.createNewEntityFromBO(dm, null);
					dmEnt.setStatus(RecordStatus.ACTIVE.getValue());
					dmEnt = dmOp.add(dmEnt);
					dmRslt = mapper.fromEntityToBO(dmEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(dmRslt);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DataMenuBO dm = request.getRequestData(DataMenuBO.class);
		DataMenuBO dmRslt = null;
		try {
			if (dm != null && dm.getId() != null) {
				DataMenu dmEnt = dmOp.findById(dm.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(dmEnt!=null){
					mapper.copyUpdatedBOToEntity(dm, dmEnt, null);
					dmEnt = dmOp.update(dmEnt);
					dmRslt = mapper.fromEntityToBO(dmEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(dmRslt);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		DataMenuBO dmRslt = null;
		DataMenuBO dm = request.getRequestData(DataMenuBO.class);
		try {
			if (dm != null && dm.getId() != null) {
				DataMenu dmEnt = dmOp.findById(dm.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(dmEnt!=null){
					dmEnt.setStatus(RecordStatus.DELETED.getValue());
					dmEnt = dmOp.update(dmEnt);
					dmRslt = mapper.fromEntityToBO(dmEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(dmRslt);
		return response;
	}

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<DataMenuBO> dmBOList = new ArrayList<DataMenuBO>();
		try {			
			List<DataMenu> dmList = dmOp.findAll();
			for (DataMenu dmEnt : dmList) {
				if(dmEnt.isActive()){
					DataMenuBO dmBO = mapper.fromEntityToBO(dmEnt);
					dmBOList.add(dmBO);
				}
			}
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(dmBOList);
		return response;
	}	




}
