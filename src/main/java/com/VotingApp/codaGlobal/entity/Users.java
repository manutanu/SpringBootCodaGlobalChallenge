package com.VotingApp.codaGlobal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Column(name ="user_name")
	private String userName;
	
	@Column(name="user_password")
	private String userPassword;
	
	@OneToOne
	@JoinColumn(name = "user_role_id")
	private Roles role;
	
	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JoinColumn(name="candidate_user_id")
	private Candidates candidate;
	
	@Column(name="user_voted")
	private Boolean userVoted;
	
	@Transient
	private Integer votesCount;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Candidates getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidates candidate) {
		this.candidate = candidate;
	}

	public Boolean getUserVoted() {
		return userVoted;
	}

	public void setUserVoted(Boolean userVoted) {
		this.userVoted = userVoted;
	}

	public Integer getVotesCount() {
		return votesCount;
	}

	public void setVotesCount(Integer votesCount) {
		this.votesCount = votesCount;
	}
	
	
	
}
