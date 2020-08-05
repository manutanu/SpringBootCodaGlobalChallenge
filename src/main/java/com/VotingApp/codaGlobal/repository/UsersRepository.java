package com.VotingApp.codaGlobal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VotingApp.codaGlobal.entity.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer>{
	
	public Users findByUserName(String userName);
	
}
