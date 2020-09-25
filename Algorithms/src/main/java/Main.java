package main.java;

        import main.java.implementation.BubbleSortImpl;
        import main.java.interfaces.BubbleSort;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter values:");

        List<Integer> input = Arrays.stream(bufferedReader.readLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());

        int[] values = fillArr(input);

        BubbleSort bubbleSort = new BubbleSortImpl(values);

        bubbleSort.sort();
        System.out.println(bubbleSort.toString());

    }

    private static int[] fillArr(List<Integer> input) {
        int [] values = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            values[i] = input.get(i);
        }
        return values;
    }
}
