package com.itbjx.service;

import com.itbjx.domain.User;

public interface UserSerice {

	public User findByName(String name);
	public User findById(Integer id);
}
