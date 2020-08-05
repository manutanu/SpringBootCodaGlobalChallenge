package com.VotingApp.codaGlobal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="votes")
public class Votes {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer voteId;
	
	@Column(name="vote_ipaddress")
	private String voteIpAddress;
	
	@Column(name="is_registered_user")
	private Boolean isUserRegistered;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="vote_candidate_id")
	private Candidates candidate;

	public Integer getVoteId() {
		return voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public String getVoteIpAddress() {
		return voteIpAddress;
	}

	public void setVoteIpAddress(String voteIpAddress) {
		this.voteIpAddress = voteIpAddress;
	}

	public Boolean getIsUserRegistered() {
		return isUserRegistered;
	}

	public void setIsUserRegistered(Boolean isUserRegistered) {
		this.isUserRegistered = isUserRegistered;
	}

	public Candidates getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidates candidate) {
		this.candidate = candidate;
	}
	
	
}
