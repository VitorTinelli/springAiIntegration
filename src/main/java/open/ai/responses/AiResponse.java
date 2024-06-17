package open.ai.responses;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AiResponse {

  @JsonProperty("request_id")
  private String requestId;

  @JsonProperty("workflow_asset_id")
  private String workflowAssetId;

  @JsonProperty("result")
  private Result result;

  @JsonProperty("all_components_log")
  private Map<String, ComponentLog> allComponentsLog;

  @JsonProperty("start_execution_time")
  private String startExecutionTime;

  @JsonProperty("end_execution_time")
  private String endExecutionTime;

  @JsonProperty("execution_time")
  private double executionTime;

  @Getter
  @Setter
  public static class Result {

    @JsonProperty("user_input")
    private String userInput;

    @JsonProperty("answer")
    private Map<String, String> answer;

    @JsonProperty("output_type")
    private String outputType;

    @JsonProperty("parsed_input")
    private List<ParsedInput> parsedInput;

    @JsonProperty("cost_track")
    private CostTrack costTrack;

    @JsonProperty("chat_history")
    private List<Object> chatHistory;
  }

  @Getter
  @Setter
  static class ParsedInput {

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
  }

  @Setter
  @Getter
  static class CostTrack {

    @JsonProperty("input_token_count")
    private int inputTokenCount;

    @JsonProperty("output_token_count")
    private int outputTokenCount;

    @JsonProperty("input_token_cost")
    private double inputTokenCost;

    @JsonProperty("output_token_cost")
    private double outputTokenCost;

    @JsonProperty("total_cost")
    private double totalCost;

  }

  @Setter
  @Getter
  static class ComponentLog {

    @JsonProperty("asset_id")
    private String assetId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("model_name")
    private String modelName;

    @JsonProperty("model_version")
    private String modelVersion;

    @JsonProperty("provider")
    private String provider;

    @JsonProperty("request_type")
    private List<String> requestType;

    @JsonProperty("model_params")
    private Map<String, Object> modelParams;

    @JsonProperty("input_token_count")
    private String inputTokenCount;

    @JsonProperty("output_token_count")
    private String outputTokenCount;

    @JsonProperty("input_token_cost")
    private String inputTokenCost;

    @JsonProperty("output_token_cost")
    private String outputTokenCost;

    @JsonProperty("total_cost")
    private String totalCost;

    @JsonProperty("start_execution_time")
    private String startExecutionTime;

    @JsonProperty("end_execution_time")
    private String endExecutionTime;

    @JsonProperty("execution_time")
    private String executionTime;

  }

}
