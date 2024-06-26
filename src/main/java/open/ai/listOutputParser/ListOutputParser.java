package open.ai.listOutputParser;

import java.util.List;
import org.springframework.core.convert.support.DefaultConversionService;

public class ListOutputParser extends AbstractConversionServiceOutputParser<List<String>> {

  public ListOutputParser(DefaultConversionService defaultConversionService) {
    super(defaultConversionService);
  }

  @Override
  public String getFormat() {
    return """
        Your response should be a list of comma separated values.
        If you will return a list, don´t return instructions like: 'My name is... or any other text. Just return the list of values separated by commas.
        eg: `foo, bar, baz`
        """;
  }

  @Override
  public List<String> parse(String text) {
    return getConversionService().convert(text, List.class);
  }
}
