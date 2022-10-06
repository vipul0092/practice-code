package code.vipul.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2020.Inputs2.INPUT_21;
import static java.util.stream.Collectors.toSet;

/**
 * https://adventofcode.com/2020/day/21
 */
public class Solve21 {

    private static final String INPUT = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
            "trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
            "sqjhc fvjkl (contains soy)\n" +
            "sqjhc mxmxvkd sbzzf (contains fish)";

    private static Map<String, List<Set<String>>> ingredientsPerAllergents;
    private static Map<String, String> ingredientToAllergentAssignment;
    private static Map<String, Integer> ingredientAppearance;

    public static void solve() {
        ingredientsPerAllergents = new LinkedHashMap<>();
        ingredientToAllergentAssignment = new LinkedHashMap<>();
        ingredientAppearance = new LinkedHashMap<>();
        String[] lines = INPUT_21.split("\n");
        for (String line : lines) {
            String[] parts = line.split(" \\(contains ");
            Set<String> ingredients = Arrays.stream(parts[0].split(" ")).collect(toSet());
            String[] allergents = parts[1].substring(0, parts[1].length() - 1).split(", ");

            for (String allergent : allergents) {
                ingredientsPerAllergents.putIfAbsent(allergent, new ArrayList<>());
                ingredientsPerAllergents.get(allergent).add(new HashSet<>(ingredients));
            }

            for (String ingredient : ingredients) {
                ingredientAppearance.putIfAbsent(ingredient, 0);
                ingredientAppearance.put(ingredient, ingredientAppearance.get(ingredient) + 1);
            }
        }

        boolean found = true;

        while (found) {
            Map<String, String> currentAssignments = new LinkedHashMap<>();

            found = false;
            for (Map.Entry<String, List<Set<String>>> entry : ingredientsPerAllergents.entrySet()) {
                String allergent = entry.getKey();
                List<Set<String>> potentialIngredients = entry.getValue();
                found = false;

                Set<String> set1 = potentialIngredients.get(0);
                Set<String> set2 = null;
                Set<String> diff = set1;
                for (int i = 1; i < potentialIngredients.size(); i++) {
                    set2 = potentialIngredients.get(i);
                    diff = diff(set1, set2);
                    if (diff.size() == 1 || diff.size() == 0) {
                        break;
                    }
                    set1 = diff;
                }

                if (diff.size() == 1) {
                    found = true;
                    currentAssignments.put(diff.iterator().next(), allergent);
                    break;
                }
            }

            if (currentAssignments.size() > 0) {
                ingredientToAllergentAssignment.putAll(currentAssignments);
                for (Map.Entry<String, String> entry : currentAssignments.entrySet()) {
                    String ingredient = entry.getKey();
                    String allergent = entry.getValue();

                    ingredientsPerAllergents.remove(allergent);

                    for (Map.Entry<String, List<Set<String>>> e : ingredientsPerAllergents.entrySet()) {
                        for (Set<String> ings : e.getValue()) {
                            ings.remove(ingredient);
                        }
                    }
                }
            }

        }


        int total = ingredientAppearance.entrySet().stream()
                .filter(e -> !ingredientToAllergentAssignment.containsKey(e.getKey()))
                .map(Map.Entry::getValue)
                .reduce(0, Integer::sum);


        System.out.println("Answer: " + total); //2389


        // ---- PART 2 STARTS ----
        String value = ingredientToAllergentAssignment.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(e -> e.getKey())
                .reduce("", (a, b) -> a + "," + b)
                .substring(1);

        System.out.println("Answer Part 2: " + value); //fsr,skrxt,lqbcg,mgbv,dvjrrkv,ndnlm,xcljh,zbhp

    }

    private static Set<String> diff(Set<String> s1, Set<String> s2) {
        return s1.stream().filter(s2::contains).collect(Collectors.toSet());
    }
}
