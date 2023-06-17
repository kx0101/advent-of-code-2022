package com.elijahkx.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day4 {
    public static void main(String[] args) throws IOException {
        var input = Files.readString(Path.of("src/main/java/com/elijahkx/day4/input.txt"));
        String[] pairs = input.split("\n");

        int part1 = 0;
        int part2 = 0;

        for (String pair : pairs) {
            String[] sections = pair.split(",");
            int[] range1 = parseRange(sections[0]);
            int[] range2 = parseRange(sections[1]);

            if (isRangeFullyContained(range1, range2)) {
                part1++;
            }

            if (isRangesOverlap(range1, range2)) {
                part2++;
            }
        }

        System.out.println("part1: " + part1);
        System.out.println("part2: " + part2);
    }

    private static int[] parseRange(String section) {
        String[] range = section.split("-");

        int start = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);

        return new int[] { start, end };
    }

    private static boolean isRangeFullyContained(int[] range1, int[] range2) {
        return range1[0] <= range2[0] && range1[1] >= range2[1];
    }

    private static boolean isRangesOverlap(int[] range1, int[] range2) {
        return range1[0] <= range2[1] && range1[1] >= range2[0];
    }
}
