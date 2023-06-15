package com.elijahkx.day1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    private static List<Integer> readCalories(BufferedReader reader) throws IOException {
        List<Integer> calories = new ArrayList<>();
        String line;
        int currentCalories = 0;

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                calories.add(currentCalories);
                currentCalories = 0;
            } else {
                currentCalories += Integer.parseInt(line);
            }
        }

        return calories;
    }

    private static int calculateMaxCalories(List<Integer> calories) {
        return Collections.max(calories);
    }

    private static int calculateCaloriesOfTopThreeElves(List<Integer> calories) {
        List<Integer> sortedCalories = new ArrayList<>(calories);
        sortedCalories.sort((a, b) -> b - a);

        return sortedCalories.stream()
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/com/elijahkx/day1/input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<Integer> calories = readCalories(reader);

            int maxCalories = calculateMaxCalories(calories);
            System.out.println("Max calories: " + maxCalories);

            int totalCaloriesOfTopThreeElves = calculateCaloriesOfTopThreeElves(calories);
            System.out.println("Total calories of top three: " + totalCaloriesOfTopThreeElves);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
