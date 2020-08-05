package com.VotingApp.codaGlobal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.VotingApp.codaGlobal.dto.RegisterCandidateDto;
import com.VotingApp.codaGlobal.entity.Users;
import com.VotingApp.codaGlobal.service.CandidateDetailsService;

@RestController
@CrossOrigin
public class UserRegisterAndLoginController {
	
	@Autowired
	private CandidateDetailsService candidateDetailsService;

	@PostMapping("/newcandidate")
	public ResponseEntity<?> createNewCandidate(@RequestBody RegisterCandidateDto newCandidate) throws Exception{
		
		Users iscreated = candidateDetailsService.addNewCandidate(newCandidate);
		
		if(iscreated != null) {
			return new ResponseEntity<Users>(iscreated,HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Error>(new Error("User cant be saved right now "),HttpStatus.BAD_REQUEST);
		}
		
	}
}
