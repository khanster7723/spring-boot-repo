package com.spring.cassandra.api.repository;

import java.io.Serializable;
//import java.util.List;
import java.util.Optional;

import com.spring.cassandra.api.model.User;

//import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Serializable> {

	//Optional<User> findByAgeGreaterThan(int age);
	Optional<User> findById(int id);
    
}