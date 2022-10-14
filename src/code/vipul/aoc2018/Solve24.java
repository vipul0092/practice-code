package code.vipul.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static code.vipul.aoc2018.Inputs2.DAY_24;
import static code.vipul.aoc2018.Inputs2.DAY_24_2;

/**
 * https://adventofcode.com/2018/day/24
 */
public class Solve24 {

    private static final String INPUT = "Immune System:\n" +
            "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2\n" +
            "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3\n" +
            "\n" +
            "Infection:\n" +
            "801 units each with 4706 hit points (weak to radiation) with an attack that does 116 bludgeoning damage at initiative 1\n" +
            "4485 units each with 2961 hit points (immune to radiation; weak to fire, cold) with an attack that does 12 slashing damage at initiative 4";

    private static Map<String, Group> immuneSystemArmy;
    private static Map<String, Group> infectionArmy;

    private static final Comparator<Group> BY_POWER_THEN_INITIATIVE = (g1, g2) ->
            g1.getEffectivePower() != g2.getEffectivePower()
                    ? g2.getEffectivePower() - g1.getEffectivePower()
                    : g2.initiative - g1.initiative;

    public static void solve() {
        initialize(DAY_24, 0);

        GroupType winningType = fight();
        Map<String, Group> winner = winningType == GroupType.IMMUNE_SYSTEM ? immuneSystemArmy : infectionArmy;
        int answer = winner.values().stream().map(g -> g.units).reduce(0, Integer::sum);

        System.out.println("Answer: " + answer); //26753
    }

    public static void solvePart2() {
        String in = DAY_24;


        GroupType winningType;
        int boost = 40;
        do {
            boost *= 2;
            initialize(in, boost);
            winningType = fight();
        } while (winningType != GroupType.IMMUNE_SYSTEM);

        int start = 0;
        int end = boost;

        while (end - start > 1) {
            int b = (start + end) / 2;
            System.out.println("Trying a boost of: " + b);
            initialize(in, b);
            GroupType win = fight();

            if (win == null) {
                System.out.println("LOOP DETECTED!");
                System.out.println();
                start = b;
                break;
            }

            if (win == GroupType.IMMUNE_SYSTEM) {
                end = (start + end) / 2;
            } else {
                start = (start + end) / 2;
            }
        }

        boost = start;
        GroupType win = GroupType.INFECTION;
        while (win != GroupType.IMMUNE_SYSTEM) {
            System.out.println("Trying a boost of: " + boost);
            initialize(in, boost);
            win = fight();
            boost++;
        }

        int answer = immuneSystemArmy.values().stream().map(g -> g.units).reduce(0, Integer::sum);
        System.out.println("Answer: " + answer); //1852
    }

