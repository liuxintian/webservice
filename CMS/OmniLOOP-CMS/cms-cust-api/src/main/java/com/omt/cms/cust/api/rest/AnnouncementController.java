package com.omt.cms.cust.api.rest;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.core.service.bo.base.AnnouncementBO;
import com.omt.cms.cust.service.AnnouncementService;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/company-instances/{cpId}/announcements")
public class AnnouncementController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);    
	@Autowired AnnouncementService ces;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<AnnouncementBO> readAll(@PathVariable Long cpId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for Announcement list received: cp Key {}", cpId);
		sreq.addRequestData(cpId);
		ServiceResponse response = ces.readAll(sreq);
		return getResultBO(response, List.class);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{annId}")
	public AnnouncementBO read(@PathVariable Long cpId, @PathVariable Long annId, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for Announcement received: cp Key {}", cpId);
		AnnouncementBO exec = new AnnouncementBO();
		exec.setId(annId);
		exec.setCompanyId(cpId);
		sreq.addRequestData(exec);
		ServiceResponse response = ces.read(sreq);
		return getResultBO(response, AnnouncementBO.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AnnouncementBO> create(@PathVariable Long cpId, @RequestBody @Valid AnnouncementBO ceb, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new Announcement creation received. Request Details : {}", ceb);
		ceb.setCompanyId(cpId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.add(sreq);
		AnnouncementBO updtAnnouncement = getResultBO(response, AnnouncementBO.class);
		return new ResponseEntity<AnnouncementBO>(updtAnnouncement, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{annId}")
	public AnnouncementBO update(@PathVariable Long cpId,  @PathVariable Long annId, @RequestBody AnnouncementBO ceb, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for Announcement update received: cp Key {}", cpId);
		ceb.setCompanyId(cpId);
		ceb.setId(annId);
		sreq.addRequestData(ceb);
		ServiceResponse response = ces.update(sreq);
		return getResultBO(response, AnnouncementBO.class);
	}

}