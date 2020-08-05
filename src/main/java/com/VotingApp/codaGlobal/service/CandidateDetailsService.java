package com.VotingApp.codaGlobal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.VotingApp.codaGlobal.dto.RegisterCandidateDto;
import com.VotingApp.codaGlobal.dto.VoteRequest;
import com.VotingApp.codaGlobal.entity.Candidates;
import com.VotingApp.codaGlobal.entity.Users;

public interface CandidateDetailsService {

	public Users addNewCandidate(RegisterCandidateDto newCandidate);
	
	public List<Users> getAllCandidates();
	
	public Candidates getCandidateDetails(Integer candidateId);
	
	public Users loginUser(String username , String password);
	
	public Boolean castVote(VoteRequest voteRequest , HttpServletRequest request);

	public Boolean deleteCandidate(Integer candidateId ,Integer userId);

}
