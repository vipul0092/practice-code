package code.vipul.aoc2019;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://adventofcode.com/2019/day/14
 */
public class Solve14 {

    static String input = "8 LHFV => 3 PMVMQ\n" +
            "2 ZXNM, 1 PSVLS, 4 GRDNT, 26 GLZH, 3 VHJX, 16 BGPF, 1 LHVTN => 4 BTQL\n" +
            "10 NKHSG, 20 FCPC, 11 GRDNT => 5 HDJB\n" +
            "6 WPZN, 1 LHFV => 7 BGPF\n" +
            "1 WDXT, 1 PLCNZ => 3 QHFKR\n" +
            "12 LCHZ => 1 TPXCK\n" +
            "11 LSNG => 4 XFGH\n" +
            "195 ORE => 4 GRNC\n" +
            "8 XFGQ => 1 GRDNT\n" +
            "1 FBRG => 5 LCHZ\n" +
            "7 XZBJ, 8 RSZF, 9 SVDX => 9 LWDP\n" +
            "20 WDXT => 5 RQFRT\n" +
            "1 LXQWG, 1 GLZH => 6 SDLJ\n" +
            "4 XFGH => 1 GCZLZ\n" +
            "1 WPZN => 1 FBRG\n" +
            "19 XZBJ => 5 WXGV\n" +
            "1 GDXC => 6 WDXT\n" +
            "1 WXGV, 1 NKHSG, 2 LWDP => 5 FCNPB\n" +
            "4 LWDP, 5 BGPF => 9 KLRB\n" +
            "1 GMRN => 4 GLZH\n" +
            "1 RQFRT => 5 SVDX\n" +
            "2 HWKG => 7 LHFV\n" +
            "2 LCHZ, 13 JTJT, 10 TPXCK => 3 RSZF\n" +
            "29 MZTVH => 6 TSGR\n" +
            "9 NRFLK, 1 SVDX => 5 NKHSG\n" +
            "123 ORE => 9 GDXC\n" +
            "1 PZPBV, 21 PMVMQ, 1 GCZLZ => 8 SKZGB\n" +
            "3 GRNC, 5 GDXC => 8 QZVM\n" +
            "6 VTDQ, 13 TCQW, 3 FCNPB, 48 PSVLS, 3 RLNF, 73 BTQL, 5 MHRVG, 26 BGPF, 26 HDJB, 5 XFGQ, 6 HTFL => 1 FUEL\n" +
            "5 QZVM, 2 JTJT => 1 PXKHG\n" +
            "3 LSNG, 1 PMVMQ => 8 VTDQ\n" +
            "31 XFGH => 1 FCPC\n" +
            "9 PSVLS => 8 FWGTF\n" +
            "1 GRNC => 3 WPZN\n" +
            "16 JBXDX, 4 GRNC => 6 HWKG\n" +
            "1 SKZGB, 5 RSZF => 4 XZBJ\n" +
            "134 ORE => 9 CTDRZ\n" +
            "1 SVDX, 2 TPXCK => 7 JTJT\n" +
            "6 RQFRT, 4 KBCW => 3 BGNLR\n" +
            "12 KLRB, 12 LHFV => 4 HTFL\n" +
            "2 GMRN => 6 XFGQ\n" +
            "16 WNSW, 12 SKZGB => 8 LXQWG\n" +
            "2 NRFLK, 2 CTDRZ => 9 JBXDX\n" +
            "1 PZPBV => 8 RLNF\n" +
            "2 JTJT, 5 GCZLZ => 3 WNSW\n" +
            "5 WXGV, 2 LCHZ => 2 SCDS\n" +
            "1 QHFKR => 3 GMRN\n" +
            "10 JTJT, 2 HRCG => 8 KBCW\n" +
            "7 HWKG => 4 PSVLS\n" +
            "7 WNSW, 1 PXKHG, 3 BGNLR => 9 MZTVH\n" +
            "15 TPXCK, 11 LHFV => 5 HRCG\n" +
            "1 LSNG, 1 HWKG => 3 PZPBV\n" +
            "7 BGPF => 9 PLCNZ\n" +
            "1 ZGWT => 6 ZXNM\n" +
            "26 NKHSG, 1 LHFV, 2 JTJT, 26 WXGV, 6 SDLJ, 1 KLRB, 1 TSGR => 8 TCQW\n" +
            "154 ORE => 4 NRFLK\n" +
            "1 GMRN => 3 VHJX\n" +
            "5 QZVM, 3 GDXC => 7 LSNG\n" +
            "5 WNSW => 5 ZGWT\n" +
            "6 QHFKR, 8 PZPBV, 10 FBRG, 13 FWGTF, 1 LHVTN, 4 SCDS, 8 VHJX, 7 TSGR => 6 MHRVG\n" +
            "12 GLZH => 5 LHVTN";

