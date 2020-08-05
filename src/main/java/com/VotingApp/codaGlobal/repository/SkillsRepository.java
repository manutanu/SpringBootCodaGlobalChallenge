package com.VotingApp.codaGlobal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VotingApp.codaGlobal.entity.Skills;

@Repository
public interface SkillsRepository extends CrudRepository<Skills, Integer>{

}
