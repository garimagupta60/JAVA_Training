package notification.functional;

@FunctionalInterface
public interface Converter<T, R> {

    R convert(T input);
}
