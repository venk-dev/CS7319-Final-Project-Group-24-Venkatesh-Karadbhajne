package com.venk.org.tutorials.repository;

import com.venk.org.tutorials.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author venkateshkaradbhajne
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findByName(String name);
}
