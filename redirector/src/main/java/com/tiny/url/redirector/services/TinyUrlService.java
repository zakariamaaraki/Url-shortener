package com.tiny.url.redirector.services;

import com.tiny.url.redirector.producers.KafkaProducer;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlService {
	
	@Value("${spring.kafka.store.tiny-url-store-name}")
	private String storeName;
	
	@Autowired
	private StreamsBuilderFactoryBean streamsBuilderFactoryBean;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	public String getOriginalUrl(String tinyUrl) {
		
		final ReadOnlyKeyValueStore<String, String> store = streamsBuilderFactoryBean.getKafkaStreams().store(StoreQueryParameters.fromNameAndType(storeName, QueryableStoreTypes.keyValueStore()));
		return store.get(tinyUrl);
	}
	
	public void sendToAnalytics(String url) {
		kafkaProducer.send(url);
	}
}
