package alimentum.alimentum.service;

import alimentum.alimentum.entity.User;

public interface UserService {

  User getUser(String username);
  void createUser(User user);
}
