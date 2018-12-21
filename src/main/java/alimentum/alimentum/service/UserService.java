package alimentum.alimentum.service;

import alimentum.alimentum.entity.User;



public interface UserService {

  User getUser(String username);
  User createUser(User user);
  Boolean checkIfUserExist(String username);
}
