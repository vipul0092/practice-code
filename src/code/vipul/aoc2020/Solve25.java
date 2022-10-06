package code.vipul.aoc2020;

/**
 * https://adventofcode.com/2020/day/25
 */
public class Solve25 {
    private static final long KEY_SUBJECT_NUMBER = 7;
    private static final long MOD = 20201227;

    private static final String INPUT_25 = "1965712\n" +
            "19072108";

    public static void solve() {
        String[] nos = INPUT_25.split("\n");

        long cardPublicKey = Long.parseLong(nos[0]);
        long doorPublicKey = Long.parseLong(nos[1]);

        long cardLoops = calculateLoopSize(cardPublicKey, KEY_SUBJECT_NUMBER);
        long doorLoops = calculateLoopSize(doorPublicKey, KEY_SUBJECT_NUMBER);

        long encryptionKey = cardLoops > doorLoops
                ? transform(cardPublicKey, doorLoops)
                : transform(doorPublicKey, cardLoops);

        System.out.println("Encryption Key: " + encryptionKey); //16881444
    }

    private static long calculateLoopSize(long key, long subjectNumber) {
        long value = 1;
        long loops = 0;
        while(value != key) {
            value *= subjectNumber;
            if (value >= MOD) {
                value %= MOD;
            }
            loops++;
        }

        return loops;
    }

    private static long transform(long subjectNumber, long loopSize) {
        long value = 1;
        while(loopSize-- > 0) {
            value *= subjectNumber;
            if (value >= MOD) {
                value %= MOD;
            }
        }
        return value;
    }
}
