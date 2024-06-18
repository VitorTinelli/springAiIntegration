package open.ai.prompt;

public enum MessageType {

  USER("user"),

  ASSISTANT("assistant"),

  SYSTEM("system"),

  FUNCTION("function");

  private final String value;

  MessageType(String value) {
    this.value = value;
  }

  public static MessageType fromValue(String value) {
    for (MessageType messageType : MessageType.values()) {
      if (messageType.getValue().equals(value)) {
        return messageType;
      }
    }
    throw new IllegalArgumentException("Invalid MessageType value: " + value);
  }

  public String getValue() {
    return value;
  }

}
