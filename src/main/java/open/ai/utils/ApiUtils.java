package open.ai.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ApiUtils {

  public static Consumer<HttpHeaders> getJsonContentHeaders(String apiKey) {
    return headers -> {
      headers.setBearerAuth(apiKey);
      headers.setContentType(MediaType.APPLICATION_JSON);
    };
  }

  public static String getJsonContentBody(String message, String model) {
    Map<String, Object> map = new HashMap<>();
    map.put("workflow_id", model);
    map.put("query", message);
    map.put("is_persistence_allowed", "false");

    ObjectMapper objectMapper = new ObjectMapper();
    String json;
    try {
      json = objectMapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }
}
