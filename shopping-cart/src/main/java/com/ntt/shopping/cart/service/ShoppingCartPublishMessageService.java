package com.ntt.shopping.cart.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ntt.shopping.cart.entity.ShoppingCart;

@Service
public class ShoppingCartPublishMessageService {

	@Value("${ntt.kakfa.topicName}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, ShoppingCart> kafkaTemplate;

	public void publishEvent(ShoppingCart cart) throws InterruptedException, ExecutionException, TimeoutException {

		final StringBuilder key = new StringBuilder("productName:").append(cart.getProductName());
		kafkaTemplate.send(topicName, key.toString(), cart).get(10, TimeUnit.SECONDS);
	}

	public void publishRetryEvent(ShoppingCart cart) throws InterruptedException, ExecutionException, TimeoutException {
		
		cart.setRetryCount(cart.getRetryCount()+1);
		cart.setRetryTime(new Timestamp(new Date().getTime()));
		publishEvent(cart);
	}
}
