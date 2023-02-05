package com.glearning.students.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.glearning.students.dao.StudentRepository;
import com.glearning.students.dao.UserRepository;
import com.glearning.students.model.Role;
import com.glearning.students.model.Student;
import com.glearning.students.model.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootstrapAppData {
	
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void initializeData(ApplicationReadyEvent readyEvent) {
		
		Student kiran = new Student("kiran", "Kumar", "Java", "India");
		Student lingaraj = new Student("lingaraj ", "Prasad", "Python", "India");
		Student ganesh = new Student("ganesh", "Patil", "NodeJs", "India");
		Student shamanth = new Student("shamanth", "Patel", "Java", "India");
		
		this.studentRepository.save(kiran);
		this.studentRepository.save(lingaraj);
		this.studentRepository.save(ganesh);
		this.studentRepository.save(shamanth);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void initializeUsersData(ApplicationReadyEvent readyEvent) {
		
			User kiran = new User("kiran", passwordEncoder.encode("helloworld"));
			User vinay = new User("vinay", passwordEncoder.encode("helloworld"));
			
			Role userRole = new Role("ROLE_USER");
			Role adminRole = new Role("ROLE_ADMIN");
			
			kiran.addRole(userRole);
			
			vinay.addRole(userRole);
			vinay.addRole(adminRole);
			
			this.userRepository.save(kiran);
			this.userRepository.save(vinay);
		
	}

}
