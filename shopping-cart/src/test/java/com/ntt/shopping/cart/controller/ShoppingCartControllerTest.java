package com.ntt.shopping.cart.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
public class ShoppingCartControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShoppingCartService shoppingCartService;
	
	@MockBean
	private ShoppingCartPublishMessageService cartPublishMessageService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void testProcessProductDetails() throws JsonProcessingException, Exception {
		
		ShoppingCart cart = new ShoppingCart();
		cart.setProductName("Iphone");
		cart.setPrice(21d);
		cart.setColor("White");
		cart.setQuantity(2);
		doNothing().when(cartPublishMessageService).publishEvent(any());
		doNothing().when(productRepository).save(any());
		ResultActions resultActions = mockMvc.perform(post("").content(new ObjectMapper().writeValueAsString(cart)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		resultActions.andExpect(status().isOk());
	}
}
