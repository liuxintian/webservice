package com.omt.cms.company.instance.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.omt.cms.company.instance.service.AllInstancesDataProxyService;
import com.omt.cms.company.instance.service.common.BaseInstanceProxyService;
import com.omt.cms.core.common.SystemCodes.ApiResource;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.AnnouncementBO;
import com.omt.cms.core.service.bo.base.KeyDateBO;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.op.CompanyOperations;

/**
 * 
 * @author Shiva Kalgudi
 *
 */

@Service
public class AllInstancesDataProxyServiceImpl extends BaseInstanceProxyService implements AllInstancesDataProxyService {
	@Value("${cms.instances.mobile.api.url}") private String mobAPIUrl;
	@Autowired private CompanyOperations cpOps;

	@Override
	public ServiceResponse readAllInstancesAnnouncements(ServiceRequest request) {
		String resURI = "/" + ApiResource.ANNOUNCEMENTS.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		Set<String> cpTypes=getCpTypes(reqBO.companyType);

		if(reqBO!=null){
			resURI = addFilterParams(resURI, reqBO);
		}

		ServiceResponse response = readAllActive(mobAPIUrl + resURI, AnnouncementBO[].class);
		if(response.getServiceResult().isResult()){
			List<AnnouncementBO> rbos=new ArrayList<>();
			AnnouncementBO[] bos = (AnnouncementBO[]) response.getResponseData();
			List<Long> cpIds = new ArrayList<>();
 			if(bos!=null && bos.length > 0){
				for(AnnouncementBO bo : bos){
					if(!cpIds.contains(bo.getCompanyId())){
						cpIds.add(bo.getCompanyId());
					}
				}
				List<Company> companies = cpOps.getCompanyDetails(cpIds);
				for(AnnouncementBO bo : bos){
					for(Company cp:companies){
						if(cp.getId().longValue() == bo.getCompanyId().longValue() && cpTypes.contains(cp.getCompanyType())){
							bo.setCompanyName(cp.getCompanyName());
							bo.setCompanyTicker(cp.getCompanyTicker());
							rbos.add(bo);
							break;
						}
					}
				}
				response.addResponseData(rbos);
			}else{
				response.addResponseData(new ArrayList<>(1));
			}
		}
		return response;
	}

	@Override
	public ServiceResponse readAllInstancesKeyDates(ServiceRequest request) {
		String resURI = "/" + ApiResource.KEY_DATES.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		Set<String> cpTypes=getCpTypes(reqBO.companyType);
		if(reqBO!=null){
			resURI = addFilterParams(resURI, reqBO);
		}
		ServiceResponse response = readAllActive(mobAPIUrl + resURI, KeyDateBO[].class);
		if(response.getServiceResult().isResult()){
			List<KeyDateBO> rbos=new ArrayList<>();
			KeyDateBO[] bos = (KeyDateBO[]) response.getResponseData();
			List<Long> cpIds = new ArrayList<>();
			if(bos!=null && bos.length > 0){
				for(KeyDateBO bo : bos){
					if(!cpIds.contains(bo.getCompanyId())){
						cpIds.add(bo.getCompanyId());
					}
				}
				List<Company> companies = cpOps.getCompanyDetails(cpIds);
				for(KeyDateBO bo : bos){
					for(Company cp:companies){
						if(cp.getId().longValue() == bo.getCompanyId().longValue() && cpTypes.contains(cp.getCompanyType())){
							bo.setCompanyName(cp.getCompanyName());
							bo.setCompanyTicker(cp.getCompanyTicker());
							rbos.add(bo);
							break;
						}
					}
				}
				response.addResponseData(rbos);
			}else{
				response.addResponseData(new ArrayList<>(1));
			}
		}
		return response;
	}

	private Set<String> getCpTypes(String cStr){
		Set<String> cpTypes=new HashSet<>();
		String [] lst=StringUtils.split(cStr, "|");
		for(String s: lst)
			cpTypes.add(s);
		return cpTypes;
	}

}
