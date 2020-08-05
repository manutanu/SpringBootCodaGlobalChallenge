package com.VotingApp.codaGlobal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Candidates {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer candidateId;
	
	@Column(name="candidate_chall_solved")
	private Integer candidateChallSolved;
	
	@Column(name="candidate_exp_level")
	private Integer candidateExperLevel;
	
	@OneToMany(mappedBy = "candidate" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<Skills> candidateSkills;
	
	@OneToMany(mappedBy = "candidate" ,fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<Votes> candidateVotes;

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public Integer getCandidateChallSolved() {
		return candidateChallSolved;
	}

	public void setCandidateChallSolved(Integer candidateChallSolved) {
		this.candidateChallSolved = candidateChallSolved;
	}

	public Integer getCandidateExperLevel() {
		return candidateExperLevel;
	}

	public void setCandidateExperLevel(Integer candidateExperLevel) {
		this.candidateExperLevel = candidateExperLevel;
	}

	public List<Skills> getCandidateSkills() {
		return candidateSkills;
	}

	public void setCandidateSkills(List<Skills> candidateSkills) {
		this.candidateSkills = candidateSkills;
	}

	public List<Votes> getCandidateVotes() {
		return candidateVotes;
	}

	public void setCandidateVotes(List<Votes> candidateVotes) {
		this.candidateVotes = candidateVotes;
	}

	@Override
	public String toString() {
		return "Candidates [candidateId=" + candidateId + ", candidateChallSolved=" + candidateChallSolved
				+ ", candidateExperLevel=" + candidateExperLevel + ", candidateSkills=" + candidateSkills
				+ ", candidateVotes=" + candidateVotes + "]";
	}
	
	
	
}
