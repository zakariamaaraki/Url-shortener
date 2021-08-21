package com.tiny.url.redirector.controllers;

import com.tiny.url.redirector.exceptions.UrlNotFoundException;
import com.tiny.url.redirector.services.TinyUrlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RequestMapping("redirector")
@RestController
public class RedirectController {
	
	@Autowired
	TinyUrlService tinyUrlService;
	
	@GetMapping("{tinyUrl}")
	@ApiOperation(
			value = "Redirector",
			notes = "retrieve the original url and redirect to it",
			response = RedirectView.class
	)
	public RedirectView redirect(@ApiParam(value = "The tiny url") @PathVariable String tinyUrl) {
		
		String url = tinyUrlService.getOriginalUrl(tinyUrl);
		
		if(url == null) {
			throw new UrlNotFoundException("Url Not Found");
		}
		
		tinyUrlService.sendToAnalytics(url);
		log.info("redirection to {}", url);
		
		return new RedirectView(url);
	}
}
