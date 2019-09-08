package com.ntt.shopping.cart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.shopping.cart.entity.ShoppingCart;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ShoppingCart, String> {

	
}