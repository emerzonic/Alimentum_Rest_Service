package alimentum.alimentum.controller;

import alimentum.alimentum.entity.User;
import alimentum.alimentum.payload.JWTLoginSuccessResponse;
import alimentum.alimentum.payload.LoginRequest;
import alimentum.alimentum.security.JwtTokenProvider;
import alimentum.alimentum.service.MapValidationErrorService;
import alimentum.alimentum.service.UserService;
import alimentum.alimentum.util.Message;
import alimentum.alimentum.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static alimentum.alimentum.security.SecurityConstants.TOKEN_PREFIX;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

  private UserService userService;
  private JwtTokenProvider tokenProvider;
  private UserValidator userValidator;
  private AuthenticationManager authenticationManager;
  private MapValidationErrorService mapValidationErrorService;

  @Autowired
  public UserController(UserService userService,
                        JwtTokenProvider tokenProvider,
                        UserValidator userValidator,
                        AuthenticationManager authenticationManager,
                        MapValidationErrorService mapValidationErrorService
  ) {
    this.userService = userService;
    this.tokenProvider = tokenProvider;
    this.userValidator = userValidator;
    this.authenticationManager = authenticationManager;
    this.mapValidationErrorService = mapValidationErrorService;
  }


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User user,
                                        BindingResult result){

    // Validate passwords match
    userValidator.validate(user,result);

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if(errorMap != null)return errorMap;

    User newUser = userService.createUser(user);
    Message message = new Message("success","Welcome "+newUser.getUsername()+
                                                    ", you will have to be logged in to save recipes to " +
                                                    "favorites or view your favorite recipes.");
    return  new ResponseEntity<Message>(message, HttpStatus.CREATED);
  }



  @PostMapping("/login")
  public ResponseEntity<?> AuthenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                            BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
    if(errorMap != null)return errorMap;

    Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(auth);
    String jwt = TOKEN_PREFIX + tokenProvider.generateToken(auth);
    return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
  }
}
