package com.omt.cms.master.admin.rest.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.common.SystemCodes;
import com.omt.cms.core.service.base.FilterCriteriaBO;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.ShareRegistryService;
import com.omt.cms.master.service.bo.ShareRegistryBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/master-admin/api/share-registries")
public class ShareRegistryController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShareRegistryController.class);    

	@Autowired ShareRegistryService srs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ShareRegistryBO> create(@RequestBody @Valid ShareRegistryBO sr, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new share registries. Request Details : {}", sr);
		sreq.addRequestData(sr);
		ServiceResponse response = srs.add(sreq);
		ShareRegistryBO updtIrm = getResultBO(response, ShareRegistryBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/master-admin/api/share-registries/{id}").buildAndExpand(updtIrm.getId()).toUri());
		return new ResponseEntity<ShareRegistryBO>(updtIrm, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ShareRegistryBO update(@RequestBody ShareRegistryBO sr, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for sr update received: sr Key {}", id);
		sr.setId(id);
		sreq.addRequestData(sr);
		ServiceResponse response = srs.update(sreq);
		return getResultBO(response, ShareRegistryBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/delete")
	public ShareRegistryBO delete(@RequestBody ShareRegistryBO sr, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for sr delete received: sr Key {}", id);
		sr.setId(id);
		sreq.addRequestData(sr);
		ServiceResponse response = srs.delete(sreq);
		return getResultBO(response, ShareRegistryBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ShareRegistryBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for sr read received: sr Key {}", id);
		sreq.addRequestData(id);
		ServiceResponse response = srs.read(sreq);
		return getResultBO(response, ShareRegistryBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<ShareRegistryBO> readAll(
			@RequestParam(name=SystemCodes.FILTER_TERM, required=false) String term,
			@RequestParam(name=SystemCodes.FILTER_PAGE, required=false) Integer page,
			@RequestParam(name=SystemCodes.FILTER_SIZE, required=false) Integer size,			
			@RequestParam(name=SystemCodes.FILTER_SORT_BY, required=false) String sortBy,
			@RequestParam(name=SystemCodes.FILTER_SORT_ORDER, required=false) Integer sortOrder,
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for SR received");
		FilterCriteriaBO criteria = new FilterCriteriaBO(term, page, size, sortBy, sortOrder);
		sreq.addRequestData(criteria);
		ServiceResponse response = srs.readAll(sreq);
		return response.getResponseData(List.class);
	}

}