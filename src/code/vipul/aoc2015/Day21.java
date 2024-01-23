package code.vipul.aoc2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static code.vipul.aoc2015.inputs.Inputs.DAY_21;

/**
 * Created by vgaur created on 19/01/24
 */
public class Day21 {

    private static final String SHOP = """
            Weapons:    Cost  Damage  Armor
            Dagger        8     4       0
            Shortsword   10     5       0
            Warhammer    25     6       0
            Longsword    40     7       0
            Greataxe     74     8       0
                        
            Armor:      Cost  Damage  Armor
            Leather      13     0       1
            Chainmail    31     0       2
            Splintmail   53     0       3
            Bandedmail   75     0       4
            Platemail   102     0       5
                        
            Rings:      Cost  Damage  Armor
            Damage +1    25     1       0
            Damage +2    50     2       0
            Damage +3   100     3       0
            Defense +1   20     0       1
            Defense +2   40     0       2
            Defense +3   80     0       3
            """;
    private static final int COST = 0, DMG = 1, ARMOR = 2, HP = 3;
    private static final List<Item> WEAPONS;
    private static final List<Item> ARMORS;
    private static final List<Item> RINGS;
    private static final int OUR_HP = 100;

    static {
        String[] lines = SHOP.split("\n");
        int idx = 0;
        List<Item> items = new ArrayList<>();
        idx = populateShopItems(lines, idx, items, 1);
        WEAPONS = items;
        items = new ArrayList<>();
        idx = populateShopItems(lines, idx, items, 1);
        ARMORS = items;
        items = new ArrayList<>();
        populateShopItems(lines, idx, items, 2);
        RINGS = items;
    }

    public static void solve() {
        List<String> lines = Arrays.stream(DAY_21.split("\n")).toList();

        System.out.println("Part 1: " +
                simulateGameAndGetCost(lines, 1, 1, 2,
                Integer.MAX_VALUE, Math::min, true)); // 91

        System.out.println("Part 2: " +
                simulateGameAndGetCost(lines, WEAPONS.size(), ARMORS.size(), RINGS.size(),
                0, Math::max, false)); // 158
    }

    private static int simulateGameAndGetCost(List<String> input,
                                              int weaponsAllowed, int armorsAllowed, int ringsAllowed,
                                              int startingCost, BiFunction<Integer, Integer, Integer> aggregator,
                                              boolean checkPlayerWin) {
        int hp = Integer.parseInt(input.get(0).split(": ")[1]);
        int dmg = Integer.parseInt(input.get(1).split(": ")[1]);
        int armor = Integer.parseInt(input.get(2).split(": ")[1]);
        int retCost = startingCost;
        for (int r = (1 << RINGS.size()) - 1; r >= 0; r--) {
            if (countSetBits(r) > ringsAllowed) continue;
            for (int w = (1 << WEAPONS.size()) - 1; w > 0; w--) { // > 0 here because at-least one weapon is necessary
                if (countSetBits(w) > weaponsAllowed) continue;
                for (int a = (1 << ARMORS.size()) - 1; a >= 0; a--) {
                    if (countSetBits(a) > armorsAllowed) continue;
                    Player enemy = Player.createEnemy(hp, dmg, armor);
                    Player player = Player.createUs(OUR_HP, w, a, r);
                    if (doesPlayerWin(player, enemy) == checkPlayerWin) {
                        int cost = player.getAttribute(COST);
                        retCost = aggregator.apply(retCost, cost);
                    }
                }
            }
        }
        return retCost;
    }

    private static boolean doesPlayerWin(Player player, Player enemy) {
        int damageToUs = player.damageReceivedFrom(enemy);
        int damageToEnemy = enemy.damageReceivedFrom(player);

        return (player.getAttribute(HP) / damageToUs) + 1 > (enemy.getAttribute(HP) / damageToEnemy);
    }

    private static int populateShopItems(String[] lines, int idx, List<Item> items, int skip) {
        int nextIndex = -1;
        for (int i = idx + 1; i < lines.length; i++) {
            if (lines[i].isEmpty()) {
                nextIndex = i + 1;
                break;
            }
            int skipsDone = 0;
            var parts = lines[i].split(" ");
            int[] attributes = new int[3];
            int index = 0;
            for (String part : parts) {
                if (!part.isEmpty()) {
                    if (skipsDone < skip) {
                        skipsDone++;
                    } else {
                        attributes[index++] = Integer.parseInt(part);
                    }
                }
            }
            items.add(new Item(attributes));
        }
        return nextIndex;
    }

    private static int getSummedValue(int bitmask, List<Item> values, int prop) {
        int total = 0;
        for (int i = 0; i < values.size(); i++) {
            if ((bitmask & (1 << i)) != 0) {
                total += values.get(i).getAttribute(prop);
            }
        }
        return total;
    }

    private static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    record Item(int[] attributes) {
        public int getAttribute(int att) {
            return this.attributes[att];
        }
    }

    record Player(int[] attributes) {

        public static Player createUs(int hp, int weapon, int armor, int rings) {
            int totalDmg = getSummedValue(weapon, WEAPONS, DMG) + getSummedValue(rings, RINGS, DMG);
            int totalArmor = getSummedValue(armor, ARMORS, ARMOR) + getSummedValue(rings, RINGS, ARMOR);
            int cost = getSummedValue(weapon, WEAPONS, COST)
                    + getSummedValue(rings, RINGS, COST) + getSummedValue(armor, ARMORS, COST);
            return new Player(new int[]{cost, totalDmg, totalArmor, hp});
        }

        public static Player createEnemy(int hp, int damage, int armor) {
            return new Player(new int[]{0, damage, armor, hp});
        }

        public int getAttribute(int att) {
            return this.attributes[att];
        }

        public int damageReceivedFrom(Player other) {
            return Math.max(1, other.getAttribute(DMG) - getAttribute(ARMOR));
        }
    }
}
