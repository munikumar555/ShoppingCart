package com.ntt.shopping.cart.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.shopping.cart.entity.EventLog;
import com.ntt.shopping.cart.entity.ShoppingCart;
import com.ntt.shopping.cart.repository.EventLogRepository;
import com.ntt.shopping.cart.repository.ProductRepository;
import com.ntt.shopping.cart.service.ShoppingCartPublishMessageService;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartPublishMessageService cartPublishMessageService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EventLogRepository eventLogRepository;
	
	public String processCart(String cartInfo) {
		
		ObjectMapper mapper = new ObjectMapper();
		ShoppingCart shoppingCart = null;
		
		try {
			shoppingCart = mapper.readValue(cartInfo, ShoppingCart.class);
			cartPublishMessageService.publishEvent(shoppingCart);
			productRepository.save(shoppingCart);
		} catch (IOException |InterruptedException | ExecutionException | TimeoutException exception) {
			EventLog eventLog = new EventLog();
			eventLog.setCartInfo(cartInfo);
			eventLog.setRetryCount(shoppingCart.getRetryCount());
			try {
				cartPublishMessageService.publishRetryEvent(shoppingCart);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				e.printStackTrace();
			}
			eventLogRepository.save(eventLog);
		} 
		return "Success";
	}
}
