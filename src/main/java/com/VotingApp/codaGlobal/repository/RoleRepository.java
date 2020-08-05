package com.VotingApp.codaGlobal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VotingApp.codaGlobal.entity.Roles;

@Repository
public interface RoleRepository extends CrudRepository<Roles, Integer>{
	
}
