package code.vipul.aoc2019.day25;

import code.vipul.aoc2019.intcode.Computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static code.vipul.aoc2019.day25.IOHandler.PRESSURE_SENSITIVE_FLOOR;
import static code.vipul.aoc2019.day25.IOHandler.SECURITY_CHECKPOINT;
import static code.vipul.aoc2019.day25.IOHandler.getInventoryCommand;
import static code.vipul.aoc2019.day25.IOHandler.getItemDropCommand;
import static code.vipul.aoc2019.day25.IOHandler.getItemPickupCommand;
import static code.vipul.aoc2019.day25.IOHandler.getMoveCommand;

/**
 * https://adventofcode.com/2019/day/25
 */
public class Solve25 {

    public static void playInteractive() {

        String input = Inputs.INPUT1;

        Computer computer = Computer.make();
        computer.loadProgramInMemory(input);
        computer.getOutput().setAsciiFlag(true);
        computer.getOutput().setNewlineSeparation(true);
        computer.getInput().makeInteractive();

        // Start the program and play from console
        computer.execute();
    }

    public static void autoSolve() {
        String input = Inputs.INPUT8;

        visitedNodes = new HashSet<>();
        connections = new HashMap<>();

        Computer computer = Computer.make();
        computer.loadProgramInMemory(input);
        computer.getOutput().setAsciiFlag(true);
        computer.getOutput().setNewlineSeparation(true);

        // Start the program
        computer.execute();
        List<String> outputs = computer.getOutput().getOutputLines();
        IOHandler.Node node = IOHandler.parse(outputs);
        // Take a walk around all the rooms, noting down:
        // 1. the path to the check point
        // 2. direction from the check point to the pressure sensitive floor
        walk(computer, "", node, "");

        // walk to the check point
        computer.giveInputAndExecute(IOHandler.getMoveCommand(securityCheckPointPath.split(",")));
        computer.getOutput().clearLines();

        // Now try all iterations until the check point opens up
        List<String> items = new ArrayList<>(fetchInventoryItems(computer));
        tryAllIterations(computer, items);
    }

    private static Set<String> visitedNodes; // Nodes visited until now during the walk
    private static Map<String, Map<String, String>> connections; // node -> map of ( neighbour node, direction taken )
    private static String securityCheckPointPath = ""; // Path from the fist node to the security checkpoint (scp)
    private static String scpDirectionToPsf = ""; // Direction to go from scp to pressure sensitive floor

    private static void walk(Computer computer, String prevNode, IOHandler.Node node, String path) {

        String currentNodeName = node.name();
        if (visitedNodes.contains(currentNodeName)) {
            // Go back to prev node
            String prevDoor = connections.get(currentNodeName).get(prevNode);
            computer.giveInputAndExecute(getMoveCommand(prevDoor));
            computer.getOutput().clearLines();
            return;
        } else {
            visitedNodes.add(currentNodeName);
        }

        if (currentNodeName.equals(SECURITY_CHECKPOINT)) {
            securityCheckPointPath = path;
        }

        // take all items
        if (node.items().size() > 0) {
            for (String item : node.items()) {
                // The backup of the computer to resume execution in case of mishaps
                Computer currentBackup = computer.takeSnapshot(); // Take a backup
                computer.giveInputAndExecute(getItemPickupCommand(item));
                computer.getOutput().clearLines();

                if (computer.hasHalted()) { // Taking the item led to the computer halting
                    computer.resetStateTo(currentBackup);
                } else {
                    Set<String> items = fetchInventoryItems(computer);
                    if (!items.contains(item)) { // Taking the item led to some weird state
                        computer.resetStateTo(currentBackup);
                    }
                }
            }
        }

        // move around the neighbours
        for (String door : node.doors()) {
            computer.giveInputAndExecute(getMoveCommand(door)); // We have moved to the node behind the "door"
            IOHandler.Node newNode = IOHandler.parse(computer.getOutput().getOutputLines());

            connections.computeIfAbsent(currentNodeName, (i) -> new HashMap<>());
            connections.get(currentNodeName).put(newNode.name(), door);

            if (!newNode.name().equals(PRESSURE_SENSITIVE_FLOOR)) {
                walk(computer, currentNodeName, newNode, path + "," + door);
            } else {
                scpDirectionToPsf = door;
            }
        }

        // go to previous node
        if (prevNode.length() > 0) {
            String prevDoor = connections.get(currentNodeName).get(prevNode);
            computer.giveInputAndExecute(getMoveCommand(prevDoor));
            computer.getOutput().clearLines();
        }
    }

    private static void tryAllIterations(Computer computer, List<String> items) {
        // first drop all objects
        computer.giveInputAndExecute(getItemDropCommand(items));
        computer.getOutput().clearLines();

        int limit = 1 << items.size();

        for (int i = 1; i <= limit; i++) {
            List<String> currentSelection = new ArrayList<>();
            for (int j = 0; j < items.size(); j++) {
                boolean isBitSet = (i & (1 << j)) != 0;
                if (isBitSet) {
                    currentSelection.add(items.get(j));
                }
            }

            // take the selected objects
            computer.giveInputAndExecute(IOHandler.getItemPickupCommand(currentSelection));
            computer.getOutput().clearLines();

            // try to go the pressure sensitive floor and evaluate the result
            computer.giveInputAndExecute(getMoveCommand(scpDirectionToPsf));
            List<String> outputs = computer.getOutput().getOutputLines();
            IOHandler.Node nodeResult = IOHandler.parse(outputs);

            if (nodeResult.getPsfResult() == IOHandler.PressureSensitiveFloorResult.SUCCESS) {
                // Woohoo! we found the combo
                break;
            }

            // drop all if failed
            computer.giveInputAndExecute(getItemDropCommand(currentSelection));
            computer.getOutput().clearLines();
        }
    }

    private static Set<String> fetchInventoryItems(Computer computer) {
        computer.giveInputAndExecute(getInventoryCommand());
        return IOHandler.parseInventory(computer.getOutput().getOutputLines());
    }
}
