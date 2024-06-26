package open.ai.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import open.ai.domain.ConversationData;
import open.ai.repository.ConversationDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationDataService {

  private final ConversationDataRepository conversationDataRepository;

  public Optional<ConversationData> getConversationDataById(UUID id) {
    if (id == null) {
      return Optional.empty();
    }
    return conversationDataRepository.findById(id);
  }

  public void saveConversationData(String userMessage, String aiResponse) {
    conversationDataRepository.save(ConversationData.builder()
        .id(UUID.randomUUID())
        .userMessage(userMessage)
        .aiResponse(aiResponse)
        .build());
  }

  public void replaceConversationData(String userMessage, String aiResponse,
      ConversationData history) {
    conversationDataRepository.save(ConversationData.builder()
        .id(history.getId())
        .userMessage(history.getUserMessage() + userMessage)
        .aiResponse(history.getAiResponse() + aiResponse)
        .build());
  }

  public void deleteConversationData(UUID id) {
    conversationDataRepository.deleteById(id);
  }
}
