package com.VotingApp.codaGlobal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.VotingApp.codaGlobal.dto.JwtRequest;
import com.VotingApp.codaGlobal.dto.VoteRequest;
import com.VotingApp.codaGlobal.entity.Candidates;
import com.VotingApp.codaGlobal.entity.Users;
import com.VotingApp.codaGlobal.service.CandidateDetailsService;

@RestController
@CrossOrigin
public class UserDetailsController {
	
	@Autowired
	private CandidateDetailsService candidateDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody JwtRequest loginrequest){
		Users loggedInUser = candidateDetailsService.loginUser(loginrequest.getUsername(), loginrequest.getPassword());
		if(loggedInUser != null) 
			return new ResponseEntity<Users>(loggedInUser , HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<Error>(new Error("User not found"), HttpStatus.NOT_FOUND);
	}	

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>> getAllCandidates(){
		List<Users> usersList = candidateDetailsService.getAllCandidates();
		if(usersList != null)
			return new ResponseEntity<List<Users>>(usersList , HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<List<Users>>(usersList , HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getDetails/{candidateId}")
	public ResponseEntity<?> getDetailedCandidate(@PathVariable("candidateId") Integer candidateId){
		Candidates detail = candidateDetailsService.getCandidateDetails(candidateId);
		
		if(detail != null) {
			return new ResponseEntity<Candidates>(detail , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Error>(new Error("Bad request ") , HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/castVote")
	public ResponseEntity<?> castVote(@RequestBody VoteRequest voteRequest , HttpServletRequest request){
		Boolean flag = candidateDetailsService.castVote(voteRequest, request);
		if(flag != null && flag.booleanValue() == true) {
			return new ResponseEntity<Boolean>(true , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Error>(new Error("Cant cast vote ") , HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/deleteCandidate/{candidateId}/{userId}")
	public ResponseEntity<?> deleteCandidate(@PathVariable("candidateId") Integer candidateId , @PathVariable("userId") Integer userId){
		Boolean flag = candidateDetailsService.deleteCandidate(candidateId , userId);
		if(flag != null && flag.booleanValue() == true) {
			return new ResponseEntity<Boolean>(true , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Error>(new Error("Cant Delete Candidate ") , HttpStatus.BAD_REQUEST);
		}
	}
}
