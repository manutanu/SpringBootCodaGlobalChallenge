///*
//* JwtUserDetailsService
//*  This class contains a method which uses DB to authenticate User details 
//*
//* 1.0
//*
//* @authored by Mritunjay Yadav
//*/
//
//
//package com.VotingApp.codaGlobal.security;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.VotingApp.codaGlobal.constants.ConstantUtils;
//import com.VotingApp.codaGlobal.entity.Roles;
//import com.VotingApp.codaGlobal.entity.Users;
//import com.VotingApp.codaGlobal.repository.UsersRepository;
//
////import com.VersionDriveBackend.entity.UserStuff;
////import com.VersionDriveBackend.repository.UserRepository;
//
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService,ConstantUtils {
//
///*	@Autowired
//*	private PasswordEncoder obj;
//*/
//
//	@Autowired
//	private UsersRepository userRepository;
//
//	
//	/**
//	 * @Description  loadUserByUsername method from UserDetailsService is @Overrided
//	 * 
//	 * @Author Mritunjay Yadav
//	 * @return UserDetails
//	 * @param String username
//	 * @Exception UsernameNotFoundException
//	 * 
//	 * */
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		//fetching userobject from database using username and activated flag
//		Users userstuff = userRepository.findByUserName(username);
//
//		if (userstuff!=null) {
//			Roles role = userstuff.getRole();
//			List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
//		    authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//		    
//		     
//			//if userobject found in the database than return User object 
//			return new User(userstuff.getUserName(), userstuff.getUserPassword() , authorities);
//		
//		} else {
//			
//			//if not found throw UsernameNotFoundException
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		
//		}
//		 
//	}
//
//}