package com.homedepot.excel.upload.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.homedepot.excel.upload.dao.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Service
public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findUserById (String id);
	public List<User> findUserByName (String name);

}
