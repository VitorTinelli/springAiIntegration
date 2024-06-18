package open.ai.prompt;

public interface ModelRequest<T> {

  /**
   * Retrieves the instructions or input required by the AI model.
   *
   * @return the instructions or input required by the AI model
   */
  T getInstructions(); // required input

  /**
   * Retrieves the customizable options for AI model interactions.
   *
   * @return the customizable options for AI model interactions
   */
  ModelOptions getOptions();

}
