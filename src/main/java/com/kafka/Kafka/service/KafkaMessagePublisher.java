package com.kafka.Kafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaMessagePublisher {
	
	private KafkaTemplate<String, Object> template;
	
	public void sendMessageToTopic(String message) {
		CompletableFuture<SendResult<String, Object>> future =  template.send("Chintu", message);
		future.whenComplete((result,ex)->{
			if(ex == null) {
				System.out.println("Sent Message =[ " + message + "] with offset =[" + + result.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to Send message =[" + message + "] due to :" + ex.getMessage());
			}
		});
	}

}
