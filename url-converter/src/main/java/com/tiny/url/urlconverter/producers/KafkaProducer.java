package com.tiny.url.urlconverter.producers;

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
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${spring.kafka.topic.tiny-url}")
	private String topic;
	
	public void send(String tinyUrl, String url) {
		kafkaTemplate.send(topic, (Integer)null, tinyUrl, url).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> result) {
				
				ProducerRecord<String, String> producerRecord = result.getProducerRecord();
                log.info("Successfully publishing new record url = {}, tinyUrl = {} to the topic = {} and partition = {}",
		                producerRecord.value(),
		                producerRecord.key(),
		                producerRecord.topic(),
		                producerRecord.partition());
			}
			
			@SneakyThrows
			@Override
			public void onFailure(Throwable ex) {
                log.warn("Error can not publish new record to the topic {}, {}", topic, ex);
                throw ex;
			}
		});
	}
}
