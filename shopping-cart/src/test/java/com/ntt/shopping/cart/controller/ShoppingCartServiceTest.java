package com.ntt.shopping.cart.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.shopping.cart.entity.ShoppingCart;
import com.ntt.shopping.cart.repository.ProductRepository;
import com.ntt.shopping.cart.service.ShoppingCartPublishMessageService;
import com.ntt.shopping.cart.service.ShoppingCartService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@EmbeddedKafka
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingCartServiceTest {

	@MockBean
	private ShoppingCartService shoppingCartService;
	
	@MockBean
	private ShoppingCartPublishMessageService cartPublishMessageService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void testProcessCart() throws JsonProcessingException, Exception {
		
		ShoppingCart cart = new ShoppingCart();
		cart.setProductName("Iphone");
		cart.setPrice(21d);
		cart.setColor("White");
		cart.setQuantity(2);
		doNothing().when(cartPublishMessageService).publishEvent(any());
		doNothing().when(productRepository).save(any());
		String cartInfo = new ObjectMapper().writeValueAsString(cart); 
		assertEquals("SUCCESS", shoppingCartService.processCart(cartInfo));
	}
}
