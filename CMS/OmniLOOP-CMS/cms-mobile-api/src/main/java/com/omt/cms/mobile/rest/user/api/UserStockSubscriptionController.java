package com.omt.cms.mobile.rest.user.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.base.ServiceResult;
import com.omt.cms.user.service.UserStockSubscriptionService;
import com.omt.cms.user.service.bo.UserStockSubscriptionBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/mobile/api/users/{userId}/stock-subscriptions")
public class UserStockSubscriptionController extends BaseController {
	//private static final Logger LOGGER = LoggerFactory.getLogger(UserStockSubscriptionController.class);    
	@Autowired UserStockSubscriptionService uss;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserStockSubscriptionBO> create(@PathVariable Long userId, @RequestBody @Valid UserStockSubscriptionBO uw, 
			BindingResult result,
			UriComponentsBuilder builder, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		uw.setUserId(userId);
		sreq.addRequestData(uw);
		ServiceResponse response = uss.add(sreq);
		UserStockSubscriptionBO updtUw = getResultBO(response, UserStockSubscriptionBO.class);
		return new ResponseEntity<UserStockSubscriptionBO>(updtUw, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/delete")
	public UserStockSubscriptionBO delete(@PathVariable Long userId, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		UserStockSubscriptionBO uw = new UserStockSubscriptionBO();
		uw.setUserId(userId);
		uw.setId(id);
		sreq.addRequestData(uw);
		ServiceResponse response = uss.delete(sreq);
		return getResultBO(response, UserStockSubscriptionBO.class);
	}


	@RequestMapping(method = RequestMethod.PUT, value = "/delete-tickers")
	public UserStockSubscriptionBO deleteTickers(@PathVariable Long userId, @RequestBody @Valid UserStockSubscriptionBO uw,
		@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		uw.setUserId(userId);
		sreq.addRequestData(uw);
		ServiceResponse response = uss.deleteTickers(sreq);
		return getResultBO(response, UserStockSubscriptionBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/ticker/{companyTicker}/delete")
	public ServiceResult deleteByAsxCode(@PathVariable Long userId, @PathVariable String companyTicker,  @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		UserStockSubscriptionBO uw = new UserStockSubscriptionBO();
		uw.setUserId(userId);
		uw.setCompanyTicker(companyTicker);
		sreq.addRequestData(uw);
		ServiceResponse response = uss.deleteByAsxCode(sreq);
		return response.getServiceResult();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<UserStockSubscriptionBO> read(@PathVariable Long userId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		UserStockSubscriptionBO uw=new UserStockSubscriptionBO();
		uw.setUserId(userId);
		sreq.addRequestData(uw);
		ServiceResponse response = uss.read(sreq);
		return getResultBO(response, List.class);
	}
}