package com.tiny.url.urlconverter.services;

import com.tiny.url.urlconverter.producers.KafkaProducer;
import com.tiny.url.urlconverter.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ShortenerService {
	
	@Autowired
	KafkaProducer producer;
	
	public String urlShortener(String url) throws NoSuchAlgorithmException {
		String tinyUrl = UrlUtils.convert(url);
		producer.send(tinyUrl, url);
		return tinyUrl;
	}
}
