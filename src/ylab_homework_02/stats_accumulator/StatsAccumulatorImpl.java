package ylab_homework_02.stats_accumulator;

import java.util.ArrayList;
import java.util.List;

public class StatsAccumulatorImpl implements StatsAccumulator {

    private final List<Integer> list = new ArrayList<>();

    @Override
    public void add(int value) {
        list.add(value);
    }

    @Override
    public int getMin() {
        return list.stream().min(Integer::compare).get();

    }

    @Override
    public int getMax() {
        return list.stream().max(Integer::compare).get();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Double getAvg() {
        return list.stream()
                .mapToInt(a -> a)
                .average().getAsDouble();
    }
}