    private static GroupType fight() {
        Map<Group, Group> attackerToDefenderMap;
        Map<Group, Group> defenderToAttackerMap;

        Map<String, Integer> lastInfectionState = new HashMap<>();
        Map<String, Integer> lastImmuneState = new HashMap<>();

        boolean loop = false;
        while (!(immuneSystemArmy.isEmpty() || infectionArmy.isEmpty())) {
//            System.out.println();
//            immuneSystemArmy.forEach((k, v) ->
//                    System.out.println(String.format("%s contains %s units", v.groupId, v.units)));
//            infectionArmy.forEach((k, v) ->
//                    System.out.println(String.format("%s contains %s units", v.groupId, v.units)));
//            System.out.println();

            attackerToDefenderMap = new LinkedHashMap<>();
            defenderToAttackerMap = new LinkedHashMap<>();
            List<Group> immuneSystemGroups = new ArrayList<>(immuneSystemArmy.values()).stream()
                    .sorted(BY_POWER_THEN_INITIATIVE).collect(Collectors.toList());
            List<Group> infectionGroups = new ArrayList<>(infectionArmy.values()).stream()
                    .sorted(BY_POWER_THEN_INITIATIVE).collect(Collectors.toList());

            int defenders = 0;
            for (Group group : immuneSystemGroups) {
                if (defenders == infectionGroups.size()) {
                    break;
                }
                Map<Group, Group> finalDefenderToAttackerMap = defenderToAttackerMap;
                // Select a defender
                Optional<Group> defender = infectionArmy.values().stream()
                        // Such that its not already a defender for some other attacker
                        .filter(e -> !finalDefenderToAttackerMap.containsKey(e))
                        // The attacker can actually do damage to the defender
                        .filter(g -> group.getDamageDoneOn(g) > 0)
                        // Sort the defenders on damage they take, then power and then initiative
                        .sorted((g1, g2) -> {
                            int d2 = group.getDamageDoneOn(g2);
                            int d1 = group.getDamageDoneOn(g1);
                            return d2 != d1
                                    ? d2 - d1
                                    : BY_POWER_THEN_INITIATIVE.compare(g1, g2);
                        })
                        .findFirst();

                if (defender.isEmpty()) {
                    continue;
                }
                defenders++;
                attackerToDefenderMap.put(group, defender.get());
                defenderToAttackerMap.put(defender.get(), group);
            }

            defenders = 0;
            for (Group group : infectionGroups) {
                if (defenders == immuneSystemGroups.size()) {
                    break;
                }
                Map<Group, Group> finalDefenderToAttackerMap = defenderToAttackerMap;
                Optional<Group> defender = immuneSystemArmy.values().stream()
                        .filter(e -> !finalDefenderToAttackerMap.containsKey(e))
                        .filter(g -> group.getDamageDoneOn(g) > 0)
                        .sorted((g1, g2) -> {
                            int d2 = group.getDamageDoneOn(g2);
                            int d1 = group.getDamageDoneOn(g1);
                            return d2 != d1
                                    ? d2 - d1
                                    : BY_POWER_THEN_INITIATIVE.compare(g1, g2);
                        })
                        .findFirst();

                if (defender.isEmpty()) {
                    continue;
                }
                defenders++;
                attackerToDefenderMap.put(group, defender.get());
                defenderToAttackerMap.put(defender.get(), group);
            }

            Map<Group, Group> temp = new LinkedHashMap<>();
            Map<Group, Group> finalAttackerToDefenderMap = attackerToDefenderMap;
            attackerToDefenderMap.keySet().stream()
                    .sorted((g1, g2) -> g2.initiative - g1.initiative)
                    .forEachOrdered(g -> temp.put(g, finalAttackerToDefenderMap.get(g)));
            attackerToDefenderMap = temp;

            for (Map.Entry<Group, Group> entry : attackerToDefenderMap.entrySet()) {
                Group attacker = entry.getKey();
                Group defender = entry.getValue();
                defender.acceptAttack(attacker);
                defender.commitDamage();
            }

            Map<String, Group> tmp = new LinkedHashMap<>();
            Map<String, Group> ft1 = tmp;
            immuneSystemArmy.entrySet().stream()
                    .filter(e -> e.getValue().isAlive())
                    .forEachOrdered(e -> ft1.put(e.getKey(), e.getValue()));
            immuneSystemArmy = tmp;

            Map<String, Integer> immuneState = new HashMap<>();
            immuneSystemArmy.forEach((k, v) -> immuneState.put(v.groupId, v.units));

            tmp = new LinkedHashMap<>();
            Map<String, Group> ft2 = tmp;
            infectionArmy.entrySet().stream()
                    .filter(e -> e.getValue().isAlive())
                    .forEachOrdered(e -> ft2.put(e.getKey(), e.getValue()));
            infectionArmy = tmp;

            Map<String, Integer> infectionState = new HashMap<>();
            infectionArmy.forEach((k, v) -> infectionState.put(v.groupId, v.units));

            if (immuneState.equals(lastImmuneState) && infectionState.equals(lastInfectionState)) {
                loop = true;
                break;
            }
            lastImmuneState = immuneState;
            lastInfectionState = infectionState;
        }
        return loop ? null : (infectionArmy.isEmpty() ? GroupType.IMMUNE_SYSTEM : GroupType.INFECTION);
    }

    private static void initialize(String in, int boost) {
        immuneSystemArmy = new LinkedHashMap<>();
        infectionArmy = new LinkedHashMap<>();

        String[] lines = in.split("\n");
        int ctr = -1;
        String currentGroup = "";
        GroupType groupType = null;
        Map<String, Group> currentArmy = null;

        for (String line : lines) {
            if (line.startsWith("Immune System")) {
                ctr = 1;
                currentGroup = "Immune System group ";
                currentArmy = immuneSystemArmy;
                groupType = GroupType.IMMUNE_SYSTEM;
            } else if (line.startsWith("Infection")) {
                ctr = 1;
                currentGroup = "Infection group ";
                currentArmy = infectionArmy;
                groupType = GroupType.INFECTION;
            } else if (!line.isEmpty()) {
                String groupId = currentGroup + ctr++;
                currentArmy.put(groupId, getGroup(line, groupId, groupType, boost));
            }
        }
    }

