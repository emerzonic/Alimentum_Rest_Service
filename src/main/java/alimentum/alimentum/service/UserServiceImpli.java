package alimentum.alimentum.service;

import alimentum.alimentum.entity.User;
import alimentum.alimentum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpli implements UserService {
  private UserRepository userRepository;

  @Autowired
  public UserServiceImpli(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public User getUser() {
    String username = "emerson";
    return userRepository.findById(username).orElse(null);
  }

  @Override
  @Transactional
  public void createUser(User user) {
    userRepository.save(user);

  }
}
