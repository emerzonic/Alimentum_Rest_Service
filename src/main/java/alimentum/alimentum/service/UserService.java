package alimentum.alimentum.service;

import alimentum.alimentum.entity.User;

public interface UserService {

  User getUser();
  void createUser(User user);
}
