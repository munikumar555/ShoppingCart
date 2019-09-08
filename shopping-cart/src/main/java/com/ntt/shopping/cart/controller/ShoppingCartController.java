package com.ntt.shopping.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.shopping.cart.service.ShoppingCartService;

@RestController
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@PostMapping(path = "/shoppingcart", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String processProductDetails(@RequestBody String cartInfo) {
		
		return shoppingCartService.processCart(cartInfo);
	}
}
