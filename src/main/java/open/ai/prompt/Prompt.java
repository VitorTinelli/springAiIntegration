package open.ai.prompt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Prompt {

  private final List<Message> messages;

  public Prompt(String contents) {
    this(new UserMessage(contents));
  }

  public Prompt(Message message) {
    this(Collections.singletonList(message));
  }

  public Prompt(List<Message> messages) {
    this.messages = messages;
  }


  @Override
  public String toString() {
    return "Prompt{" + "messages=" + this.messages + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Prompt prompt)) {
      return false;
    }
    return Objects.equals(this.messages, prompt.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.messages);
  }

  public Prompt copy() {
    return new Prompt(instructionsCopy());
  }

  private List<Message> instructionsCopy() {
    List<Message> messagesCopy = new ArrayList<>();
    this.messages.forEach(message -> {
      if (message instanceof UserMessage) {
        messagesCopy.add(
            new UserMessage(message.getContent(), message.getMedia(), message.getMetadata()));
      } else if (message instanceof SystemMessage) {
        messagesCopy.add(new SystemMessage(message.getContent()));
      } else if (message instanceof AssistantMessage) {
        messagesCopy.add(new AssistantMessage(message.getContent(), message.getMetadata()));
      } else if (message instanceof FunctionMessage) {
        messagesCopy.add(new FunctionMessage(message.getContent(), message.getMetadata()));
      } else {
        throw new IllegalArgumentException(
            "Unsupported message type: " + message.getClass().getName());
      }
    });

    return messagesCopy;
  }

}
