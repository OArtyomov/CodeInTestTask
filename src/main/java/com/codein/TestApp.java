package com.codein;

import com.codein.data.statistic.StatisticData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TestApp {


    public static void main(String[] args) {
        Integer maxThreadCount = extractInteger(0, 1, 65, args);
        Integer maxElementsCount = extractInteger(1, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, args);
        PoolManager initService = new PoolManager(maxThreadCount, maxElementsCount);
        initService.execute();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter any key for print statistic: ");
        try {
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initService.shutdown();

        StatisticData statisticData = initService.getStatistic();
        if (statisticData.getStatisticPerThread() != null) {
            statisticData.getStatisticPerThread().forEach(
                    statisticThreadData ->
                            System.out.println("Thread: " + statisticThreadData.getThreadName()
                                    + " - " + statisticThreadData.getCount()
                                    + ". Min time: " + statisticThreadData.getNanoTimeOfFirstElement()
                                    + ". Max time: " + statisticThreadData.getNanoTimeOfLastElement() +
                                    ". Time of elements: " + statisticThreadData.getTimeOfElements()));
        }
        System.out.println("Max size in queue: " + statisticData.getMaxCountInQueue());

        exitProgramWithMessage("Bye");
    }


    private static void exitProgramWithMessage(String message) {
        System.out.println(message);
        System.exit(0);
    }


    private static Integer extractInteger(int index, int minValue, int maxValue, String[] args) {
        Integer result = null;
        if ((args != null) && (args.length >= index + 1)) {
            String argument = args[index];
            try {
                result = Integer.parseInt(argument);
                if (result <= minValue) {
                    exitProgramWithMessage("Argument " + (index + 1) + " is less or equal low limit " + minValue);
                }
                if (result >= maxValue) {
                    exitProgramWithMessage("Argument " + (index + 1) + " is more or equal high limit " + maxValue);
                }

            } catch (NumberFormatException e) {
                System.err.println("Error parsing element with index " + index);
            }
        }
        if (result == null) {
            exitProgramWithMessage("Argument " + (index + 1) + " is not valid or not specified");
        }
        return result;
    }
}
