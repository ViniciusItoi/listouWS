package com.listou.listou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listou.listou.entity.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long>{

}
