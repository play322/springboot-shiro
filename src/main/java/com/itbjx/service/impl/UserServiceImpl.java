package com.itbjx.service.impl;

import com.itbjx.domain.User;
import com.itbjx.mapper.UserMapper;
import com.itbjx.service.UserSerice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserSerice {

	@Resource
	private UserMapper userMapper;

	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}
}