    private static Group getGroup(String groupString, String groupId, GroupType groupType, int boost) {
        String[] unitsSplit = groupString.split(" units each with ");
        int units = Integer.parseInt(unitsSplit[0]);
        String[] hitPointsSplit = unitsSplit[1].split(" hit points ");
        int hitpoints = Integer.parseInt(hitPointsSplit[0]);

        Set<String> weaknesses = new HashSet<>();
        Set<String> immunities = new HashSet<>();

        if (hitPointsSplit[1].contains("(")) {
            String immuneWeak = hitPointsSplit[1].substring(1, hitPointsSplit[1].lastIndexOf(')'));
            String[] immuneWeakParts = immuneWeak.split("; ");
            if (immuneWeakParts[0].startsWith("immune to ")) {
                immunities = Arrays.stream(immuneWeakParts[0].substring(10).split(", ")).collect(Collectors.toSet());
            } else {
                weaknesses = Arrays.stream(immuneWeakParts[0].substring(8).split(", ")).collect(Collectors.toSet());
            }

            if (immuneWeakParts.length > 1 && immuneWeakParts[1].startsWith("immune to ")) {
                immunities = Arrays.stream(immuneWeakParts[1].substring(10).split(", ")).collect(Collectors.toSet());
            } else if (immuneWeakParts.length > 1) {
                weaknesses = Arrays.stream(immuneWeakParts[1].substring(8).split(", ")).collect(Collectors.toSet());
            }
        }

        String[] damageInitiativeSplit = hitPointsSplit[1]
                .split(" an attack that does ")[1]
                .split(" damage at initiative ");


        String[] damage = damageInitiativeSplit[0].split(" ");
        int initiative = Integer.parseInt(damageInitiativeSplit[1]);
        String attackType = damage[1];
        int attackDamage = Integer.parseInt(damage[0]) + (groupType == GroupType.IMMUNE_SYSTEM ? boost : 0);

        return new Group(units, hitpoints, weaknesses, immunities, attackDamage, attackType, initiative, groupId,
                groupType);
    }

    private static class Group {
        private int units;
        private final int hitPointsPerUnit;
        private final Set<String> weaknesses;
        private final Set<String> immunities;
        private final int attackDamagePerUnit;
        private final String attackType;
        private final int initiative;
        private final String groupId;
        private final GroupType groupType;
        private int hash = -1;

        private int roundUnits = -1;

        Group(int u, int hitPointsPerUnit, Set<String> weaknesses, Set<String> immunities, int attackDamagePerUnit,
              String attackType, int initiative, String groupId, GroupType groupType) {
            this.units = u;
            this.hitPointsPerUnit = hitPointsPerUnit;
            this.weaknesses = weaknesses;
            this.immunities = immunities;
            this.attackDamagePerUnit = attackDamagePerUnit;
            this.attackType = attackType;
            this.initiative = initiative;
            this.groupId = groupId;
            this.groupType = groupType;
        }

        public boolean isImmune(String type) {
            return immunities.contains(type);
        }

        public boolean isWeakness(String type) {
            return weaknesses.contains(type);
        }

        public boolean isAlive() {
            return units > 0;
        }

        public int getEffectivePower() {
            return units * attackDamagePerUnit;
        }

        public int getDamageDoneOn(Group toAttack) {
            int damage = getEffectivePower();
            if (toAttack.isImmune(attackType)) {
                return 0;
            } else if (toAttack.isWeakness(attackType)) {
                return 2 * damage;
            } else {
                return damage;
            }
        }

        public void acceptAttack(Group attacker) {
            int damage = attacker.getDamageDoneOn(this);
            int unitsDestroyed = damage / hitPointsPerUnit;
            roundUnits = unitsDestroyed > units ? 0 : units - unitsDestroyed;
//            System.out.println(String.format("%s attacks %s, killing %s units",
//                    attacker.groupId, groupId, (units - roundUnits)));
        }

        public void commitDamage() {
            if (roundUnits != -1) {
                this.units = roundUnits;
                roundUnits = -1;
            }
        }

        @Override
        public int hashCode() {
            if (hash == -1) {
                hash = 5381;
                hash += (hash << 5) + Objects.hashCode(groupId);
            }
            return hash;
        }

        public String toString() {
            return groupId + String.format(", units: %s", units);
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) return true;
            return another instanceof Group && equalTo((Group) another);
        }

        private boolean equalTo(Group another) {
            return Objects.equals(groupId, another.groupId);
        }
    }

    private enum GroupType {
        IMMUNE_SYSTEM,
        INFECTION
    }
}
