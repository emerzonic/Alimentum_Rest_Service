package alimentum.alimentum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlimentumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlimentumApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/user/**").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}
}

