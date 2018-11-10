package com.mitocode.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.ISignosVitalesDAO;
import com.mitocode.model.SignosVitales;
import com.mitocode.service.ISignosVitalesService;

@Service
public class SignosVitalesServiceImpl implements ISignosVitalesService{

	@Autowired
	private ISignosVitalesDAO dao;
	
	@Override
	public SignosVitales registrar(SignosVitales t) {
		return dao.save(t);
	}

	@Override
	public SignosVitales modificar(SignosVitales t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public SignosVitales listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<SignosVitales> listar() {
		return dao.findAll();
	}

	@Override
	public Page<SignosVitales> listarPageable(Pageable pageable) {
		return dao.findAll(pageable);
	}

}
