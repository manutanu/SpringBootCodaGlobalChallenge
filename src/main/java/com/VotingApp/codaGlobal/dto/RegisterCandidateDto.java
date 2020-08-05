package com.VotingApp.codaGlobal.dto;

import java.util.List;

public class RegisterCandidateDto {
	
	private String candidateName;
	private Integer challenges_solved;
	private String password;
	private Integer expertise_level;
	
	private List<SkillsDto> skills ;
	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Integer getChallenges_solved() {
		return challenges_solved;
	}
	public void setChallenges_solved(Integer challenges_solved) {
		this.challenges_solved = challenges_solved;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getExpertise_level() {
		return expertise_level;
	}
	public void setExpertise_level(Integer expertise_level) {
		this.expertise_level = expertise_level;
	}
	public List<SkillsDto> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillsDto> skills) {
		this.skills = skills;
	}
	
		

}
