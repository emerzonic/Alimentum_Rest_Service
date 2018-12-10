package alimentum.alimentum;


import alimentum.alimentum.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlimentumApplicationTests {
	@Autowired
	private UserService userService;



	@Test
	public void contextLoads() {
//		User testUser = new User("emerson","pass");
//		userService.createUser(testUser);
	}

}
