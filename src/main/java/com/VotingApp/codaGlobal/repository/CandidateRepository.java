package com.VotingApp.codaGlobal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VotingApp.codaGlobal.entity.Candidates;

@Repository
public interface CandidateRepository extends CrudRepository<Candidates, Integer>{

}
