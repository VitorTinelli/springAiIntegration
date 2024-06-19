package open.ai.listOutputParser;

@FunctionalInterface
public interface Parser<T> {

  T parse(String text);
}