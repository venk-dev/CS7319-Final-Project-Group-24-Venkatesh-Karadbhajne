package com.venk.org.tutorials.seeddata;

import com.venk.org.tutorials.model.User;
import com.venk.org.tutorials.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author venkateshkaradbhajne
 */
@Component
public class UserDataLoader implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    loadUserData();
  }

  private void loadUserData() {
    if (userRepository.count() == 0) {
      User user1 = new User("user1", "user1@testgmail.com", "USER");
      User user2 = new User("user2", "user2@testgmail.com", "USER");
      User admin = new User("admin", "admin@testgmail.com", "ADMIN");
      userRepository.save(user1);
      userRepository.save(user2);
      userRepository.save(admin);
    }
    System.out.println(userRepository.count());
  }
}
