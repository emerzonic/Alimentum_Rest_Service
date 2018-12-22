package alimentum.alimentum.service;

import alimentum.alimentum.entity.Role;
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

//  @Override
  @Transactional
  public User createUser1(User user) {
    try {
      user.setPassword(encoder.encode(user.getPassword()));
      Role userRole = new Role("ADMIN");
      user.addRole(userRole);
      return userRepository.save(user);
    }catch (Exception e){
      throw new UsernameAlreadyExistsException("User with user '"+ user.getUsername()+ "' already exists!");
    }
  }


  @Override
  @Transactional
  public User createUser(User newUser) {
    try{
      newUser.setPassword(encoder.encode(newUser.getPassword()));
      //Username has to be unique (exception)
      newUser.setUsername(newUser.getUsername());
      // Make sure that password and confirmPassword match
      newUser.setConfirmPassword("");
      return userRepository.save(newUser);

    }catch (Exception e){
      throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
    }
  }

  @Override
  @Transactional
  public Boolean checkIfUserExist(String username) {
    return userRepository.existsUsersByUsername(username);
  }
}
