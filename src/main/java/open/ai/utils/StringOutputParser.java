package open.ai.utils;

import lombok.experimental.UtilityClass;
import open.ai.responses.AiResponse;

@UtilityClass
public class StringOutputParser {

  public static String parse(AiResponse response) {
    return response.getResult().getAnswer().values().toString().replace("[", "").replace("]", "");
  }

}
