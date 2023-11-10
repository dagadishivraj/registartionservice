package com.microservices.bits.assignment.registerservice.userRepo;

import com.microservices.bits.assignment.registerservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    Optional<User> findById(String mobileno);
}
