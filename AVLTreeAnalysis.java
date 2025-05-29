import java.util.*;

public class AVLTreeAnalysis {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random rand = new Random();

        int[] data = new int[10000];
        for (int i = 0; i < 10000; i++) {
            data[i] = rand.nextInt(1_000_000);
        }

        List<Long> insertTimes = new ArrayList<>();
        List<Integer> insertOps = new ArrayList<>();

        for (int num : data) {
            tree.operationCount = 0;
            long start = System.nanoTime();
            tree.root = tree.insert(tree.root, num);
            long end = System.nanoTime();
            insertTimes.add(end - start);
            insertOps.add(tree.operationCount);
        }

        List<Long> searchTimes = new ArrayList<>();
        List<Integer> searchOps = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int num = data[rand.nextInt(data.length)];
            tree.operationCount = 0;
            long start = System.nanoTime();
            tree.search(tree.root, num);
            long end = System.nanoTime();
            searchTimes.add(end - start);
            searchOps.add(tree.operationCount);
        }

        List<Long> deleteTimes = new ArrayList<>();
        List<Integer> deleteOps = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            int num = data[rand.nextInt(data.length)];
            tree.operationCount = 0;
            long start = System.nanoTime();
            tree.root = tree.delete(tree.root, num);
            long end = System.nanoTime();
            deleteTimes.add(end - start);
            deleteOps.add(tree.operationCount);
        }

        System.out.printf("\nСреднее время вставки: %.2f мкс%n", average(insertTimes) / 1000);
        System.out.printf("Среднее кол-во операций вставки: %.2f%n", average(insertOps));
        System.out.printf("Среднее время поиска: %.2f мкс%n", average(searchTimes) / 1000);
        System.out.printf("Среднее кол-во операций поиска: %.2f%n", average(searchOps));
        System.out.printf("Среднее время удаления: %.2f мкс%n", average(deleteTimes) / 1000);
        System.out.printf("Среднее кол-во операций удаления: %.2f%n", average(deleteOps));
    }

    public static double average(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list)
            sum += n.doubleValue();
        return sum / list.size();
    }
}
