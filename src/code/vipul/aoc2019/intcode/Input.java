package code.vipul.aoc2019.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static code.vipul.aoc2019.day25.IOHandler.updateListWithCommandInput;

public class Input {

    private List<Long> inputToGive;
    private int currentInputIndex;
    private boolean isInteractive = false;
    private CustomInput customInput;

    public Input() {
        inputToGive = new ArrayList<>();
        currentInputIndex = 0;
    }

    public Input takeSnapshot() {
        Input snap = new Input();
        snap.inputToGive = new ArrayList<>(this.inputToGive);
        snap.currentInputIndex = this.currentInputIndex;
        snap.isInteractive = this.isInteractive;
        return snap;
    }

    public void resetStateTo(Input input) {
        this.inputToGive = input.inputToGive;
        this.currentInputIndex = input.currentInputIndex;
        this.isInteractive = input.isInteractive;
    }

    public void enqueueInput(Long input) {
        inputToGive = new ArrayList<>();
        inputToGive.add(input);
        currentInputIndex = 0;
    }

    public void enqueueInput(List<Long> input) {
        inputToGive = new ArrayList<>();
        inputToGive.addAll(input);
        currentInputIndex = 0;
    }

    public void attachCustomInput(CustomInput input) {
        this.customInput = input;
    }

    public void makeInteractive() {
        isInteractive = true;
    }

    public long fetchInput() {
        if (customInput != null) {
            return customInput.fetchInput();
        }
        if (currentInputIndex >= inputToGive.size()) {
            if (!isInteractive) {
                throw new NoMoreInputException();
            } else {
                Scanner scanner = new Scanner(System.in);
                String s = scanner.nextLine();
                inputToGive = new ArrayList<>();
                currentInputIndex = 0;
                updateListWithCommandInput(inputToGive, s);
            }
        }
        return inputToGive.get(currentInputIndex++);
    }

    static class NoMoreInputException extends RuntimeException {
    }
}
