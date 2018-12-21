package alimentum.alimentum.util;

public class Message {
  private String text;
  private String type;


  public Message(String type, String text) {
    this.text = text;
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
