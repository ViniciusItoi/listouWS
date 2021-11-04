package com.listou.listou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listou.listou.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


}
	