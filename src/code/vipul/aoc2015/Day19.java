package code.vipul.aoc2015;

import java.util.*;

import static code.vipul.aoc2015.inputs.Inputs.DAY_19;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day19 {

    private static String INPUT = """
            H => HO
            H => OH
            O => HH
            
            HOH
            """;

    public static void solve() {
        INPUT = DAY_19;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        Map<String, List<String>> replacements = new HashMap<>();
        Map<String, String> reverseReplacements = new HashMap<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }
            var parts = line.split(" => ");
            replacements.putIfAbsent(parts[0], new ArrayList<>());
            replacements.get(parts[0]).add(parts[1]);
            reverseReplacements.put(parts[1], parts[0]);
        }

        String replaceIn = lines.get(lines.size()-1);
        System.out.println("Part 1: " + getPossibilities(replacements, replaceIn).size());
        System.out.println("Part 2: " + findMin(replaceIn, "e", reverseReplacements));
    }

    private static int findMin(String start, String target, Map<String, String> reverseReplacements) {
        if (start.equals(target)) {
            return 0;
        }
        for (var entry : reverseReplacements.entrySet()) {
            String to = entry.getKey(), from = entry.getValue();
            var indices = allIndices(start, to);
            for (var idx : indices) {
                int res = findMin(start.substring(0, idx) + from + start.substring(idx + to.length()),
                        target, reverseReplacements);
                if (res != -1) {
                    return 1 + res;
                }
            }
        }
        return -1;
    }

    private static Set<String> getPossibilities(Map<String, List<String>> replacements, String replaceIn) {
        Map<String, List<Integer>> found = new HashMap<>();
        for (int i = 0; i < replaceIn.length(); i++) {
            String single = String.valueOf(replaceIn.charAt(i));
            if (replacements.containsKey(single)) {
                found.putIfAbsent(single, new ArrayList<>());
                found.get(single).add(i);
            }
            if (i == replaceIn.length()-1) continue;
            String dbl = String.valueOf(replaceIn.charAt(i)) + replaceIn.charAt(i+1);
            if (replacements.containsKey(dbl)) {
                found.putIfAbsent(dbl, new ArrayList<>());
                found.get(dbl).add(i);
            }
        }
        Set<String> allStrings = new HashSet<>();
        for (var entry : replacements.entrySet()) {
            String toReplace = entry.getKey();
            if (!found.containsKey(toReplace)) continue;
            for (String replaceWith : entry.getValue()) {
                for (int index : found.get(toReplace)) {
                    String newString = replaceIn.substring(0, index)
                            + replaceWith + replaceIn.substring(index+toReplace.length());
                    allStrings.add(newString);
                }
            }
        }
        return allStrings;
    }

    private static List<Integer> allIndices(String findIn, String find) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < findIn.length(); i++) {
            int idx = findIn.substring(i).indexOf(find);
            if (idx == -1) return indices;
            indices.add(idx);
            i += (idx + find.length());
        }
        return indices;
    }
}
