package code.vipul.aoc2019.intcode;

/**
 * State of the CPU
 */
public enum CpuState {
    BOOTED,
    PROGRAM_LOADED,
    EXECUTING,
    EXECUTION_PAUSED,
    HALTED;
}
