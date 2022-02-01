package com.gunnerapis.cirbuyers.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gunnerapis.cirbuyers.entities.BuyerEntity;
import com.gunnerapis.cirbuyers.repositories.BuyerRepository;

@RestController
@RequestMapping(value = "/buyer")
public class BuyerController {

	@Autowired
	BuyerRepository buyerRepository;

	@GetMapping(value = "/findall")
	public ResponseEntity<List<BuyerEntity>> findAll() {
		List<BuyerEntity> list = buyerRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/findbyid")
	public ResponseEntity<BuyerEntity> findById(@RequestBody BuyerEntity buyerEntity) {
		if (buyerEntity.getId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		long id = buyerEntity.getId();
		Optional<BuyerEntity> has = buyerRepository.findById(buyerEntity.getId());
		if (has.isPresent()) {
			BuyerEntity buyerEntityNew = buyerRepository.findById(id).get();
			return ResponseEntity.ok(buyerEntityNew);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "/save")
	public ResponseEntity<BuyerEntity> save(@RequestBody BuyerEntity buyerEntity) {
		BuyerEntity buyerEntityNew = buyerRepository.save(buyerEntity);
		return ResponseEntity.ok(buyerEntityNew);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<BuyerEntity> update(@RequestBody BuyerEntity buyerEntity) {
		if (buyerEntity.getId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<BuyerEntity> has = buyerRepository.findById(buyerEntity.getId());
		if (has.isPresent()) {
			BuyerEntity buyerEntitySave = buyerRepository.save(buyerEntity);
			return ResponseEntity.ok(buyerEntitySave);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> deleteById(@RequestBody BuyerEntity buyerEntity) {
		if (buyerEntity.getId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		long id = buyerEntity.getId();
		Optional<BuyerEntity> has = buyerRepository.findById(buyerEntity.getId());
		if (has.isPresent()) {
			buyerRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
