package code.vipul.aoc2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * https://adventofcode.com/2019/day/7
 */
public class Solve7 {

    private static String input = "3,8,1001,8,10,8,105,1,0,0,21,34,59,68,85,102,183,264,345,426,99999,3,9,101,3,9,9,102,3,9,9,4,9,99,3,9,1002,9,4,9,1001,9,2,9,1002,9,2,9,101,5,9,9,102,5,9,9,4,9,99,3,9,1001,9,4,9,4,9,99,3,9,101,3,9,9,1002,9,2,9,1001,9,5,9,4,9,99,3,9,1002,9,3,9,1001,9,5,9,102,3,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,99";

    private static int ctr = 0;
    private static int[] inputArray;

    public static void solve() {
        // input = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        Set<Integer> phaseSequence;
//        phaseSequence.add(0);
//        phaseSequence.add(1);
//        phaseSequence.add(2);
//        phaseSequence.add(3);
//        phaseSequence.add(4);

        int answer = 0;

        for (int i = 0; i< 5; i++) {
            phaseSequence = new LinkedHashSet<>();
            phaseSequence.add(i);
            for (int j = 0; j< 5; j++) {
                if (phaseSequence.contains(j)) {
                    continue;
                }
                phaseSequence.add(j);
                for (int k = 0; k< 5; k++) {
                    if (phaseSequence.contains(k)) {
                        continue;
                    }
                    phaseSequence.add(k);
                    for (int l = 0; l< 5; l++) {
                        if (phaseSequence.contains(l)) {
                            continue;
                        }
                        phaseSequence.add(l);
                        for (int m = 0; m< 5; m++) {
                            if (phaseSequence.contains(m)) {
                                continue;
                            }
                            phaseSequence.add(m);
                            printPhaseSequence(phaseSequence);
                            int finalOutput = getSignal(phaseSequence, true, 0);
                            answer = Math.max(answer, finalOutput);
                            phaseSequence.remove(m);
                        }
                        phaseSequence.remove(l);
                    }
                    phaseSequence.remove(k);
                }
                phaseSequence.remove(j);
            }
            phaseSequence.remove(i);
        }

        System.out.println("Final: " + answer); // 24405
    }

    public static void solvePart2() {
//        input = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54," +
//                "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4," +
//                "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"; // 18216
        times = 0;
        Set<Integer> phaseSequence = new LinkedHashSet<>();
        phaseSequence.add(9);
        phaseSequence.add(8);
        phaseSequence.add(7);
        phaseSequence.add(6);
        phaseSequence.add(5);

        int finalOutput = 0;

        for (int i = 5; i< 10; i++) {
            phaseSequence = new LinkedHashSet<>();
            phaseSequence.add(i);
            for (int j = 5; j< 10; j++) {
                if (phaseSequence.contains(j)) {
                    continue;
                }
                phaseSequence.add(j);
                for (int k = 5; k< 10; k++) {
                    if (phaseSequence.contains(k)) {
                        continue;
                    }
                    phaseSequence.add(k);
                    for (int l = 5; l< 10; l++) {
                        if (phaseSequence.contains(l)) {
                            continue;
                        }
                        phaseSequence.add(l);
                        for (int m = 5; m< 10; m++) {
                            if (phaseSequence.contains(m)) {
                                continue;
                            }
                            phaseSequence.add(m);
                            int answer = 0;
                            int output = 0;
                            while (true) {
                                output = getSignal(phaseSequence, false, output);
                                printPhaseSequence(phaseSequence);
                                answer = Math.max(answer, output);
                                if (systems.get(4).hasHalted()) {
                                    break;
                                }
                            }
                            finalOutput = Math.max(answer, finalOutput);
                            systems = new HashMap<>(); // Reset the system to feed them another sequence
                            times = 0;
                            phaseSequence.remove(m);
                        }
                        phaseSequence.remove(l);
                    }
                    phaseSequence.remove(k);
                }
                phaseSequence.remove(j);
            }
            phaseSequence.remove(i);
        }

        System.out.println("Final: " + finalOutput); // 8271623
    }

    private static void printPhaseSequence(Set<Integer> phaseSequence) {
        List<String> output = phaseSequence.stream().map(s -> String.valueOf(s)).collect(toList());
        System.out.print("Phase Sequence: " + String.join(", ", output));
        System.out.println();
    }

    private static Map<Integer, IntCode> systems = new HashMap<>();
    private static int times = 0;
    private static int getSignal(Set<Integer> phaseSequence, boolean generateNew, int inputSignal) {
        times++;
        Integer[] phaseArr = phaseSequence.toArray(new Integer[0]);
        for (int i = 0; i < phaseArr.length; i++) {
            Integer phaseInput = phaseArr[i];
            if (generateNew) {
                systems.put(i, IntCode.getInstance(input));
            } else {
                systems.putIfAbsent(i, IntCode.getInstance(input));
            }
            IntCode code = systems.get(i);
            code.enablePauses();
            List<Integer> inputs = new ArrayList<>();

            if (generateNew || times == 1) { // only pass it the first time
                inputs.add(phaseInput);
            }
            inputs.add(inputSignal);
            code.setInputArray(inputs);
            code.evaluateStreamWithModes();

            String output = code.getAsciiOutput();
            inputSignal = Integer.parseInt(output);
        }
        return inputSignal;
    }
}
