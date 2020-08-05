package com.VotingApp.codaGlobal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.VotingApp.codaGlobal.dto.RegisterCandidateDto;
import com.VotingApp.codaGlobal.dto.SkillsDto;
import com.VotingApp.codaGlobal.dto.VoteRequest;
import com.VotingApp.codaGlobal.entity.Candidates;
import com.VotingApp.codaGlobal.entity.Roles;
import com.VotingApp.codaGlobal.entity.Skills;
import com.VotingApp.codaGlobal.entity.Users;
import com.VotingApp.codaGlobal.entity.Votes;
import com.VotingApp.codaGlobal.repository.CandidateRepository;
import com.VotingApp.codaGlobal.repository.RoleRepository;
import com.VotingApp.codaGlobal.repository.SkillsRepository;
import com.VotingApp.codaGlobal.repository.UsersRepository;
import com.VotingApp.codaGlobal.repository.VoteRepository;

@Service
public class CandidateDetailsServiceImpl implements CandidateDetailsService {

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private SkillsRepository skillsRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	
	@Override
	@Transactional
	public Users addNewCandidate(RegisterCandidateDto newCandidate) {
		Users datasaved = null;
		try {
			
			Candidates newRegisteration = new Candidates();
			newRegisteration.setCandidateChallSolved(newCandidate.getChallenges_solved());
			newRegisteration.setCandidateExperLevel(newCandidate.getExpertise_level());
			newRegisteration = candidateRepository.save(newRegisteration);
			
			List<Skills> skillSet = new ArrayList<>();
			
			for(SkillsDto skill : newCandidate.getSkills()){ 
				Skills newskill = new Skills();
				newskill.setSkillName(skill.getSkillName());
				newskill.setSkillExpertLevel(skill.getSkillExpertLevel());
				newskill.setCandidate(newRegisteration);
				skillsRepository.save(newskill);
			}
			
			
			Users newUser = new Users();
			newUser.setCandidate(newRegisteration);
			newUser.setUserName(newCandidate.getCandidateName());
			newUser.setUserPassword(newCandidate.getPassword());
			newUser.setUserVoted(false);
			// give this user a CANDIDATE role 
			Optional<Roles> optionalrole = roleRepository.findById(2);
			if(optionalrole.isPresent())
				newUser.setRole(optionalrole.get());
			
			datasaved = userRepository.save(newUser);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return datasaved;
	}
	
	@Override
	public List<Users> getAllCandidates(){
		List<Users> usersList = null;
		try {
			List<Users> tempList = (List<Users>)userRepository.findAll();
			usersList = new ArrayList<>();
			for(Users user : tempList) {
				if(user.getCandidate() != null && user.getCandidate().getCandidateVotes() != null && user.getCandidate().getCandidateVotes().size() > 0) {
					user.setVotesCount(user.getCandidate().getCandidateVotes().size());
				}
				
				if(user.getRole().getRoleId() != 1) {
					usersList.add(user);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}


	@Override
	public Candidates getCandidateDetails(Integer candidateId) {
		Candidates detailedCandidate = null;
		try {
			Optional<Candidates> candOption = candidateRepository.findById(candidateId);
			System.out.println("here");
			if(candOption.isPresent()) {
				detailedCandidate = candOption.get();
				System.out.println(detailedCandidate.getCandidateChallSolved() + "  "+ detailedCandidate.getCandidateSkills().size());
				detailedCandidate.getCandidateSkills().forEach(skill -> {
					System.out.println(skill.getSkillName());
				});
			}else {
				System.out.println("no data found");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return detailedCandidate;
	}

	@Override
	public Users loginUser(String username , String password) {
		Users loggedInuser = null;
		try {
			Users opuser = userRepository.findByUserName(username);
			if(opuser != null) {
				if(opuser.getUserPassword().equals(password)) {
					loggedInuser = opuser;
					loggedInuser.setCandidate(null);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return loggedInuser;
	}

	@Override
	@Transactional
	public Boolean castVote(VoteRequest voteRequest, HttpServletRequest request) {
		Boolean isVoteCasted = Boolean.FALSE;
		try {
			if(voteRequest.getUserId() == -1) { // general vote 
				System.out.println(request.getRemoteAddr());
				Votes vote = voteRepository.findVotesByVoteIpAddress(request.getRemoteAddr() == null || request.getRemoteAddr().equals("") ? request.getLocalAddr() : request.getRemoteAddr());
				if(vote == null) { // still can cast vote on this IP address
					Optional<Candidates> opcandidate = candidateRepository.findById(voteRequest.getCandidateId());
					if(opcandidate.isPresent()) {
						Candidates candidate  = opcandidate.get();
						Votes newVote = new Votes();
						newVote.setCandidate(candidate);
						newVote.setIsUserRegistered(false);
						newVote.setVoteIpAddress(request.getRemoteAddr() == null || request.getRemoteAddr().equals("") ? request.getLocalAddr() : request.getRemoteAddr());
						voteRepository.save(newVote);
						isVoteCasted = Boolean.TRUE;
					}
				}else {
					isVoteCasted = Boolean.FALSE;
				}
			}else { // that means user is registered and wants to vote 
				Optional<Users> opuser = userRepository.findById(voteRequest.getUserId());
				if(opuser.isPresent()) {
					Users user = opuser.get();
					Optional<Candidates> opcandidate = candidateRepository.findById(voteRequest.getCandidateId());
					if(opcandidate.isPresent()) {
						Candidates candidate  = opcandidate.get();
						Votes newVote = new Votes();
						newVote.setCandidate(candidate);
						newVote.setIsUserRegistered(true);
						voteRepository.save(newVote);
						user.setUserVoted(true);
						user = userRepository.save(user);
						isVoteCasted = Boolean.TRUE;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isVoteCasted;
	}

	@Override
	@Transactional
	public Boolean deleteCandidate(Integer candidateId , Integer userId) {
		Boolean isDeleted = Boolean.FALSE;
		try {
			userRepository.deleteById(userId);
//			candidateRepository.deleteById(candidateId);
			isDeleted = Boolean.TRUE;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

}
