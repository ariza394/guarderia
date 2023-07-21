package com.ciao.userservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ciao.userservice.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
