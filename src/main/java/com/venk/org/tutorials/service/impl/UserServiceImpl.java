package com.venk.org.tutorials.service.impl;

import com.venk.org.tutorials.model.User;
import com.venk.org.tutorials.repository.UserRepository;
import com.venk.org.tutorials.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author venkateshkaradbhajne
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User getUserByName(String name) {
    return userRepository.findByName(name);
  }
}
