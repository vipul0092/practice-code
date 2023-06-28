package code.vipul.codegladitor;

import java.util.*;
import java.util.stream.Collectors;
import code.vipul.tree.RBBST;

/**
 * Created by vgaur created on 26/06/23
 */
public class CG2023_SemiFinal {

    public static void solvelocal2() {
        internalSolve2(1, 304, "3030");
    }

    //AC
    public static void solve1() {

        Scanner in = new Scanner(System.in);
        String npString = in.nextLine();
        String nistring = in.nextLine();
        String pistring = in.nextLine();


        List<Integer> nis = Arrays.stream(nistring.split(" "))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        List<Integer> pis = Arrays.stream(pistring.split(" "))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        internalSolve1(nis, pis);
    }

    public static void solvelocal1() {
        internalSolve1(List.of(120, 100, 100, 90, 60), List.of(40, 40, 80));
    }

    private static void internalSolve1(List<Integer> nis, List<Integer> pis) {
        RBBST<Integer, Integer> bst = new RBBST<>();

        for (int ni : nis) {
            if (!bst.contains(ni)) {
                bst.put(ni, 0);
            }
        }

        for (int pi : pis) {
            if (!bst.contains(pi)) {
                bst.put(pi, 0);
                System.out.println(bst.size() - bst.rank(pi));
            } else {
                System.out.println(bst.size() - bst.rank(pi));
            }
        }
    }

    //AC
    public static void solve2() {

        Scanner in = new Scanner(System.in);
        String nkString = in.nextLine();
        String numString = in.nextLine();

        List<Long> nk = Arrays.stream(nkString.split(" "))
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());
        long n = nk.get(0);
        long k = nk.get(1);

        internalSolve2(n, k, numString);
    }

    private static void internalSolve2(long n, long k, String numString) {
        long mod = 1000000007;
        long[] digits = new long[numString.length()];
        for (int i = 0; i < numString.length(); i++) {
            digits[i] = numString.charAt(i) - 48;
        }

        Map<Long, Long> map = new HashMap<>();
        map.put(digits[0], 1L);

        boolean noAnswer = digits.length == 1 && k > digits[0];
        for (int i = 1; i < digits.length; i++) {
            Map<Long, Long> currMap = new HashMap<>();
            long digit = digits[i];

            if (digit > k) {
                noAnswer = true;
                break;
            }

            for (Map.Entry<Long, Long> entry : map.entrySet()) {
                long val = entry.getKey();
                long count = entry.getValue();

                long newVal = (val*10) + digit;
                if (newVal <= k) {
                    currMap.put(newVal,
                            (currMap.getOrDefault(newVal, 0L) + count) % mod);
                }

                if (digit != 0) {
                    currMap.put(digit,
                            (currMap.getOrDefault(digit, 0L) + count) % mod);
                }
            }
            map = currMap;
        }

        long answer = 0;
        if (noAnswer) {
            System.out.println(answer);
        } else {

            for (long v : map.values()) {
                answer = (answer + v) % mod;
            }

            System.out.println(answer);
        }
    }
}
