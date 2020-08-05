package com.VotingApp.codaGlobal.entity;

import javax.persistence.CascadeType;
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
@Table(name="skills")
public class Skills {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer skillId;
	
	@Column(name="skill_name")
	private String skillName;
	
	@Column(name="skill_exp_level")
	private Integer skillExpertLevel;
	
	@ManyToOne(targetEntity= Candidates.class , cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JsonBackReference
	@JoinColumn(name="skill_candidate_id")
	private Candidates candidate;

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Integer getSkillExpertLevel() {
		return skillExpertLevel;
	}

	public void setSkillExpertLevel(Integer skillExpertLevel) {
		this.skillExpertLevel = skillExpertLevel;
	}

	public Candidates getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidates candidate) {
		this.candidate = candidate;
	}
	
	
}
