package com.itbjx.mapper;

import com.itbjx.domain.User;

public interface UserMapper {

	public User findByName(String name);
	
	public User findById(Integer id);
}
