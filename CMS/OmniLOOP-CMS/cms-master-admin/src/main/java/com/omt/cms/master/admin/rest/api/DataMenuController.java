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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.omt.cms.core.api.base.BaseController;
import com.omt.cms.core.service.base.ServiceRequest;
import com.omt.cms.core.service.base.ServiceResponse;
import com.omt.cms.master.service.DataMenuService;
import com.omt.cms.master.service.bo.DataMenuBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/master-admin/api/data-menus")
public class DataMenuController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataMenuController.class);    

	@Autowired DataMenuService dms;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DataMenuBO> create(@RequestBody @Valid DataMenuBO dm, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new Data Menu creation received. Request Details : {}", dm);
		sreq.addRequestData(dm);
		ServiceResponse response = dms.add(sreq);
		DataMenuBO updtDataMenu = getResultBO(response, DataMenuBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/master-admin/api/companies/{id}").buildAndExpand(updtDataMenu.getId()).toUri());
		return new ResponseEntity<DataMenuBO>(updtDataMenu, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public DataMenuBO update(@RequestBody DataMenuBO dm, 
			@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for dm update received: dm Key {}", id);
		dm.setId(id);
		sreq.addRequestData(dm);
		ServiceResponse response = dms.update(sreq);
		return getResultBO(response, DataMenuBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/delete")
	public DataMenuBO delete(@RequestBody DataMenuBO dm, 
			@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for dm delete received: dm Key {}", id);
		dm.setId(id);
		sreq.addRequestData(dm);
		ServiceResponse response = dms.delete(sreq);
		return getResultBO(response, DataMenuBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public DataMenuBO read(@PathVariable Long id, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for dm read received: dm Key {}", id);
		DataMenuBO dm=new DataMenuBO();
		dm.setId(id);
		sreq.addRequestData(dm);
		ServiceResponse response = dms.read(sreq);
		return getResultBO(response, DataMenuBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dataMenu/{dataMenu}")
	public DataMenuBO readDataMenu(@PathVariable String dataMenu, 
			@ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for dm read received: dm dataMenu {}", dataMenu);
		DataMenuBO dm=new DataMenuBO();
		dm.setDataMenu(dataMenu);
		sreq.addRequestData(dm);
		ServiceResponse response = dms.readByDataMenu(sreq);
		return getResultBO(response, DataMenuBO.class);
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<DataMenuBO> readAll(  ServiceRequest sreq) {
		LOGGER.debug("Request for dm read ");
		ServiceResponse response = dms.readAll(sreq);
		return response.getResponseData(List.class);
	}


}