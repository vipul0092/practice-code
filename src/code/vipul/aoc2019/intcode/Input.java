package code.vipul.aoc2019.intcode;

import java.util.ArrayList;
import java.util.List;

public class Input {

    private List<Long> inputToGive;
    private int currentInputIndex;

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

    public long fetchInput() {
        if (currentInputIndex >= inputToGive.size()) {
            throw new NoMoreInputException();
        }
        return inputToGive.get(currentInputIndex++);
    }

    static class NoMoreInputException extends RuntimeException {
    }
}
