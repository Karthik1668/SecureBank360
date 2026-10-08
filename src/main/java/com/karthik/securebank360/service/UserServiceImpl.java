package com.karthik.securebank360.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karthik.securebank360.entity.AccountType;
import com.karthik.securebank360.entity.Role;
import com.karthik.securebank360.entity.User;
import com.karthik.securebank360.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AccountService accountService;

	UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AccountService accountService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.accountService = accountService;
	}

	@Override
	@Transactional
	
	public User registerUser(User user) {

		if(emailExists(user.getEmail()))
		{
			throw new RuntimeException("Email is Already Registered: "+user.getEmail());
		}
		user.setRole(Role.CUSTOMER);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser=userRepository.save(user);

		accountService.createAccount(savedUser, AccountType.SAVINGS);

		return savedUser;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User Not Found with Email:"+email));
	}

	@Override
	public boolean emailExists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}
}