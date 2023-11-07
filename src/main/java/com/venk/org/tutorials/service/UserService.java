package com.venk.org.tutorials.service;

import com.venk.org.tutorials.model.User;
import org.springframework.stereotype.Component;

/**
 * @author venkateshkaradbhajne
 */
@Component
public interface UserService {

  public User createUser(User user);
  public User getUserByName(String name);
}
