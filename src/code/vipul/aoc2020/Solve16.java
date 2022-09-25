package code.vipul.aoc2020;

import code.vipul.tree.binaryindexedtree.BIT_1D_PointQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs2.INPUT_16;
import static java.util.stream.Collectors.toMap;

/**
 * https://adventofcode.com/2020/day/16
 */
public class Solve16 {

    private static final String INPUT = "class: 1-3 or 5-7\n" +
            "row: 6-11 or 33-44\n" +
            "seat: 13-40 or 45-50\n" +
            "\n" +
            "your ticket:\n" +
            "7,1,14\n" +
            "\n" +
            "nearby tickets:\n" +
            "7,3,47\n" +
            "40,4,50\n" +
            "55,2,20\n" +
            "38,6,12";

    private static final String INPUT_2 = "class: 0-1 or 4-19\n" +
            "row: 0-5 or 8-19\n" +
            "seat: 0-13 or 16-19\n" +
            "\n" +
            "your ticket:\n" +
            "11,12,13\n" +
            "\n" +
            "nearby tickets:\n" +
            "3,9,18\n" +
            "15,1,5\n" +
            "5,14,9";

    private static BIT_1D_PointQuery validValues;
    private static List<TicketField> ticketFields;
    private static List<Integer> selfTicket;
    private static List<List<Integer>> otherTickets;

    public static void solve() {
        parseInput(INPUT_16);

        int errorRate = 0;
        for (List<Integer> otherTicket : otherTickets) {
            for (Integer value : otherTicket) {
                if (validValues.point_query(value) == 0) {
                    errorRate += value;
                }
            }
        }

        System.out.println("Answer: " + errorRate); //23115
    }

    public static void solvePart2() {
        parseInput(INPUT_16);

        List<List<Integer>> validTickets = new ArrayList<>();
        for (List<Integer> otherTicket : otherTickets) {
            boolean valid = true;
            for (Integer value : otherTicket) {
                if (validValues.point_query(value) == 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                validTickets.add(otherTicket);
            }
        }
        Map<String, TicketField> fieldMap = ticketFields.stream().collect(toMap(t -> t.name, t -> t));

        // Track all valid indexes for all the fields
        Map<String, Set<Integer>> indexesPerField = new LinkedHashMap<>();
        for (TicketField field : ticketFields) {
            indexesPerField.put(field.name, new LinkedHashSet<>());
            for (int i = 0; i < ticketFields.size(); i++) {
                boolean isIndexValid = true;
                for (List<Integer> validTicket : validTickets) {
                    if (!field.isValid(validTicket.get(i))) {
                        isIndexValid = false;
                        break;
                    }
                }

                if (isIndexValid) {
                    indexesPerField.get(field.name).add(i);
                }
            }
        }

        // Sort through the possible indexes per field to assign the correct index to each field
        while (!indexesPerField.isEmpty()) {
            // Search for field with only one matched index
            String fieldId = indexesPerField.entrySet().stream()
                    .filter(e -> e.getValue().size() == 1)
                    .map(e -> e.getKey())
                    .findFirst().orElseThrow();

            Set<Integer> indexes = indexesPerField.get(fieldId);
            int index = indexes.iterator().next();

            // Set the index for that field
            fieldMap.get(fieldId).setIndex(index);
            indexesPerField.remove(fieldId);

            // Remove that index from all other fields
            for (Map.Entry<String, Set<Integer>> entry : indexesPerField.entrySet()) {
                entry.getValue().remove(index);
            }
        }

        long answer = ticketFields.stream()
                .filter(f -> f.name.startsWith("departure"))
                .map(f -> (long) selfTicket.get(f.index))
                .reduce(1L, (a, b) -> a * b);

        System.out.println("Answer: " + answer); //239727793813
    }

    private static void parseInput(String input) {
        String[] lines = input.split("\n");
        int ctr = 0;
        validValues = BIT_1D_PointQuery.ofSize(1000);
        ticketFields = new ArrayList<>();
        while (!lines[ctr].isEmpty()) {
            String[] parts = lines[ctr].split(": ");
            String[] ranges = parts[1].split(" or ");

            TicketField field = new TicketField(parts[0]);
            for (String range : ranges) {
                String[] startEnd = range.split("-");
                int start = Integer.parseInt(startEnd[0]);
                int end = Integer.parseInt(startEnd[1]);

                validValues.range_add(start, end, 1);
                field.addRange(start, end);
            }
            ticketFields.add(field);
            ctr++;
        }
        ctr += 2;

        selfTicket = Arrays.stream(lines[ctr].split(","))
                .map(t -> Integer.parseInt(t)).collect(Collectors.toList());
        ctr += 3;

        otherTickets = new ArrayList<>();
        while (ctr < lines.length) {
            otherTickets.add(Arrays.stream(lines[ctr].split(","))
                    .map(t -> Integer.parseInt(t)).collect(Collectors.toList()));
            ctr++;
        }
    }

    public static class TicketField {
        private final String name;
        private final BIT_1D_PointQuery valid;
        private int index = -1;

        public TicketField(String name) {
            this.name = name;
            valid = BIT_1D_PointQuery.ofSize(1000);
        }

        public void addRange(int start, int end) {
            valid.range_add(start, end, 1);
        }

        public void setIndex(int idx) {
            this.index = idx;
        }

        public boolean isValid(int value) {
            return valid.point_query(value) != 0;
        }

        public String toString() {
            return String.format("field: %s, index: %s", name, index);
        }
    }
}
