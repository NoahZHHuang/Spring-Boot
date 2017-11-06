package com.noah.dao;

import org.springframework.data.repository.CrudRepository;

import com.noah.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
