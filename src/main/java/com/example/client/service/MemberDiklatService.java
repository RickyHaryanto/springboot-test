package com.example.client.service;
import java.util.List;

import com.example.client.repository.MemberDiklatRepository;
import com.example.client.model.MemberDiklat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberDiklatService {
    @Autowired
	private MemberDiklatRepository repo;
	
	public List<MemberDiklat> listAll() {
		return repo.findAll();
	}

	
	public MemberDiklat get(long memberdiklat_id) {
		return repo.findById(memberdiklat_id).get();
	}

	public void save(MemberDiklat memberdiklat) {
		repo.save(memberdiklat);
	}
}