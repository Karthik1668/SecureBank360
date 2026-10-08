package com.karthik.securebank360.service;

import com.karthik.securebank360.entity.User;

public interface UserService {
	User registerUser(User user);
	User findByEmail(String email);
	boolean emailExists(String email);

}
