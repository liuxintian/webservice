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
import com.omt.cms.master.service.FeedService;
import com.omt.cms.master.service.bo.FeedBO;

/** 
 * 
 * @author Shiva Kalgudi
 *
 */

@RestController
@RequestMapping("/master-admin/api/feeds")
public class FeedController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);    

	@Autowired FeedService fs;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FeedBO> create(@RequestBody @Valid FeedBO feed, BindingResult result,
			UriComponentsBuilder builder, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for new Feeds. Request Details : {}", feed);
		sreq.addRequestData(feed);
		ServiceResponse response = fs.add(sreq);
		FeedBO updtIrm = getResultBO(response, FeedBO.class);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/master-admin/api/feeds/{id}").buildAndExpand(updtIrm.getId()).toUri());
		return new ResponseEntity<FeedBO>(updtIrm, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public FeedBO update(@RequestBody FeedBO feed, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for feed update received: feed Key {}", id);
		feed.setId(id);
		sreq.addRequestData(feed);
		ServiceResponse response = fs.update(sreq);
		return getResultBO(response, FeedBO.class);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/delete")
	public FeedBO delete(@RequestBody FeedBO feed, @PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for feed delete received: feed Key {}", id);
		feed.setId(id);
		sreq.addRequestData(feed);
		ServiceResponse response = fs.delete(sreq);
		return getResultBO(response, FeedBO.class);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public FeedBO read(@PathVariable Long id, @ModelAttribute("serviceRequest") ServiceRequest sreq) {
		LOGGER.debug("Request for feed read received: feed Key {}", id);
		sreq.addRequestData(id);
		ServiceResponse response = fs.read(sreq);
		return getResultBO(response, FeedBO.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public List<FeedBO> readAll(
			@RequestParam(name=SystemCodes.FILTER_TERM, required=false) String term,
			@RequestParam(name=SystemCodes.FILTER_PAGE, required=false) Integer page,
			@RequestParam(name=SystemCodes.FILTER_SIZE, required=false) Integer size,			
			@RequestParam(name=SystemCodes.FILTER_SORT_BY, required=false) String sortBy,
			@RequestParam(name=SystemCodes.FILTER_SORT_ORDER, required=false) Integer sortOrder,			
			@ModelAttribute("serviceRequest") ServiceRequest sreq
			) {
		LOGGER.debug("Request for Feeds received");
		FilterCriteriaBO criteria = new FilterCriteriaBO(term, page, size, sortBy, sortOrder);
		sreq.addRequestData(criteria);
		ServiceResponse response = fs.readAll(sreq);
		return response.getResponseData(List.class);
	}

}