package code.vipul.aoc2015;

import java.util.*;
import java.util.stream.Stream;

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

    private static int id = 0;
    private static Map<String, Integer> ids = new HashMap<>();

    public static void solve() {
        INPUT = DAY_19;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();

        Map<String, List<String>> replacements = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                break;
            }
            var parts = lines.get(i).split(" => ");
            replacements.putIfAbsent(parts[0], new ArrayList<>());
            replacements.get(parts[0]).add(parts[1]);
        }

        String replaceIn = lines.get(lines.size()-1);
        List<Integer> medicineIds = getIdsForString(replaceIn);

        Map<Integer, List<List<Integer>>> replacementNumbers = new HashMap<>();
        for (var entry : replacements.entrySet()) {
            String key = entry.getKey();
            int id = getId(key);
            replacementNumbers.putIfAbsent(getId(key), new ArrayList<>());
            for (String str : entry.getValue()) {
                replacementNumbers.get(id).add(getIdsForString(str));
            }
        }

        Map<String, List<Integer>> found = new HashMap<>();
        Set<String> unique = new HashSet<>();
        for (int i = 0; i < replaceIn.length(); i++) {
            String single = String.valueOf(replaceIn.charAt(i));
            if (replacements.containsKey(single)) {
                found.putIfAbsent(single, new ArrayList<>());
                found.get(single).add(i);
            }
            if (replaceIn.charAt(i) <= 'Z') unique.add(single);
            if (i == replaceIn.length()-1) continue;
            String dbl = String.valueOf(replaceIn.charAt(i)) + replaceIn.charAt(i+1);
            if (replacements.containsKey(dbl)) {
                found.putIfAbsent(dbl, new ArrayList<>());
                found.get(dbl).add(i);
            }
            if (replaceIn.charAt(i) <= 'Z' && replaceIn.charAt(i+1) >= 'a') unique.add(dbl);
        }

        Map<Integer, List<Integer>> found2 = new HashMap<>();
        for (int i = 0; i < medicineIds.size(); i++) {
            int id = medicineIds.get(i);
            if (replacementNumbers.containsKey(id)) {
                found2.putIfAbsent(id, new ArrayList<>());
                found2.get(id).add(i);
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

        Set<List<Integer>> allLists = new HashSet<>();
        for (var entry : replacementNumbers.entrySet()) {
            Integer toReplace = entry.getKey();
            if (!found2.containsKey(toReplace)) continue;
            for (List<Integer> replaceWith : entry.getValue()) {
                for (int index : found2.get(toReplace)) {
                    List<Integer> newList = Stream.concat(
                            Stream.concat(medicineIds.subList(0, index).stream(), replaceWith.stream()),
                            index == medicineIds.size()- 1 ? Stream.of() : medicineIds.subList(index+1, medicineIds.size()).stream()).toList();
                    allLists.add(newList);
                }
            }
        }

        System.out.println(allStrings.size());
        System.out.println(allLists.size());


        Set<List<Integer>> visited = new HashSet<>();
        Queue<List<Integer>> queue = new ArrayDeque<>();
        Queue<Integer> length = new ArrayDeque<>();

        for (var possible : replacementNumbers.get(getId("e"))) {
            queue.add(possible);
            length.add(1);
            visited.add(possible);
        }

        int answer = -1;
        while(!queue.isEmpty()) {
            List<Integer> curr = queue.remove();
            int curlen = length.remove();
            if (curr.size() == medicineIds.size() && curr.equals(medicineIds)) {
                answer = curlen;
                break;
            }

            for (int i = 0; i < curr.size(); i++) {
                int c = curr.get(i);
                if (!replacementNumbers.containsKey(c)) continue;
                for (List<Integer> replaceWith : replacementNumbers.get(c)) {
                    List<Integer> newList = Stream.concat(
                            Stream.concat(curr.subList(0, i).stream(), replaceWith.stream()),
                            i == curr.size() - 1 ? Stream.of() : curr.subList(i + 1, curr.size()).stream()).toList();
                    if (newList.size() <= medicineIds.size() && !visited.contains(newList)) {
                        queue.add(newList);
                        length.add(curlen+1);
                        visited.add(newList);
                    }
                }
            }

        }

        System.out.println(answer);
    }

    private static List<Integer> getIdsForString(String replaceIn) {
        List<Integer> medicine = new ArrayList<>();
        for (int i = 0; i < replaceIn.length();) {
            char current = replaceIn.charAt(i);
            char next = i == replaceIn.length() - 1 ? '\0' : replaceIn.charAt(i+1);
            if (current <= 'Z' && next >= 'a') {
                medicine.add(getId(String.valueOf(current) + next));
                i++;
            } else if (current <= 'Z') {
                medicine.add(getId(String.valueOf(current)));
            }
            i++;
        }
        return medicine;
    }

    private static int getId(String str) {
        if (!ids.containsKey(str)) {
            ids.put(str, id);
            id++;
        }
        return ids.get(str);
    }
}
