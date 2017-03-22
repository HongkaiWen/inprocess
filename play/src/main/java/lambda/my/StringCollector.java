package lambda.my;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by hongkai on 2017/3/16.
 */
public class StringCollector implements Collector<String, StringCombiner, String> {

    private String delim;
    private String prefix;
    private String suffix;


    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(delim, prefix, suffix);
    }

    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }

    public StringCollector(String delim, String prefix, String suffix) {
        this.delim = delim;
        this.prefix = prefix;
        this.suffix = suffix;
    }
}
