package open.ai.config;

import java.util.Set;
import java.util.UUID;
import open.ai.domain.ConversationData;
import open.ai.prompt.Prompt;
import open.ai.repository.ConversationDataRepository;
import open.ai.responses.AiResponse;
import open.ai.service.ConversationDataService;
import open.ai.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

@Service
public class AiApi {

  private final RestClient restClient;
  private final ConversationDataService conversationDataService;
  private final ConversationDataRepository conversationDataRepository;
  private final String persistence;
  private final String model;

  public AiApi(Builder restClientBuilder,
      ConversationDataService conversationDataService,
      ConversationDataRepository conversationDataRepository,
      @Value("${spring.tinelli.ai.url}") String baseUrl,
      @Value("${spring.tinelli.ai.token}") String token,
      @Value("${spring.tinelli.ai.model}") String modelValue,
      @Value("${spring.tinelli.ai.persistence}") String persistenceValue) {
    this.conversationDataService = conversationDataService;
    this.conversationDataRepository = conversationDataRepository;
    this.model = modelValue;
    this.persistence = persistenceValue;
    this.restClient = restClientBuilder
        .baseUrl(baseUrl)
        .defaultHeaders(ApiUtils.getJsonContentHeaders(token))
        .build();
  }

  public AiResponse returnResponse(UUID id, String message) {
    ConversationData history = null;
    if (id != null) {
      history = conversationDataService.getConversationDataById(id);
    }
    AiResponse response = this.restClient.post()
        .uri("/v1/inference")
        .body(ApiUtils.getJsonContentBody(message, model, history, persistence))
        .retrieve()
        .toEntity(AiResponse.class)
        .getBody();
    if (persistence.equals("true") && history != null) {
      conversationDataRepository.save(ConversationData.builder()
          .id(history.getId())
          .userMessage(history.getUserMessage() + message)
          .aiResponse(
              history.getAiResponse() + response.getResult().getAnswer().values().toString()
                  .replace("[", "").replace("]", ""))
          .build());
    } else if (persistence.equals("true")) {
      conversationDataService.saveConversationData(message,
          response.getResult().getAnswer().values().toString().replace("[", "").replace("]", ""));
    }
    return response;
  }

  public AiResponse returnResponse(UUID id, Prompt message) {
    ConversationData history = null;
    if (id != null) {
      history = conversationDataService.getConversationDataById(id);
    }
    AiResponse response = this.restClient.post()
        .uri("/v1/inference")
        .body(ApiUtils.getJsonContentBody(message.toString(), model, history, persistence))
        .retrieve()
        .toEntity(AiResponse.class)
        .getBody();
    if (persistence.equals("true") && history != null) {
      conversationDataRepository.save(ConversationData.builder()
          .id(history.getId())
          .userMessage(history.getUserMessage() + message)
          .aiResponse(
              history.getAiResponse() + response.getResult().getAnswer().values().toString()
                  .replace("[", "").replace("]", ""))
          .build());
    } else if (persistence.equals("true")) {
      conversationDataService.saveConversationData(message.toString(),
          response.getResult().getAnswer().values().toString().replace("[", "").replace("]", ""));
    }
    return response;
  }

  public Set getModels() {
    return this.restClient.get()
        .uri("/v2/workflow")
        .retrieve()
        .toEntity(Set.class)
        .getBody();
  }

  public Set getAllModels() {
    return this.restClient.get()
        .uri("/v2/workflow?show_all=true")
        .retrieve()
        .toEntity(Set.class)
        .getBody();
  }
}

