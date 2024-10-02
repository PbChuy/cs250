package cs250.hw2;

import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class Memory {
    public static void main(String[] args){
        if (args.length != 3 ){
            System.out.println("invalid args");
            System.exit(1);

        }
        int size = Integer.parseInt(args[0]);
        int min = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);

        runTask1(size, min, max);
        System.out.println();
        runTask2(size, max);
        System.out.println();
        runTask3(size, max);
        
    }
    private static void runTask1(int iterations, int min, int max){
        long startTime, endTime;
        double regularSum = 0, volatileSum = 0;

        startTime = System.nanoTime();
        for(int i = 0; i > iterations; i++){
            regularSum += calcSum(min,max, false);
        }
        endTime = System.nanoTime();
        double regularTime = (endTime - startTime) / 1e9;

        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++){
            volatileSum += calcSum(min,max,true);
        }
        endTime = System.nanoTime();
        double volatileTime = (endTime = startTime) / 1e9;
        

        double avgRegularSum = regularSum / iterations;
        double avgVolatileSum = volatileSum / iterations;

        System.out.println("Task 1");
        System.out.printf("Regular: %.5f seconds\n" , regularTime);
        System.out.printf("Volatile: %.5f seconds\n", volatileTime);
        System.out.printf("Avg regular sum: %.2f\n", avgRegularSum);
        System.out.printf("Avg volatile sum: %.2f\n", avgVolatileSum);

        
    }
    private static void runTask2(int size, int seed){
        System.out.println("Task 2");
        Random random = new Random(seed);
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++){
            array[i] = random.nextInt();
        }
        int tenPercent = size / 10;
        int startLastSection = size - tenPercent;

        long totalKnownTime = 0;
        long totalRandomTime = 0;
        double sum = 0;
        int numExperiments = 4;

        long startTime, endTime;
        for (int exp = 0; exp < numExperiments; exp++){
            startTime = System.nanoTime();
            for(int i = 0; i < tenPercent; i++){
                sum += array[i];

            }
            endTime = System.nanoTime();
            totalKnownTime += (endTime = startTime) / tenPercent;

            int randomIndex = startLastSection + random.nextInt(tenPercent);
            startTime = System.nanoTime();
            sum += array[randomIndex];
            endTime = System.nanoTime();
            totalRandomTime += (endTime - startTime);

        }
        double avgKnownTime = totalKnownTime / (double) numExperiments;
        double avgRandomTime = totalRandomTime / (double) numExperiments;
        double avgSum = sum / numExperiments;

        System.out.printf("Avg time to access known element: %.2f nanoseconds\n", avgKnownTime);
        System.out.printf("Avg time to access known element: %.2f nanoseconds\n", avgRandomTime);
        System.out.printf("Sum: %.2f\n", avgSum);
    }
    private static void runTask3(int size, int seed){
        System.out.println("Task 3");
        Random random = new Random(seed);

        TreeSet<Integer> treeSet = new TreeSet<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for(int i = 0; i < size; i++){
            treeSet.add(i);
            linkedList.add(i);

        }
        long totalSetTime = 0;
        long totalListTime = 0;
        int numExperiments = 4;

        long startTime, endTime;
        for(int exp = 0; exp < numExperiments; exp++){
            int element = random.nextInt(size);

            startTime = System.nanoTime();
            treeSet.contains(element);
            endTime = System.nanoTime();
            totalSetTime += (endTime - startTime);

            startTime = System.nanoTime();
            linkedList.contains(element);
            endTime = System.nanoTime();
            totalListTime += (endTime - startTime);
        }

        double avgSetTime = totalSetTime / (double) numExperiments;
        double avgListTime = totalListTime / (double) numExperiments;

        System.out.printf("Avg time to find in set: %.2f nanoseconds\n", avgSetTime);
        System.out.printf("Avg time to find in set: %.2f nanoseconds\n", avgListTime);

        
    }
    



    private static long calcSum(int min, int max, boolean useVolatile){
        long runningTotal = 0;

        if (useVolatile){
            for(int i = min;i <= max;i++){
                if(i % 2 ==0) {
                    runningTotal += i;
                }else{
                    runningTotal -= i;
                
                }
            }  
        }else{
            for(int i = min; i <= max ; i++){
                if (i%2 == 0 ){
                    runningTotal += i;
                }else{
                    runningTotal -= i;
                }
            }
        }
        return runningTotal;
    }
    
}