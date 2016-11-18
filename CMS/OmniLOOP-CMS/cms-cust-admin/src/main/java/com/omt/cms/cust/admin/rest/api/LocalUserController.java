package com.omt.cms.cust.admin.rest.api;

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
import com.omt.cms.core.service.base.ServiceResult;
import com.omt.cms.core.service.bo.base.LocalUserBO;
import com.omt.cms.core.service.bo.base.MailMessageReqBO;
import com.omt.cms.core.service.bo.base.UsersBO;
import com.omt.cms.core.service.bo.base.UsersLoginBO;
import com.omt.cms.cust.service.LocalUserService;
import com.omt.cms.cust.service.UsersProxyService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/cust-admin/api/company-instances/{cpId}/local-users")
public class LocalUserController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserController.class);    
	@Autowired private LocalUserService lus;
	@Autowired private UsersProxyService ups;

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
		ServiceResponse response = lus.readAll(sreq);
		return getResultBO(response, List.class);
	}

	/* Commented out as per input from Jayant
	//I don’t know if something was missed during communication, because I feel it has, this was not the concern we started with, the concern was removing the
	//Download Users in Instances, this is only required for the Admin instance (Master).
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value="/csv")
	public void downloadAll(@PathVariable Long cpId, 
			@RequestParam(name=SystemCodes.FILTER_TERM, required=false) String term,
			@RequestParam(name=SystemCodes.FILTER_PAGE, required=false) Integer page,
			@RequestParam(name=SystemCodes.FILTER_SIZE, required=false) Integer size,
			@RequestParam(name=SystemCodes.FILTER_SORT_BY, required=false) String sortBy,
			@RequestParam(name=SystemCodes.FILTER_SORT_ORDER, required=false) Integer sortOrder,
			@ModelAttribute("serviceRequest") ServiceRequest sreq, HttpServletResponse res) {
		LOGGER.debug("Request for LocalUser list received: cp Key {}", cpId);
		FilterCriteriaBO criteria = new FilterCriteriaBO(term, page, size, sortBy, sortOrder);
		criteria.cpId = cpId;
		sreq.addRequestData(criteria);
		ServiceResponse response = lus.readAll(sreq);
		String csvFileName="local-users.csv";
		List<LocalUserBO> users=getResultBO(response, List.class);
		res.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		res.setHeader(headerKey, headerValue);
		PrintWriter writer;
		try {
			writer = res.getWriter();
			writer.println(getHeaders());
			for(LocalUserBO bo:users){
				writer.println( bo.toCSVRow());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
		}
	}
	*/
	
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
		ServiceResponse response = lus.addToMasterAndInstance(sreq);
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

	@RequestMapping(method = RequestMethod.PUT, value = "/login-names-inuse")
	public UsersLoginBO userLoginNamesInUse(@PathVariable Long cpId, @RequestBody UsersLoginBO bo, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for checking login names received");
		sreq.addRequestData(bo);
		ServiceResponse response = ups.userLoginNamesInUse(sreq);
		return getResultBO(response, UsersLoginBO.class);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/mutli-users")
	public UsersBO addUsers(@PathVariable Long cpId, @RequestBody UsersBO users, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		users.setCpId(cpId);
		sreq.addRequestData(users);
		ServiceResponse response = ups.addUsers(sreq);
		return getResultBO(response, UsersBO.class);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/send-mail")
	public ServiceResult sendMail(@PathVariable Long cpId, @RequestBody MailMessageReqBO msgReq, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		msgReq.setCompanyId(cpId);
		sreq.addRequestData(msgReq);
		ServiceResponse response = lus.sendMail(sreq);
		return response.getServiceResult();
	}

	@SuppressWarnings("unused")
	private String getHeaders(){
		StringBuilder builder = new StringBuilder();
		builder.append(" loginName ");
		builder.append(", userName");
		builder.append(", createdOn	");
 		builder.append(", address");
 		builder.append(", userContact");
 		builder.append(", userStatus");
 		builder.append(", userInvited");
 		builder.append(", userEmail");
 		builder.append(", emailValid");
 		builder.append(", phoneValid");
 		builder.append(", title");
 		builder.append(", userActivated");
 		return builder.toString();
  	}

}