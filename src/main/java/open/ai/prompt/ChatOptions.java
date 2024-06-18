package open.ai.prompt;

public interface ChatOptions extends ModelOptions {

  Float getTemperature();

  Float getTopP();

  Integer getTopK();

}
