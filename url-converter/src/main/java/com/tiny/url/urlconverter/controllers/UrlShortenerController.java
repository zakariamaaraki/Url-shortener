package com.tiny.url.urlconverter.controllers;

import com.tiny.url.urlconverter.services.ShortenerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RequestMapping("api")
@RestController
public class UrlShortenerController {
	
	@Autowired
	private ShortenerService shortenerService;
	
	@Value("${spring.redirector.prefix}")
	private String urlPrefix;
	
	@GetMapping(value = "shortener")
	@ApiOperation(
			value = "url shortener",
			notes = "return a tiny url",
			response = String.class
	)
	public String getTinyUrl(@ApiParam(value = "The original url") @RequestParam("url") String url) throws NoSuchAlgorithmException {
		log.info("new request ", url);
		return urlPrefix + shortenerService.urlShortener(url);
	}
}
