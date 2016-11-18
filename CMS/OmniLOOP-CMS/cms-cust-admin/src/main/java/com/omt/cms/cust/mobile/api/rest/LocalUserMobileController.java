package com.omt.cms.cust.mobile.api.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.cust.service.LocalUserService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/company-instances/{cpId}/local-users")
public class LocalUserMobileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserMobileController.class);    
	@Autowired private LocalUserService lus;
 
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<LocalUserBO> readAll(@PathVariable Long cpId, 
			@RequestParam(name=SystemCodes.FILTER_TERM, required=false) String term,
			@RequestParam(name=SystemCodes.FILTER_PAGE, required=false) Integer page,
			@RequestParam(name=SystemCodes.FILTER_SIZE, required=false) Integer size,
			@RequestParam(name=SystemCodes.FILTER_SORT_BY, required=false) String sortBy,
			@RequestParam(name=SystemCodes.FILTER_SORT_ORDER, required=false) Integer sortOrder,
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser list received: cp Key {}", cpId);
		FilterCriteriaBO criteria = new FilterCriteriaBO(term, page, size, sortBy, sortOrder);
		criteria.cpId = cpId;
		sreq.addRequestData(criteria);
		ServiceResponse response = lus.readAllWithDeviceInfo(sreq);
		return getResultBO(response, List.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/type/{shareSubscriber}")
	public List<LocalUserBO> readAllyByType(@PathVariable Long cpId, 
			@PathVariable boolean shareSubscriber,
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser list received: cp Key {}", cpId);
		LocalUserBO reqBO = new LocalUserBO();
		reqBO.setCompanyId(cpId);
		reqBO.setShareSubscriber(shareSubscriber);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.readAllByType(sreq);
		return getResultBO(response, List.class);
	}

	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public LocalUserBO read(@PathVariable Long cpId, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser received: cp Key {}", cpId);
		LocalUserBO exec = new LocalUserBO();
		exec.setId(id);
		exec.setCompanyId(cpId);
		sreq.addRequestData(exec);
		ServiceResponse response = lus.read(sreq);
		return getResultBO(response, LocalUserBO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<LocalUserBO> create(@PathVariable Long cpId, @RequestBody @Valid LocalUserBO reqBO, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new LocalUser creation received. Request Details : {}", reqBO);
		reqBO.setCompanyId(cpId);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.add(sreq);
		LocalUserBO updtLocalUser = getResultBO(response, LocalUserBO.class);
		return new ResponseEntity<LocalUserBO>(updtLocalUser, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	public LocalUserBO update(@PathVariable Long cpId,  @PathVariable Long id, @RequestBody LocalUserBO reqBO, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser update received: cp Key {}", cpId);
		reqBO.setCompanyId(cpId);
		reqBO.setId(id);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.update(sreq);
		return getResultBO(response, LocalUserBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}/subscribe-status")
	public LocalUserBO updateShareSubscriptionSts(@PathVariable Long cpId,  @PathVariable Long id, @RequestBody LocalUserBO reqBO, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for LocalUser update received: cp Key {}", cpId);
		reqBO.setCompanyId(cpId);
		reqBO.setId(id);
		sreq.addRequestData(reqBO);
		ServiceResponse response = lus.updateShareSubscription(sreq);
		return getResultBO(response, LocalUserBO.class);
	}

}