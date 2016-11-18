package com.omt.cms.company.instance.service.common;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.util.UriUtils;

import com.omt.cms.core.common.GsonHelper;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceConstants.ServiceResultCodes;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.entity.Company;
import com.omt.cms.master.entity.CompanyInstanceRegistry;
import com.omt.cms.master.repo.op.CompanyInstanceRegistryOperations;
import com.omt.cms.master.repo.op.CompanyOperations;

public abstract class BaseInstanceProxyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseInstanceProxyService.class);    
	@Autowired CompanyInstanceRegistryOperations cirOps;
	@Autowired protected CompanyOperations cpOps;
	@Autowired RestOperations restOps;

	protected <T> ServiceResponse readAllActive(Long companyId, String endPoint, String apiPath, Class<T> type) {
		String resultMsg = null;
		List<T> listEnt=null;
		ServiceResponse resp = isCompanyActive(companyId);
		boolean result = resp.getServiceResult().isResult();
		String resultCode = resp.getServiceResult().getResultCode();
		if(result){
			Company company = resp.getResponseData(Company.class);
			if(company.isActive()){
				result = false;
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				CompanyInstanceRegistry registry = cirOps.findByCompany(company);
				if(registry!=null){
					String urlPrefix = registry.getInstanceURL();	
					UriTemplate uri=new UriTemplate(urlPrefix + apiPath);
					String url=uri.expand(companyId, endPoint).toString();
					try{
						url = UriUtils.decode(url, "UTF-8");
						LOGGER.info("Instance URL Is :{}", url);
						ResponseEntity<List<T>> respEntity =restOps.exchange(url,HttpMethod.GET, null, new ParameterizedTypeReference<List<T>>() {});
						resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
						if(respEntity!=null && respEntity.getStatusCode() == HttpStatus.OK){
							listEnt = respEntity.getBody();
							if(listEnt!=null){
								result=true;
								resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
							}
						}
					}catch(Exception e){
						resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
						resultMsg = "Error Details :" + e.toString();
					}
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(listEnt);
		return response;
	}

	protected <T> ServiceResponse readActive(Long companyId,Long entId,String endPoint, String apiPath, Class<T> type) {
		String resultMsg = null;
		T resultEnt = null;
		ServiceResponse resp = isCompanyActive(companyId);
		boolean result = resp.getServiceResult().isResult();
		String resultCode = resp.getServiceResult().getResultCode();
		if(result){
			Company company = resp.getResponseData(Company.class);
			if(company.isActive()){
				CompanyInstanceRegistry registry = cirOps.findByCompany(company);
				resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
				if(registry!=null){
					String urlPrefix = registry.getInstanceURL();
					UriTemplate uri = new UriTemplate(urlPrefix + apiPath);
					String url = uri.expand(companyId, endPoint, entId).toString();
					LOGGER.info("Instance URL Is :{}", url);
					try{
						ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.GET, null, type);
						resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
						if(respEntity!=null && respEntity.getStatusCode() == HttpStatus.OK){
							resultEnt = respEntity.getBody();
							if(resultEnt!=null){
								result=true;
								resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
							}
						}
					}catch(Exception e){
						resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
						resultMsg = "Error Details :" + e.toString();
					}
				}
			}			
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	protected <T> ServiceResponse add(Company company, Long entId, T ent, String endPoint, Class<T> type) {
		String resultMsg = null;
		boolean result = false;
		String  resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue(); 
		T resultEnt=null;
		if(company.isActive()){
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(registry!=null){
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
 				String urlPrefix = registry.getInstanceURL();
				UriTemplate uri=new UriTemplate(urlPrefix + "/oapi/{endPoint}/{entId}");
				String url=uri.expand(endPoint,entId).toString();
				LOGGER.info("Instance URL Is :{}", url);
				try{
					HttpHeaders headers = createHeaders();
					HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(ent), headers);
					ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.POST, requestEntity, type);
					resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
					if(respEntity!=null && (respEntity.getStatusCode() == HttpStatus.OK || respEntity.getStatusCode() == HttpStatus.CREATED)){
						resultEnt = respEntity.getBody();
						if(resultEnt!=null){
							result=true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}catch(Exception e){
					resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
					resultMsg = "Error Details :" + e.toString();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	protected <T> ServiceResponse add(Company company, T ent, String endPoint, Class<T> type) {
		String resultMsg = null;
		boolean result = false;
		String  resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue(); 
		T resultEnt = null;
		if(company.isActive()){
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(registry!=null){
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
 				String urlPrefix = registry.getInstanceURL();
				UriTemplate uri=new UriTemplate(urlPrefix + "/oapi/{endPoint}");
				String url=uri.expand(endPoint).toString();
				LOGGER.info("Instance URL Is :{}", url);
				try{
					HttpHeaders headers = createHeaders();
					HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(ent), headers);
					ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.POST, requestEntity, type);
					resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
					if(respEntity!=null && (respEntity.getStatusCode() == HttpStatus.OK || respEntity.getStatusCode() == HttpStatus.CREATED)){
						resultEnt = respEntity.getBody();
						if(resultEnt!=null){
							result=true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}catch(Exception e){
					resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
					resultMsg = "Error Details :" + e.toString();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	protected <T> ServiceResponse add(Long companyId, T entity, String apiPath, String entityName, Class<T> type) {
		String resultMsg = null;
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		T resultEnt=null;
		ServiceResponse resp = isCompanyActive(companyId);
		result = resp.getServiceResult().isResult();
		resultCode = resp.getServiceResult().getResultCode();
		if(result){
			Company company = resp.getResponseData(Company.class);
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			result = false;
			if(registry!=null){
				String urlPrefix = registry.getInstanceURL();
				UriTemplate uri = new UriTemplate(urlPrefix + apiPath);
				String url = uri.expand(companyId, entityName).toString();
				LOGGER.info("Instance URL Is :{}", url);
				try{
					HttpHeaders headers = createHeaders();
					HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(entity), headers);
					ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.POST, requestEntity, type);
					resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
					if(respEntity!=null && (respEntity.getStatusCode() == HttpStatus.OK || respEntity.getStatusCode() == HttpStatus.CREATED)){
						resultEnt = respEntity.getBody();
						if(resultEnt!=null){
							result=true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}catch(Exception e){
					resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
					resultMsg = "Error Details :" + e.toString();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	protected <T> ServiceResponse update(Long companyId, T entity, Long entityId, String apiPath, String entityName, Class<T> type) {
		boolean result = false;
		String resultMsg = null;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		T resultEnt=null;
		ServiceResponse resp = isCompanyActive(companyId);
		result = resp.getServiceResult().isResult();
		resultCode = resp.getServiceResult().getResultCode();
		if(result){
			Company company = resp.getResponseData(Company.class);
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			result = false;
			if(registry!=null){
				String urlPrefix = registry.getInstanceURL();
				UriTemplate uri = new UriTemplate(urlPrefix + apiPath);
				String url = uri.expand(companyId, entityName, entityId).toString();
				LOGGER.info("Instance URL Is :{}", url);
				try{
					HttpHeaders headers = createHeaders();
					HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(entity), headers);
					ResponseEntity<T> respEntity =restOps.exchange(url, HttpMethod.PUT, requestEntity, type);
					resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
					if(respEntity!=null && respEntity.getStatusCode() == HttpStatus.OK){
						resultEnt = respEntity.getBody();
						if(resultEnt!=null){
							result=true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}catch(Exception e){
					resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
					resultMsg = "Error Details :" + e.toString();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	private ServiceResponse isCompanyActive(Long companyId){
		boolean result = false;
		String resultCode = ServiceResultCodes.REQUEST_DATA_INVALID.getValue();
		Company company = null;
		if (companyId != null) {
			company = cpOps.findById(companyId);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(company!=null){
				resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue();
				if(company.isActive()){
					result = true;
					resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.addResponseData(company);
		return response;
	}

	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	protected <T> ServiceResponse readAllActive(String apiURL, Class<T> type) {
		boolean result = true;
		String resultCode = ServiceResultCodes.RECORD_FOUND.getValue();
		String resultMsg = null;
		T listEnt=null;
		try{
			LOGGER.info("API EndPoint:{}", apiURL);
			listEnt = restOps.getForObject(apiURL, type);
		}catch(Exception e){
			LOGGER.warn("Exeption occured :{}", e);
			resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
			resultMsg = "Error Details :" + e.toString();
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(listEnt);
		return response;
	}
	
	protected String addFilterParams(String resURI, Timestamp validUntil, Integer status) {
		if(validUntil!=null && status!=null){
			resURI = resURI + "?" + SystemCodes.FILTER_VALID_UNTIL + "=" + validUntil.getTime() + "&" + SystemCodes.FILTER_STATUS + "=" + status;
		}else if(validUntil!=null && status==null){
			resURI = resURI + "?" + SystemCodes.FILTER_VALID_UNTIL + "=" + validUntil.getTime();
		}else if(validUntil==null && status!=null){
			resURI = resURI + "?" + SystemCodes.FILTER_STATUS + "=" + status;
		}
		return resURI;
	}

	protected String addFilterParams(String resURI, FilterCriteriaBO criteria) {
		StringBuilder builder = new StringBuilder();
		builder.append(resURI);
		String separator = "?";

		if(criteria.validUntil!=null){
			builder.append(separator + SystemCodes.FILTER_VALID_UNTIL + "=" + criteria.validUntil.getTime());
			separator = "&";
		}

		if(criteria.status!=null){
			builder.append(separator + SystemCodes.FILTER_STATUS + "=" + criteria.status);
			separator = "&";
		}

		if(criteria.tagRoleEmtpy!=null && criteria.tagRoleEmtpy){
			builder.append(separator + SystemCodes.FILTER_EMPTY_TAG + "=" + criteria.tagRoleEmtpy);
			separator = "&";
		}

		if(StringUtils.isNotBlank(criteria.tagRole)){
			builder.append(separator + SystemCodes.FILTER_TAGS + "=" + criteria.tagRole);
		}
		return builder.toString();
	
	}
	
	protected <T> ServiceResponse disable(Company company, T ent, String endPoint, Class<T> type) {
		String resultMsg = null;
		boolean result = false;
		String  resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue(); 
		T resultEnt = null;
		if(company.isActive()){
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(registry!=null){
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
 				String urlPrefix = registry.getInstanceURL();
				UriTemplate uri=new UriTemplate(urlPrefix + "/oapi/{endPoint}");
				String url=uri.expand(endPoint).toString();
				LOGGER.info("Instance URL Is :{}", url);
				try{
					HttpHeaders headers = createHeaders();
					HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(ent), headers);
					ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.PUT, requestEntity, type);
					resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
					if(respEntity!=null && (respEntity.getStatusCode() == HttpStatus.OK || respEntity.getStatusCode() == HttpStatus.CREATED)){
						resultEnt = respEntity.getBody();
						if(resultEnt!=null){
							result=true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}catch(Exception e){
					resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
					resultMsg = "Error Details :" + e.toString();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	protected <T> ServiceResponse enable(Company company, T ent, String endPoint, Class<T> type) {
		String resultMsg = null;
		boolean result = false;
		String  resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue(); 
		T resultEnt = null;
		if(company.isActive()){
			CompanyInstanceRegistry registry = cirOps.findByCompany(company);
			resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
			if(registry!=null){
				resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
 				String urlPrefix = registry.getInstanceURL();
				UriTemplate uri=new UriTemplate(urlPrefix + "/oapi/{endPoint}");
				String url=uri.expand(endPoint).toString();
				LOGGER.info("Instance URL Is :{}", url);
				try{
					HttpHeaders headers = createHeaders();
					HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(ent), headers);
					ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.PUT, requestEntity, type);
					resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
					if(respEntity!=null && (respEntity.getStatusCode() == HttpStatus.OK || respEntity.getStatusCode() == HttpStatus.CREATED)){
						resultEnt = respEntity.getBody();
						if(resultEnt!=null){
							result=true;
							resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
						}
					}
				}catch(Exception e){
					resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
					resultMsg = "Error Details :" + e.toString();
				}
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}	

	protected <T> ServiceResponse instanceUpdate(Company company, T ent, String endPoint, Class<T> type) {
		String resultMsg = null;
		boolean result = false;
		String  resultCode = ServiceResultCodes.RECORD_INACTIVE.getValue(); 
		T resultEnt = null;
		CompanyInstanceRegistry registry = cirOps.findByCompany(company);
		resultCode = ServiceResultCodes.RECORD_NOT_FOUND.getValue();
		if(registry!=null){
			resultCode = ServiceResultCodes.OPERATION_FAILURE.getValue();
			String urlPrefix = registry.getInstanceURL();
			UriTemplate uri=new UriTemplate(urlPrefix + "/oapi/{endPoint}");
			String url=uri.expand(endPoint).toString();
			LOGGER.info("Instance URL Is :{}", url);
			try{
				HttpHeaders headers = createHeaders();
				HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(ent), headers);
				ResponseEntity<T> respEntity =restOps.exchange(url,HttpMethod.PUT, requestEntity, type);
				resultCode = ServiceResultCodes.INSTANCE_ERROR.getValue();
				if(respEntity!=null && (respEntity.getStatusCode() == HttpStatus.OK || respEntity.getStatusCode() == HttpStatus.CREATED)){
					resultEnt = respEntity.getBody();
					if(resultEnt!=null){
						result=true;
						resultCode = ServiceResultCodes.OPERATION_SUCCESS.getValue();
					}
				}
			}catch(Exception e){
				resultCode = ServiceResultCodes.INSTANCE_UNREACHABLE.getValue();
				resultMsg = "Error Details :" + e.toString();
			}
		}
		ServiceResponse response = new ServiceResponse(result, resultCode);
		response.getServiceResult().setResultMessage(resultMsg);
		response.addResponseData(resultEnt);
		return response;
	}

	
}
