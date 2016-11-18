package com.omt.cms.master.service.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.omt.cms.core.common.GsonHelper;
import com.omt.cms.core.service.base.ServiceResponse;

public class InstanceRestClient {

	protected RestTemplate mailInvoker = new RestTemplate();
	protected HttpHeaders headers = new HttpHeaders();

	public InstanceRestClient() {
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	protected ServiceResponse invoke(Object request, String endPoint, HttpMethod method) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(GsonHelper.toJsonAll(request), headers);
		ResponseEntity<ServiceResponse> result = mailInvoker.exchange(endPoint, method, requestEntity, ServiceResponse.class);
		if (result.hasBody()) {
			return result.getBody();
		}
		return null;
	}
}
