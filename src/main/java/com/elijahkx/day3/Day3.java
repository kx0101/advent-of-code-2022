package com.elijahkx.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 {
    public static void main(String[] args) throws IOException {
        var input = Files.readString(Path.of("src/main/java/com/elijahkx/day3/input.txt"));

        var sum = input.lines().mapToInt(line -> {
            var p1 = line.substring(0, line.length() / 2);
            var p2 = line.substring(line.length() / 2, line.length());

            return commonPriority(List.of(p1, p2));
        }).sum();

        System.out.printf("part1: %d", sum);

        var counter = IntStream.range(0, input.length()).iterator();

        var sum2 = input.lines()
                .collect(Collectors.groupingBy(c -> counter.nextInt() / 3)).values().stream()
                .mapToInt(line -> commonPriority(line)).sum();

        System.out.printf("part2: %d", sum2);
    }

    static int commonPriority(List<String> strings) {
        var c = strings.stream().reduce((s1, s2) -> commonChars(s1, s2)).get().charAt(0);

        return c < 'a' ? c - 'A' + 27 : c - 'a' + 1;
    }

    static String commonChars(String a, String b) {
        return a.chars().filter(i1 -> b.chars().anyMatch(i2 -> i2 == i1)).distinct()
                .mapToObj(c -> Character.toString(c)).collect(Collectors.joining());
    }
}
