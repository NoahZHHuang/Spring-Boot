package com.noah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noah.dao.AddressRepository;
import com.noah.domain.Address;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address addressToSave){
		return addressRepository.save(addressToSave);
	}
	
}
