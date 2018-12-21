package alimentum.alimentum.exceptions;


public class InvalidLoginResponse {
  private String credentials;

  public InvalidLoginResponse() {
    this.credentials = "Invalid username and or password!";
  }

  public String getCredentials() {
    return credentials;
  }


  public void setCredentials(String credentials) {
    this.credentials = credentials;
  }
}
