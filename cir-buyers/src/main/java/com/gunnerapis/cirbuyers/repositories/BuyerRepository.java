package com.gunnerapis.cirbuyers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gunnerapis.cirbuyers.entities.BuyerEntity;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Long> {

}
