package alimentum.alimentum.service;

import alimentum.alimentum.entity.User;
import alimentum.alimentum.exceptions.UsernameAlreadyExistsException;
import alimentum.alimentum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpli implements UserService {
  private UserRepository userRepository;
  private PasswordEncoder encoder;

  @Autowired
  public UserServiceImpli(UserRepository userRepository,
                          PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  @Override
  @Transactional
  public User getUser(Long userId) {
    return userRepository.getById(userId);
  }


  @Override
  @Transactional
  public User createUser(User newUser) {
    try{
      newUser.setPassword(encoder.encode(newUser.getPassword()));
      newUser.setUsername(newUser.getUsername());
      // reset the confirmPassword
      newUser.setConfirmPassword("");
      return userRepository.save(newUser);
    }catch (Exception e){
      throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists!");
    }
  }

  @Override
  @Transactional
  public Boolean checkIfUserExist(String username) {
    return userRepository.existsUsersByUsername(username);
  }

  @Override
  @Transactional
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
