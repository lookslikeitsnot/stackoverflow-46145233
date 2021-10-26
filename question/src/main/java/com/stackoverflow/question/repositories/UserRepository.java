package com.stackoverflow.question.repositories;

import com.stackoverflow.question.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
