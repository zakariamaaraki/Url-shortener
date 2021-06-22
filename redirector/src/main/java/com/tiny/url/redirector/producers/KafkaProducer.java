package com.tiny.url.redirector.producers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class KafkaProducer {
	
	@Value("${spring.kafka.topic.visitor}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate<String, Long> kafkaTemplate;
	
	public void send(String url) {
		
		kafkaTemplate.send(topicName, (Integer)null, url, 1L).addCallback(new ListenableFutureCallback<SendResult<String, Long>>() {
			
			@Override
			public void onSuccess(SendResult<String, Long> result) {
				
				ProducerRecord<String, Long> producerRecord = result.getProducerRecord();
				log.info("Successfully publishing new record url = {} to the topic = {} and partition = {}",
						producerRecord.key(),
						producerRecord.topic(),
						producerRecord.partition());
			}
			
			@SneakyThrows
			@Override
			public void onFailure(Throwable ex) {
				log.warn("Error can not publish new record to the topic {}, {}", topicName, ex);
				throw ex;
			}
		});
	}
}
