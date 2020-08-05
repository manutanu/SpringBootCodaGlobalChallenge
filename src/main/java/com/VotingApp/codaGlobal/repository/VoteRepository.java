package com.VotingApp.codaGlobal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.VotingApp.codaGlobal.entity.Votes;

@Repository
public interface VoteRepository extends CrudRepository<Votes, Integer>{

	public Votes findVotesByVoteIpAddress(String voteIpAddress);
}
