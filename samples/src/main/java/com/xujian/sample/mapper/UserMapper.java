package com.xujian.sample.mapper;

import com.xujian.sample.model.User;

import java.util.List;

public interface UserMapper {

	void insertUser(User user);

	List<User> selectUsersAll();

	User selectUsersById(Integer id);

	void updateUser(User user);

	void deleteUserById(Integer id);
}
