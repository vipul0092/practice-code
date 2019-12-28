package code.vipul.aoc2019.day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * IO Handler for Problem 25
 */
public class IOHandler {

    private static final Set<String> DANGEROUS_ITEMS;
    static final String SECURITY_CHECKPOINT = "== Security Checkpoint ==";
    static final String PRESSURE_SENSITIVE_FLOOR = "== Pressure-Sensitive Floor ==";

    static {
        DANGEROUS_ITEMS = new HashSet<>();
        DANGEROUS_ITEMS.add("infinite loop");
    }

    static List<Long> getItemPickupCommand(Collection<String> items) {
        List<Long> inputs = new ArrayList<>();
        items.forEach(item -> updateListWithCommandInput(inputs, takeItemCommand(item)));
        return inputs;
    }

    static List<Long> getItemPickupCommand(String item) {
        List<Long> inputs = new ArrayList<>();
        updateListWithCommandInput(inputs, takeItemCommand(item));
        return inputs;
    }

    static List<Long> getItemDropCommand(Collection<String> items) {
        List<Long> inputs = new ArrayList<>();
        items.forEach(item -> updateListWithCommandInput(inputs, dropItemCommand(item)));
        return inputs;
    }

    static List<Long> getMoveCommand(String[] directions) {
        List<Long> inputs = new ArrayList<>();
        Arrays.stream(directions)
                .filter(direction -> direction != null && !direction.equals(""))
                .forEach(direction -> updateListWithCommandInput(inputs, direction));
        return inputs;
    }

    static List<Long> getMoveCommand(String direction) {
        List<Long> inputs = new ArrayList<>();
        updateListWithCommandInput(inputs, direction);
        return inputs;
    }

    static List<Long> getInventoryCommand() {
        List<Long> inputs = new ArrayList<>();
        updateListWithCommandInput(inputs, "inv");
        return inputs;
    }

    public static void updateListWithCommandInput(List<Long> inputs, String command) {
        for (char ch : command.toCharArray()) {
            inputs.add((long) ch);
        }
        inputs.add(10L); //newline
    }

    private static String takeItemCommand(String item) {
        return "take " + item;
    }

    private static String dropItemCommand(String item) {
        return "drop " + item;
    }

    private static boolean isPressureSensitiveFloorFailure(String output) {
        return output.startsWith("A loud, robotic voice says \"Alert");
    }

    static Node parse(List<String> outputs) {
        String sectionName = "";
        boolean doorInputStarted = false;
        boolean itemsInputStart = false;
        List<String> doors = new ArrayList<>();
        List<String> items = new ArrayList<>();
        PressureSensitiveFloorResult psfResult = PressureSensitiveFloorResult.NONE;

        for (String output : outputs) {
            output = output.substring(0, output.length() - 1); // Remove the trailing \n

            if (isPressureSensitiveFloorFailure(output)) {
                psfResult = PressureSensitiveFloorResult.FAILURE;
                break;
            }

            if (output.startsWith("==")) {
                sectionName = output;
            } else if (output.equals("Doors here lead:")) {
                doorInputStarted = true;
            } else if (output.equals("Items here:")) {
                doorInputStarted = false;
                itemsInputStart = true;
            } else if (doorInputStarted) {
                if (output.startsWith("- ")) {
                    doors.add(output.substring(2)); // Skip the "- " part in the front
                } else {
                    doorInputStarted = false;
                }
            } else if (itemsInputStart) {
                if (output.startsWith("- ")) {
                    String item = output.substring(2); // Skip the "- " part in the front
                    if (!DANGEROUS_ITEMS.contains(item)) {
                        items.add(item);
                    }
                } else {
                    itemsInputStart = false;
                }
            }
        }

        if (sectionName.equals(PRESSURE_SENSITIVE_FLOOR) && psfResult != PressureSensitiveFloorResult.FAILURE) {
            psfResult = PressureSensitiveFloorResult.SUCCESS;
        }

        return new Node(sectionName, doors, items, psfResult);
    }

    public static Set<String> parseInventory(List<String> outputs) {
        Set<String> items = new HashSet<>();
        for (String output : outputs) {
            output = output.substring(0, output.length() - 1); // Remove the trailing \n
            if (output.startsWith("- ")) {
                String item = output.substring(2); // Skip the "- " part in the front
                items.add(item);
            }
        }
        return items;
    }

    static class Node {
        private final String name;
        private final List<String> doors;
        private final List<String> items;
        private final PressureSensitiveFloorResult psfResult;

        String name() {
            return name;
        }

        List<String> doors() {
            return doors;
        }

        List<String> items() {
            return items;
        }

        PressureSensitiveFloorResult getPsfResult() {
            return psfResult;
        }

        private Node(String name, List<String> doors, List<String> items, PressureSensitiveFloorResult psfResult) {
            this.name = name;
            this.doors = doors;
            this.items = items;
            this.psfResult = psfResult;
        }
    }

    public enum PressureSensitiveFloorResult {
        NONE,
        FAILURE,
        SUCCESS
    }
}
