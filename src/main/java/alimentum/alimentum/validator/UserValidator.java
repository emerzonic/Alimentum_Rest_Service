package alimentum.alimentum.validator;

import alimentum.alimentum.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
  @Override
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  @Override
  public void validate(Object obj, Errors errors) {
    User user = (User) obj;
    if(user.getPassword().length() < 4){
        errors.rejectValue("password","Length","Password cannot be less than four(4) characters!");
    }
    if(!user.getPassword().equals(user.getConfirmPassword())){
        errors.rejectValue("confirmPassword","Match","Passwords don't match!");
    }

  }
}
