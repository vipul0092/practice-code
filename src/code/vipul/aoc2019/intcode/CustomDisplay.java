package code.vipul.aoc2019.intcode;

/**
 * Interface modelling an entity connected to the computer display used for specific custom "graphics"
 */
public interface CustomDisplay {

    void acceptOutput(Long value);
}
