package com.tiny.url.analytics.streams;

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

@Configuration
@Slf4j
@EnableKafkaStreams
public class StreamTopologyConfig {
	
	// Serializers/deserializers (serde) for String and Long types
	final Serde<String> stringSerde = Serdes.String();
	final Serde<Long> longSerde = Serdes.Long();
	
	@Bean
	public Topology topology(@Value("${spring.kafka.topic.visitor}") String topicName,
	                         @Value("${spring.kafka.store.visitor-store-name}") String storeName,
	                         @Autowired StreamsBuilder builder) {
		
		KStream<String, Long> stream = builder.stream(topicName, Consumed.with(stringSerde, longSerde));
		
		KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(storeName);
		
		stream.groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
				.aggregate(() -> 0L, (url, counter, aggregate) -> counter + aggregate, Materialized.<String, Long>as(storeSupplier)
						.withKeySerde(stringSerde)
						.withValueSerde(longSerde));
		
		log.info("{}", builder.build().describe());
		
		return builder.build();
	}

}
