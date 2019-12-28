package code.vipul.aoc2019.intcode;

import java.util.ArrayList;
import java.util.List;

public class Output {

    private List<Long> receivedOutput;
    private boolean showAscii;
    private boolean separateByNewline;
    private StringBuilder stringOutput;
    private StringBuilder overallOutput;
    private List<String> lines;

    public Output() {
        stringOutput = new StringBuilder();
        overallOutput = new StringBuilder();
        receivedOutput = new ArrayList<>();
        lines = new ArrayList<>();
        showAscii = false;
        separateByNewline = false;
    }

    public Output takeSnapshot() {
        Output snap = new Output();
        snap.receivedOutput = new ArrayList<>(receivedOutput);
        snap.showAscii = this.showAscii;
        snap.separateByNewline = this.separateByNewline;
        snap.stringOutput = new StringBuilder().append(stringOutput);
        snap.overallOutput = new StringBuilder().append(overallOutput);
        snap.lines = new ArrayList<>(lines);
        return snap;
    }

    public void resetStateTo(Output output) {
        this.receivedOutput = output.receivedOutput;
        this.showAscii = output.showAscii;
        this.separateByNewline = output.separateByNewline;
        this.stringOutput = output.stringOutput;
        this.overallOutput = output.overallOutput;
        this.lines = output.lines;
    }

    public void setAsciiFlag(boolean flag) {
        this.showAscii = flag;
    }

    public void setNewlineSeparation(boolean separateByNewline) {
        this.separateByNewline = separateByNewline;
    }

    public void pushOutput(long value) {
        receivedOutput.add(value);
        if (showAscii) {
            overallOutput.append((char) value);
        } else {
            overallOutput.append(value);
        }
        if (separateByNewline && value == 10) {
            System.out.print(overallOutput.toString());
            String output = overallOutput.toString();
            if (output.length() > 0 && !(output.length() == 1 && output.equals("\n"))) {
                lines.add(overallOutput.toString());
            }
            overallOutput = new StringBuilder();
        }
    }

    public void flush() {
        if (receivedOutput.size() == 0) {
            return;
        }
        stringOutput = new StringBuilder();
        receivedOutput.forEach(o -> {
            if (showAscii) {
                stringOutput.append((char) o.intValue());
            } else {
                stringOutput.append(o);
            }
        });
        receivedOutput = new ArrayList<>();
        if (!separateByNewline) {
            System.out.println("Flushed Output: " + stringOutput.toString());
        }
    }

    public String getOutput() {
        return stringOutput.toString();
    }

    public String getFullOutput() {
        return overallOutput.toString();
    }

    public List<String> getOutputLines() {
        List<String> toReturn = new ArrayList<>(lines);
        lines = new ArrayList<>();
        return toReturn;
    }

    public void clearLines() {
        lines = new ArrayList<>();
    }
}
