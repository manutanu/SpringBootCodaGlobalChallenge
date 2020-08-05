DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS candidates;

	
	CREATE TABLE roles (
			role_id INT PRIMARY KEY ,
			role_name VARCHAR(250) NOT NULL 
		);
	
	INSERT INTO roles (role_id , role_name ) VALUES (1 , 'ADMIN');
	INSERT INTO roles (role_id , role_name ) VALUES (2 , 'CANDIDATE');
	
	CREATE TABLE candidates (
		candidate_id INT AUTO_INCREMENT PRIMARY KEY,
		candidate_chall_solved INT ,
		candidate_exp_level INT 
	);
	
	CREATE TABLE users (
		user_id INT AUTO_INCREMENT PRIMARY KEY ,
		user_name VARCHAR(250) NOT NULL ,
		user_password varchar(250) NOT NULL ,
		user_role_id INT NOT NULL ,
		candidate_user_id INT ,
		user_voted boolean ,
		FOREIGN KEY (user_role_id) REFERENCES roles(role_id),
		FOREIGN KEY (candidate_user_id) REFERENCES candidates(candidate_id)
	);
	
	INSERT INTO users (user_name , user_password , user_role_id , user_voted ) VALUES ('admin' , 'admin' , 1 , false );
	
	CREATE TABLE skills (
		skill_id INT AUTO_INCREMENT PRIMARY KEY ,
		skill_name VARCHAR(250) NOT NULL,
		skill_exp_level INT NOT NULL ,
		skill_candidate_id INT ,
		FOREIGN KEY (skill_candidate_id) REFERENCES candidates(candidate_id)
	);
	
	CREATE TABLE votes (
		vote_id INT AUTO_INCREMENT PRIMARY KEY ,
		vote_ipaddress VARCHAR(250) ,
		is_registered_user boolean ,
		vote_candidate_id INT NOT NULL ,
		FOREIGN KEY (vote_candidate_id) REFERENCES candidates(candidate_id)
	);
	
	
	 