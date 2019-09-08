package com.ntt.shopping.cart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntt.shopping.cart.entity.EventLog;

@Transactional
@Repository
public interface EventLogRepository extends JpaRepository<EventLog, String> {

}
