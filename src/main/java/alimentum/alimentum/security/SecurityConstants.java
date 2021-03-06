package alimentum.alimentum.security;

public class SecurityConstants {

  public static final String AUTH_URLS = "/api/users/**";
  public static final String EXTERNAL_API_URLS = "/api/recipes/**";
  public static final String SECRET ="SecretKeyToGenJWTs";
  public static final String TOKEN_PREFIX= "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final long EXPIRATION_TIME = 86_400; //a day
}
