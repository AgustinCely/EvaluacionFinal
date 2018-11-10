package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.SignosVitales;

public interface ISignosVitalesDAO extends JpaRepository<SignosVitales, Integer>{

}
