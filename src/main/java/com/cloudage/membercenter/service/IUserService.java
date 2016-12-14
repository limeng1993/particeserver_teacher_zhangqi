package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.User;

public interface IUserService {
	User create(String account, String passwordHash);
	
	void login(String account, String passwordHash);
	User getCurrentUser();
	boolean changePassword(String newPasswordHash);
	void logout();

	User save(User user);
	User findByAccount(String account);
	User findByID(Integer id);
	User findByEmail(String email);
}
