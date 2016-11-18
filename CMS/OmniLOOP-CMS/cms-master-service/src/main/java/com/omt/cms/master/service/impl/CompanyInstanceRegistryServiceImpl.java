/**
 * 
 */
package com.omt.cms.master.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.common.SystemCodes.RecordStatus;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;
import com.omt.cms.master.repo.op.CompanyOperations;
import com.omt.cms.master.service.CompanyInstanceRegistryService;
import com.omt.cms.master.service.bo.CompanyInstanceRegistryBO;
import com.omt.cms.master.service.bo.mapper.CompanyInstanceRegistryMapper;

/**
 * @author muragesh
 *
 */
@Service
public class CompanyInstanceRegistryServiceImpl implements CompanyInstanceRegistryService {
	@Autowired private CompanyInstanceRegistryOperations cirOp;
	@Autowired  private CompanyOperations cpOp;
	@Autowired  private CompanyInstanceRegistryMapper mapper;

	@Override
	public ServiceResponse read(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstanceRegistryBO cir = request.getRequestData(CompanyInstanceRegistryBO.class);
		CompanyInstanceRegistryBO cirRslt = null;
		try{
			if(cir!=null && cir.getCpId()!=null){
				Company cpEnt=cpOp.findById(cir.getCpId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cpEnt!=null){
					CompanyInstanceRegistry cirEnt = cirOp.findByCompany(cpEnt);
					if(cirEnt!=null){
						cirRslt = mapper.fromEntityToBO(cirEnt);
						result = true;
						resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
					}
				}
			}
		} catch(Exception e){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cirRslt);
		return response;
	}

	@Override
	public ServiceResponse add(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstanceRegistryBO cir=request.getRequestData(CompanyInstanceRegistryBO.class);
		CompanyInstanceRegistryBO cirRslt = null;
		try {
			if(cir!=null){
				cir.setDefaultValidUntil();
				Company cpEnt=cpOp.findById(cir.getCpId());
				if(cpEnt!=null){
					CompanyInstanceRegistry cirEnt = mapper.createNewEntityFromBO(cir, null);
					cirEnt.setCompany(cpEnt);
					cirEnt.setStatus(RecordStatus.ACTIVE.getValue());
					cirEnt.setActivated(false);
					cirEnt = cirOp.add(cirEnt);
					cirRslt = mapper.fromEntityToBO(cirEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cirRslt);
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstanceRegistryBO cir=request.getRequestData(CompanyInstanceRegistryBO.class);
		CompanyInstanceRegistryBO cirRslt = null;
		try {
			if(cir!=null && cir.getId()!=null){
				CompanyInstanceRegistry cirEnt=cirOp.findById(cir.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cirEnt!=null){
					mapper.copyUpdatedBOToEntity(cir, cirEnt, null);
					cirEnt = cirOp.update(cirEnt);
					cirRslt = mapper.fromEntityToBO(cirEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cirRslt);
		return response;
	}


	@Override
	public ServiceResponse delete(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		CompanyInstanceRegistryBO cirRslt = null;
		CompanyInstanceRegistryBO cir=request.getRequestData(CompanyInstanceRegistryBO.class);
		try {
			if(cir!=null && cir.getId()!=null){
				CompanyInstanceRegistry cirEnt=cirOp.findById(cir.getId());
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(cirEnt!=null){
					cirEnt.setStatus(SystemCodes.RecordStatus.DELETED.getValue());
					cirEnt=cirOp.update(cirEnt);
					cirRslt = mapper.fromEntityToBO(cirEnt);
					result = true;
					resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
				}
			}
		} catch (Exception e) {
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(cirRslt);
		return response;
	}
	
	
	@Override
	public ServiceResponse instanceNameExists(ServiceRequest request) {
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		String instanceName=request.getRequestData(String.class);
		try {
			if(StringUtils.isNotBlank(instanceName)){
				result = cirOp.instanceNameExists(instanceName);
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
}
