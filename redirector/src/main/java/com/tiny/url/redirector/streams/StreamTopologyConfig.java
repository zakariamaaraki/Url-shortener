package com.tiny.url.redirector.streams;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;

import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Slf4j
@Configuration
@EnableKafkaStreams
public class StreamTopologyConfig {
	
	// Serializers/deserializers (serde) for String
	final Serde<String> stringSerde = Serdes.String();
	
	@Bean
	public Topology topology(@Value("${spring.kafka.topic.tiny-url}") String topicName,
	                         @Value("${spring.kafka.store.tiny-url-store-name}") String storeName,
	                         @Autowired StreamsBuilder builder) {
		
		KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(storeName);
		
		builder.table(topicName, Materialized.<String, String>as(storeSupplier)
				.withKeySerde(stringSerde)
				.withValueSerde(stringSerde));
		
		log.info("{}", builder.build().describe());
		
		return builder.build();
	}

}