    private static final String FUEL = "FUEL";
    private static final String ORE = "ORE";

    private static Map<String, Compound> compounds;
    private static Map<String, Long> extra;

    public static void solve() {
        compounds = new HashMap<>();
        extra = new TreeMap<>();
        parseInput();

        long answer = dive(FUEL, 1);
        System.out.println("Answer: " + answer); // 397771
    }

    private static final Long MAX_ORE = 1000000000000L;

    public static void solvePart2() {
        compounds = new HashMap<>();
        extra = new TreeMap<>();
        parseInput();

        long low = 0;
        long high = MAX_ORE;

        // By binary search we are trying to find the fuel value for which the ore usage is closest to MAX_ORE
        while (low < high) {
            long mid = (low + high + 1) / 2;

            long ore = dive(FUEL, mid);
            if (ore <= MAX_ORE) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Answer: " + low); // 3126714
    }

    private static long dive(String productName, long quantityRequired) {

        if (productName.equals(ORE)) {
            return quantityRequired;
        }

        Compound compound = compounds.get(productName);

        long outputQuantity = compound.outputQuantity;
        long multiplier = 1;
        if (outputQuantity < quantityRequired) {
            multiplier = quantityRequired % outputQuantity == 0
                    ? (quantityRequired / outputQuantity)
                    : (quantityRequired / outputQuantity) + 1;
        }

        long extraQuantity = (multiplier * outputQuantity) - quantityRequired;

        if (extraQuantity > 0) {
            extra.putIfAbsent(productName, 0L);
            extra.put(productName, extra.get(productName) + extraQuantity);
        }

        long sum = 0;
        for (Map.Entry<String, Integer> reactants : compound.reactants.entrySet()) {
            long reactantQuantity = reactants.getValue() * multiplier;
            String reactantName = reactants.getKey();

            if (extra.containsKey(reactantName) && extra.get(reactantName) > 0) {
                long extraQuantityToUse = reactantQuantity >= extra.get(reactantName)
                        ? extra.get(reactantName) : reactantQuantity;
                reactantQuantity -= extraQuantityToUse;

                extra.put(reactantName, extra.get(reactantName) - extraQuantityToUse);
            }

            if (reactantQuantity == 0) {
                continue;
            }
            sum += dive(reactantName, reactantQuantity);
        }
        return sum;
    }

    private static void parseInput() {
        String[] reactions = input.split("\n");

        for (String reaction : reactions) {
            String[] reactionBreakUp = reaction.split("=>");
            String[] reactants = reactionBreakUp[0].split(",");
            String productString = reactionBreakUp[1].trim();

            String[] productBreakUp = productString.split(" ");
            int productQuantity = Integer.parseInt(productBreakUp[0]);
            String productName = productBreakUp[1];

            compounds.computeIfAbsent(productName, (n) -> new Compound());

            Compound product = compounds.get(productName);
            product.name = productName;
            if (product.outputQuantity != null) {
                throw new RuntimeException("I dont handle that dude!");
            }
            product.outputQuantity = productQuantity;

            for (String reactant : reactants) {
                reactant = reactant.trim();
                String[] reactantWithQuantity = reactant.split(" ");

                product.reactants.put(reactantWithQuantity[1].trim(),
                        Integer.parseInt(reactantWithQuantity[0].trim()));
            }

        }
    }

    public static class Compound {
        private String name;
        private Integer outputQuantity = null;
        private Map<String, Integer> reactants = new HashMap<>();

        public boolean isProducedFromOre() {
            return reactants.size() == 1
                    && reactants.containsKey(ORE);
        }
    }

}
