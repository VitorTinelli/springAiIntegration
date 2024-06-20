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
        eg: `foo, bar, baz`
        Don´t use symbols like `[]` or `{}` or `/|` or  `**` .
        """;
  }

  @Override
  public List<String> parse(String text) {
    return getConversionService().convert(text, List.class);
  }
}
