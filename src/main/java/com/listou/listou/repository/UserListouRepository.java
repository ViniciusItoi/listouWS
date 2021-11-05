package com.listou.listou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listou.listou.entity.UserListou;

@Repository
public interface UserListouRepository  extends JpaRepository<UserListou, Long>{
	UserListou findByUsername(String Username);
}
