package alimentum.alimentum.service;

import alimentum.alimentum.entity.User;



public interface UserService {

  User getUser(Long userId);
  User findByUsername(String username);
  User createUser(User user);
  Boolean checkIfUserExist(String username);
}
