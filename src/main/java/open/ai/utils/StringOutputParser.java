package open.ai.utils;

import open.ai.responses.AiResponse;

public class StringOutputParser {

  public static String parse(AiResponse response) {
    return response.getResult().getAnswer().values().toString().replace("[", "").replace("]", "");
  }

}
