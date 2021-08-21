package com.tiny.url.analytics.controllers;

import com.tiny.url.analytics.services.VisitorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;
	
	@GetMapping(value = "analytics")
	@ApiOperation(
			value = "number of visits for a specific url",
			notes = "return the number of visits for a specific url (the original url)",
			response = Long.class
	)
	public long getNumberOfVisits(@ApiParam(value = "The original url") @RequestParam("url") String url) {
		return visitorService.getNumberOfVisits(url);
	}
}
