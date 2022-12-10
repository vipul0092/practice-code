package code.vipul.aoc2022.emulator;

import java.util.List;
import java.util.Set;

/**
 * Created by vgaur created on 10/12/22
 */
public class VideoSystem {

    private static final int ROWS = 6;
    private static final int COLS = 40;
    private static final Set<Integer> CYCLES = Set.of(20, 60, 100, 140, 180, 220);
    private static final String NOOP = "noop";
    private static final String ADDX = "addx";

    private int registerX;
    private int cycle;
    private final char[][] crt;
    private int prow;
    private int pcol;
    private int sum = 0;

    public VideoSystem() {
        registerX = 1;
        cycle = 0;
        crt = new char[ROWS][COLS];
        prow = 0;
        pcol = 0;
    }

    public int getSum() {
        return sum;
    }

    public void displayCrt() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(crt[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void executeProgram(List<String> program) {
        for (int i = 0; i < program.size(); i++) {
            handleInstruction(program.get(i));
        }
    }

    private void handleInstruction(String instruction) {
        if (instruction.startsWith(NOOP)) {
            cycle++;
            renderPixel();
            updateSum();
        } else if (instruction.startsWith(ADDX)) {
            cycle++;
            renderPixel();
            updateSum();

            cycle++;
            renderPixel();
            updateSum();
            registerX += Integer.parseInt(instruction.split(" ")[1]);
        }
    }

    private void renderPixel() {
        if (pcol == COLS) {
            prow++;
            pcol = 0;
        }
        if (prow >= ROWS) {
            return;
        }
        Set<Integer> sprite = Set.of(registerX, registerX - 1, registerX + 1);
        crt[prow][pcol] = sprite.contains(pcol) ? '#' : '.';
        pcol++;
    }

    private void updateSum() {
        if (CYCLES.contains(cycle)) {
            sum += (registerX * cycle);
        }
    }
}
