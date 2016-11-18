/**
 * 
 */
package com.omt.cms.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.AsxRegistry;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.repo.op.CompanyOperations;
import com.omt.cms.master.service.CompanyService;
import com.omt.cms.master.service.bo.CompanyBO;
import com.omt.cms.master.service.bo.mapper.CompanyMapper;
	

/**
 * @author muragesh
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired private CompanyOperations cpOp;
	@Autowired private CompanyMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO cpRslt = null;
		CompanyBO cp = request.getRequestData(CompanyBO.class);
		try {
			if (cp != null && cp.getId() != null) {
				Company cpEnt = cpOp.findById(cp.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cpEnt!=null){
					cpRslt = mapper.fromEntityToBO(cpEnt);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}

		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpRslt);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO cp = request.getRequestData(CompanyBO.class);
		CompanyBO cpRslt = null;
		try {
			if(cp!=null){
				cp.setDefaultValidUntil();
				Company cpEnt = mapper.createNewEntityFromBO(cp, null);
				cpEnt.setStatus(RecordStatus.ACTIVE.getValue());
				cpEnt = cpOp.add(cpEnt);
				cpRslt = mapper.fromEntityToBO(cpEnt);
				result = true;
				resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpRslt);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO cp = request.getRequestData(CompanyBO.class);
		CompanyBO cpRslt = null;
		try {
			if (cp != null && cp.getId() != null) {
				Company cpEnt = cpOp.findById(cp.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cpEnt!=null){
					mapper.copyUpdatedBOToEntity(cp, cpEnt, null);
					cpEnt = cpOp.update(cpEnt);
					cpRslt = mapper.fromEntityToBO(cpEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpRslt);
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO cpRslt = null;
		CompanyBO cp = request.getRequestData(CompanyBO.class);
		try {
			if (cp != null && cp.getId() != null) {
				Company cpEnt = cpOp.findById(cp.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cpEnt!=null){
					cpEnt.setStatus(RecordStatus.DELETED.getValue());
					cpEnt = cpOp.update(cpEnt);
					cpRslt = mapper.fromEntityToBO(cpEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpRslt);
		return response;
	}

	@Override
	public ServiceResponse readAll(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<CompanyBO> cpBOList = new ArrayList<CompanyBO>();
		try {			
			List<Company> cpList = cpOp.findByStatus(RecordStatus.ACTIVE.getValue());
			for (Company cpEnt : cpList) {
				CompanyBO cpBO = mapper.fromEntityToBO(cpEnt);
				cpBOList.add(cpBO);
			}
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpBOList);
		return response;
	}	
	
	
	@Override
	public ServiceResponse readAllAdmin(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<CompanyBO> cpBOList = new ArrayList<CompanyBO>();
		try {
			List<Company> cpList = cpOp.findByFiltersAdmins(reqBO);
			Long total = cpOp.countByFiltersAdmins(reqBO);
			convertToBOList(cpBOList, cpList, total);
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpBOList);
		return response;
	}

	@Override
	public ServiceResponse push(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO cpRslt = null;
		Long cpId = request.getRequestData(Long.class);
		try {
			if (cpId!=null) {
				Company cpEnt = cpOp.findById(cpId);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cpEnt!=null && cpEnt.isActive()){
					
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpRslt);
		return response;
	}

	@Override
	public ServiceResponse readByASXCode(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyBO cpRslt = null;
		String asxCode = request.getRequestData(String.class);
		try {
			if (StringUtils.isNotBlank(asxCode)) {
				Company cpEnt = cpOp.findByCompanyTicker(asxCode.toLowerCase());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cpEnt!=null){
					cpRslt = mapper.fromEntityToBO(cpEnt);
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpRslt);
		return response;
	}

	@Override
	public ServiceResponse tickerNameExists(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String asxCode = request.getRequestData(String.class);
		try {
			if (StringUtils.isNotBlank(asxCode)) {
				result = cpOp.tickerNameExists(asxCode);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(result){
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		return response;
	}
	
	
	@Override
	public ServiceResponse readByCompanyType(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		String type = request.getRequestData(String.class);
		List<CompanyBO> cpBOList = new ArrayList<CompanyBO>(1);
		try {
			List<Company> cpList = null;
			int stsActive = RecordStatus.ACTIVE.getValue();
			if(StringUtils.isNotBlank(type)){
				cpList = cpOp.findByStatusAndCompanyType(stsActive, type);	
			}else{
				cpList = cpOp.findByStatus(stsActive);
			}
			for (Company cpEnt : cpList) {
				CompanyBO cpBO = mapper.fromEntityToBO(cpEnt);
				cpBOList.add(cpBO);
			}
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cpBOList);
		return response;
	}
	
	@Override
	public ServiceResponse listAsxRegistry(ServiceRequest request) {
		boolean result = true;
		String resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
		List<AsxRegistry> asxLst = new ArrayList<AsxRegistry>(1);
		try {
			asxLst = cpOp.getAsxRegList();
		} catch (Exception e) {
			result = false;
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(asxLst);
		return response;
	}
	
	
	private void convertToBOList(List<CompanyBO> compBOList, List<Company> compList) {
		for (Company compEnt : compList) {
			CompanyBO compBO = mapper.fromEntityToBO(compEnt);
			compBOList.add(compBO);
		}
	}
	
	
	private void convertToBOList(List<CompanyBO> compBOList, List<Company> compList, Long total) {
		boolean addTotal = true;
		for (Company compEnt : compList) {
			CompanyBO compBO = mapper.fromEntityToBO(compEnt);
			if(addTotal){
				compBO.setTotal(total);
				addTotal = false;
			}
			compBOList.add(compBO);
		}
	}

	
	@Override
	public ServiceResponse readAllWithFilters(ServiceRequest request){
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		FilterCriteriaBO reqBO = request.getRequestData(FilterCriteriaBO.class);
		List<CompanyBO> respList = new ArrayList<>(1);
		try{
			if (reqBO!=null) {
				List<Company> resList = cpOp.findByFilters(reqBO);
				convertToBOList(respList, resList);
				result = true;
				resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
			}
		}catch (Exception e) {
			LOGGER.error("Error occurred: {}", e);
			resultMsg = "Error details:" + e.getMessage();
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(respList);
		return response;
	}
	
}
