import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int threads = Runtime.getRuntime().availableProcessors();
        System.out.println("Число возможных потоков для вычисления - " + threads);
        threads = 25;
        System.out.println("Для выполнения задачи будем использовать " + threads + " потоков.");

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Runnable> tasks = new ArrayList<>();

        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }
        // start time
        long startTs = System.currentTimeMillis();

        for (String text : texts) {
            tasks.add(() -> {
                int maxSize = 0;
                for (int i = 0; i < text.length(); i++) {
                    for (int j = 0; j < text.length(); j++) {
                        if (i >= j) {
                            continue;
                        }
                        boolean bFound = false;
                        for (int k = i; k < j; k++) {
                            if (text.charAt(k) == 'b') {
                                bFound = true;
                                break;
                            }
                        }
                        if (!bFound && maxSize < j - i) {
                            maxSize = j - i;
                        }
                    }
                }
                System.out.println(text.substring(0, 100) + " -> " + maxSize);
            });
        }

        for (Runnable task : tasks) {
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);

        long endTs = System.currentTimeMillis(); // end time
        System.out.println("Time: " + (endTs - startTs) + "ms");
    }// main

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}//class