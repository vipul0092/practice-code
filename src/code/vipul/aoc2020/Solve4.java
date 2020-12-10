package code.vipul.aoc2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2020.Inputs.INPUT_4;

/**
 * https://adventofcode.com/2020/day/4
 */
public class Solve4 {

    private final static Map<String, Integer> FIELD_MAP;
    private final static String BIRTH_YEAR = "byr";
    private final static String ISSUE_YEAR = "iyr";
    private final static String EXPIRATION_YEAR = "eyr";
    private final static String HEIGHT = "hgt";
    private final static String HAIR_COLOR = "hcl";
    private final static String EYE_COLOR = "ecl";
    private final static String PASSPORT_ID = "pid";
    private final static String COUNTRY_ID = "cid";
    private final static Integer COUNTRY_BITMAP = 128;
    private final static int FULL_BITMAP = 255;

    static {
        FIELD_MAP = new HashMap<>();
        FIELD_MAP.put(BIRTH_YEAR, 1);
        FIELD_MAP.put(ISSUE_YEAR, 2);
        FIELD_MAP.put(EXPIRATION_YEAR, 4);
        FIELD_MAP.put(HEIGHT, 8);
        FIELD_MAP.put(HAIR_COLOR, 16);
        FIELD_MAP.put(EYE_COLOR, 32);
        FIELD_MAP.put(PASSPORT_ID, 64);
        FIELD_MAP.put(COUNTRY_ID, COUNTRY_BITMAP);
    }

    public static void solve() {
        var inputRows = INPUT_4.split("\n");

        int valid = 0;
        int currentBitmap = 0;
        for (String inputRow : inputRows) {
            if (inputRow.equals("")) {
                if (currentBitmap == FULL_BITMAP || (currentBitmap + COUNTRY_BITMAP) == FULL_BITMAP) {
                    valid++;
                }
                currentBitmap = 0;
                continue;
            }

            String[] fields = inputRow.split(" ");

            for (String field : fields) {
                currentBitmap |= (FIELD_MAP.get(field.split(":")[0]));
            }
        }

        if (currentBitmap == FULL_BITMAP || (currentBitmap + COUNTRY_BITMAP) == FULL_BITMAP) {
            valid++;
        }

        System.out.println("Answer: " + valid); //222
    }

    public static void solvePart2() {
        var inputRows = INPUT_4.split("\n");

        int valid = 0;
        int currentBitmap = 0;
        for (String inputRow : inputRows) {
            if (inputRow.equals("")) {
                if (currentBitmap == FULL_BITMAP || (currentBitmap + COUNTRY_BITMAP) == FULL_BITMAP) {
                    valid++;
                }
                currentBitmap = 0;
                continue;
            }

            String[] fieldValues = inputRow.split(" ");

            for (String fieldValue : fieldValues) {
                String field = fieldValue.split(":")[0];
                String value = fieldValue.split(":")[1];
                boolean isValid = false;

                switch (field) {
                    case BIRTH_YEAR:
                        isValid = validateBirthYear(value);
                        break;
                    case ISSUE_YEAR:
                        isValid = validateIssueYear(value);
                        break;
                    case EXPIRATION_YEAR:
                        isValid = validateExpirationYear(value);
                        break;
                    case HEIGHT:
                        isValid = validateHeight(value);
                        break;
                    case HAIR_COLOR:
                        isValid = validateHairColor(value);
                        break;
                    case EYE_COLOR:
                        isValid = validateEyeColor(value);
                        break;
                    case PASSPORT_ID:
                        isValid = validatePassportId(value);
                        break;
                    case COUNTRY_ID:
                        isValid = true;
                        break;
                    default:
                        break;
                }

                if (isValid) {
                    currentBitmap |= (FIELD_MAP.get(field));
                } else {
                    break;
                }
            }
        }
        System.out.println("Answer: " + valid); //140
    }

    private static boolean validateBirthYear(String value) {
        int year = Integer.parseInt(value);
        return year >= 1920 && year <= 2002;
    }

    private static boolean validateIssueYear(String value) {
        int year = Integer.parseInt(value);
        return year >= 2010 && year <= 2020;
    }

    private static boolean validateExpirationYear(String value) {
        int year = Integer.parseInt(value);
        return year >= 2020 && year <= 2030;
    }

    private static boolean validateHeight(String value) {
        if (!(value.endsWith("in") || value.endsWith("cm"))) {
            return false;
        }

        boolean isCm = value.endsWith("cm");
        int numericValue = Integer.parseInt(value.substring(0, value.length() - 2));

        return isCm ? (numericValue >= 150 && numericValue <= 193) : (numericValue >= 59 && numericValue <= 76);
    }

    private static final Set<Character> VALID_HAIR_COLOR_CHARS = Set.of('#', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f');

    private static boolean validateHairColor(String value) {
        Set<Character> valueChars = new HashSet<>();
        for (char ch : value.toCharArray()) {
            valueChars.add(ch);
        }
        return value.charAt(0) == '#' && VALID_HAIR_COLOR_CHARS.containsAll(valueChars);
    }

    private static final Set<String> VALID_EYE_COLORS = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    private static boolean validateEyeColor(String value) {
        return VALID_EYE_COLORS.contains(value);
    }

    private static boolean validatePassportId(String value) {
        return value.length() == 9 && Integer.parseInt(value) != 0;
    }
}
