package com.elijahkx.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Day3 {
    private static final int LOWERCASE_PRIORITY_OFFSET = 1;
    private static final int UPPERCASE_PRIORITY_OFFSET = 27;

    public static void main(String[] args) throws IOException {
        var input = Files.readString(Path.of("src/main/java/com/elijahkx/day3/input.txt"));
        String[] rucksacks = input.split("\n");

        int part1 = calculateSumOfPriorities1(rucksacks);
        int part2 = calculateSumOfPriorities2(rucksacks);

        System.out.println("part1 :" + part1);
        System.out.println("part2 :" + part2);
    }

    private static int calculateSumOfPriorities1(String[] rucksacks) {
        int sumOfPriorities = 0;

        for (String rucksack : rucksacks) {
            String firstPart = rucksack.substring(0, rucksack.length() / 2);
            String secondPart = rucksack.substring(rucksack.length() / 2);

            Set<Character> seenItems = new HashSet<>();

            for (char c : firstPart.toCharArray()) {
                if (secondPart.contains(String.valueOf(c)) && !seenItems.contains(c)) {
                    int priority = getPriority(c);

                    sumOfPriorities += priority;
                    seenItems.add(c);
                }
            }
        }

        return sumOfPriorities;
    }

    private static int calculateSumOfPriorities2(String[] rucksacks) {
        int sumOfPriorities = 0;

        for (int i = 0; i < rucksacks.length; i += 3) {
            String rucksack1 = rucksacks[i];
            String rucksack2 = rucksacks[i + 1];
            String rucksack3 = rucksacks[i + 2];

            for (char c : rucksack1.toCharArray()) {
                if (rucksack2.contains(String.valueOf(c)) && rucksack3.contains(String.valueOf(c))) {
                    int priority = getPriority(c);

                    sumOfPriorities += priority;
                    break;
                }
            }
        }

        return sumOfPriorities;
    }

    private static int getPriority(char item) {
        if (Character.isLowerCase(item)) {
            return item - 'a' + LOWERCASE_PRIORITY_OFFSET;
        } else {
            return item - 'A' + UPPERCASE_PRIORITY_OFFSET;
        }
    }
}
