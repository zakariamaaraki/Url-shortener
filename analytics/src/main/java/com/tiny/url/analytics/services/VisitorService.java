package com.tiny.url.analytics.services;

import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {
	
	@Value("${spring.kafka.store.visitor-store-name}")
	private String storeName;
	
	@Autowired
	StreamsBuilderFactoryBean streamsBuilderFactoryBean;
	
	public long getNumberOfVisits(String url) {
		
		final ReadOnlyKeyValueStore<String, Long> store = streamsBuilderFactoryBean.getKafkaStreams().store(StoreQueryParameters.fromNameAndType(storeName, QueryableStoreTypes.keyValueStore()));
		return store.get(url);
	}
}
