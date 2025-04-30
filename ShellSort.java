import java.io.*;
import java.util.*;

public class ShellSort {

    private static long iterations = 0;

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    iterations++;
                }
                arr[j] = temp;
                iterations++;
            }
        }
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000); 
        }
        return arr;
    }

    public static void saveArrayToFile(int[] arr, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : arr) {
                writer.write(num + " ");
            }
        }
    }

    public static int[] readArrayFromFile(String filename) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) throws IOException {

        String resultsFile = "results.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFile))) {
            writer.write("Size,Time(ns),Iterations");

            for (int size = 100; size <= 10000; size += 100) {
                int[] arr = generateRandomArray(size);
                String inputFile = "input_" + size + ".txt";
                saveArrayToFile(arr, inputFile);

                int[] data = readArrayFromFile(inputFile);
                iterations = 0;
                long startTime = System.nanoTime();
                shellSort(data);
                long endTime = System.nanoTime();

                long timeElapsed = endTime - startTime;
                writer.write(size + "," + timeElapsed + "," + iterations + "\n");
                System.out.println("Processed size: " + size);
            }
        }
    }
}
