package code.vipul.aoc2015;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static code.vipul.aoc2015.inputs.Inputs.DAY_22;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day22 {

    private static String INPUT = """
            Hit Points: 13
            Damage: 8
            """;
    private static int bossDamage = 0;
    private static boolean hardMode = false;
    private static Map<Key, Integer> cache;

    public static void solve() {
        INPUT = DAY_22;
        List<String> lines = Arrays.stream(INPUT.split("\n")).toList();
        int hp = Integer.parseInt(lines.get(0).split(": ")[1]);
        bossDamage = Integer.parseInt(lines.get(1).split(": ")[1]);

        hardMode = false;
        cache = new HashMap<>();
        int answer = recurse(50, 500, hp, 0, 0, 0, 0);
        System.out.println("Part 1: " + answer); // 1824

        hardMode = true;
        cache = new HashMap<>();
        answer = recurse(50, 500, hp, 0, 0, 0, 0);
        System.out.println("Part 2: " + answer); // 1937
    }

    // Dynamic programming solution with the state as the function arguments
    private static int recurse(int php, int pmana, int bhp, int t1, int t2, int t3, int playerPlays) {
        int parmor = 0;
        if (bhp <= 0) {
            return 0;
        }
        Key key = new Key(php, pmana, bhp, t1, t2, t3, playerPlays);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (hardMode && playerPlays == 0) {
            php--;
        }
        if (php <= 0) {
            return -1;
        }
        if (t1 > 0) { // SHIELD
            parmor = 7;
            t1--;
        }
        if (t2 > 0) { // POISON
            bhp -= 3;
            t2--;
        }
        if (t3 > 0) { // RECHARGE
            pmana += 101;
            t3--;
        }
        if (bhp <= 0) {
            return 0;
        }

        int minCost = Integer.MAX_VALUE;
        // play the round
        if (playerPlays == 0) {
            Spell spell;
            spell = Spell.SHIELD;
            if (spell.cost <= pmana && t1 == 0) {
                int cur = recurse(php, pmana - spell.cost, bhp, 6, t2, t3, 1);
                if (cur != -1) minCost = Math.min(cur + spell.cost, minCost);
            }
            spell = Spell.POISON;
            if (spell.cost <= pmana && t2 == 0) {
                int cur = recurse(php, pmana - spell.cost, bhp, t1, 6, t3, 1);
                if (cur != -1) minCost = Math.min(cur + spell.cost, minCost);
            }
            spell = Spell.RECHARGE;
            if (spell.cost <= pmana && t3 == 0) {
                int cur = recurse(php, pmana - spell.cost, bhp, t1, t2, 5, 1);
                if (cur != -1) minCost = Math.min(cur + spell.cost, minCost);
            }
            spell = Spell.MAGIC_MISSILE;
            if (spell.cost <= pmana) {
                int cur = recurse(php, pmana - spell.cost, bhp - 4, t1, t2, t3, 1);
                if (cur != -1) minCost = Math.min(cur + spell.cost, minCost);
            }
            spell = Spell.DRAIN;
            if (spell.cost <= pmana) {
                int cur = recurse(php + 2, pmana - spell.cost, bhp - 2, t1, t2, t3, 1);
                if (cur != -1) minCost = Math.min(cur + spell.cost, minCost);
            }
        } else {
            php = Math.max(0, php - Math.max(1, bossDamage - parmor));
            minCost = recurse(php, pmana, bhp, t1, t2, t3, 0);
        }
        minCost = minCost == Integer.MAX_VALUE ? -1 : minCost;

        cache.put(key, minCost);
        return minCost;
    }

    enum Spell {
        SHIELD(113),
        POISON(173),
        RECHARGE(229),
        MAGIC_MISSILE(53),
        DRAIN(73);

        private final int cost;

        Spell(int cost) {
            this.cost = cost;
        }
    }

    record Key(int php, int pmana, int bhp, int t1, int t2, int t3, int playerPlays) {
    }
}
