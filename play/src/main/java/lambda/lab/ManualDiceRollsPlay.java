package lambda.lab;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 蒙特卡洛模拟 掷塞子
 *
 * Created by hongkai on 2017/3/22.
 */
public class ManualDiceRollsPlay {

    public static void main(String args[]) {
        Map<Integer, Double> result = parallelDiceRolls(1000000000);
        result.forEach((count, rate) -> System.out.println(count + "  -> " + rate));
    }

    public static Map<Integer, Double> parallelDiceRolls(int n){
        double fraction = 1.0/n;
        return IntStream.range(0, n)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(Collectors.groupingBy(side -> side,
                        Collectors.summingDouble(p -> fraction)));
    }


    private static IntFunction<Integer> twoDiceThrows() {
        return i -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int firstThrow = random.nextInt(1, 7);
            int secondThrow = random.nextInt(1, 7);
            return firstThrow + secondThrow;
        };
    }
}
