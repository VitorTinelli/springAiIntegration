package open.ai.prompt;

public interface Message extends Content {

  MessageType getMessageType();

}