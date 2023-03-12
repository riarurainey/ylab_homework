package ylab_homework_02.sequences;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequencesImpl implements SequenceGenerator {

    @Override
    public void a(int n) {
        IntStream.iterate(2, i -> i + 2)
                .limit(n)
                .forEach(result -> System.out.print(result + " "));
    }

    @Override
    public void b(int n) {
        IntStream.iterate(1, i -> i + 2)
                .limit(n)
                .forEach(result -> System.out.print(result + " "));
    }

    @Override
    public void c(int n) {
        IntStream.iterate(1, i -> i + 1)
                .limit(n)
                .map(i -> i * i)
                .forEach(result -> System.out.print(result + " "));

    }

    @Override
    public void d(int n) {
        IntStream.iterate(1, i -> i + 1)
                .limit(n)
                .map(i -> i * i * i)
                .forEach(result -> System.out.print(result + " "));
    }

    @Override
    public void e(int n) {
        IntStream.generate(() -> -1)
                .limit(n)
                .forEach(result -> System.out.print(result + " "));

    }

    @Override
    public void f(int n) {
        IntStream.iterate(1, i -> i + 1)
                .map(i -> i % 2 == 0 ? -i : i)
                .limit(n)
                .forEach(result -> System.out.print(result + " "));
    }

    @Override
    public void g(int n) {
        IntStream.iterate(1, i -> i + 1)
                .map(i -> i * i)
                .map(i -> i % 2 == 0 ? -i : i)
                .limit(n)
                .forEach(result -> System.out.print(result + " "));
    }

    @Override
    public void h(int n) {
        IntStream.iterate(1, i -> i + 1)
                .flatMap(i -> IntStream.of(i, 0))
                .limit(n)
                .forEach(result -> System.out.print(result + " "));
    }

    @Override
    public void i(int n) {
        IntStream.rangeClosed(1, n)
                .map(i -> IntStream.rangeClosed(1, i).reduce(1, (a, b) -> a * b))
                .forEach(result -> System.out.print(result + " "));

    }

    @Override
    public void j(int n) {
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .map(i -> i[0])
                .limit(n)
                .forEach(result -> System.out.print(result + " "));
    }
}
