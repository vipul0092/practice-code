package code.vipul.aoc2021;

import code.vipul.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vgaur created on 01/01/23
 * https://adventofcode.com/2021/day/16
 */
public class Solve16 {

    private static final String INPUT = "9C0141080250320F1802104A08";
    private static final int LITERAL_CHUNK_SIZE = 5;
    private static final Map<Character, String> BINARY_CONVERTER;
    static {
        BINARY_CONVERTER = new HashMap<>();
        BINARY_CONVERTER.put('0', "0000");
        BINARY_CONVERTER.put('1', "0001");
        BINARY_CONVERTER.put('2', "0010");
        BINARY_CONVERTER.put('3', "0011");
        BINARY_CONVERTER.put('4', "0100");
        BINARY_CONVERTER.put('5', "0101");
        BINARY_CONVERTER.put('6', "0110");
        BINARY_CONVERTER.put('7', "0111");
        BINARY_CONVERTER.put('8', "1000");
        BINARY_CONVERTER.put('9', "1001");
        BINARY_CONVERTER.put('A', "1010");
        BINARY_CONVERTER.put('B', "1011");
        BINARY_CONVERTER.put('C', "1100");
        BINARY_CONVERTER.put('D', "1101");
        BINARY_CONVERTER.put('E', "1110");
        BINARY_CONVERTER.put('F', "1111");
    }

    public static void solve() {
        String input = Inputs.INPUT_16;
        //String input = INPUT;

        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            sb.append(BINARY_CONVERTER.get(ch));
        }
        char[] bits = sb.toString().toCharArray();
        Packet packet = parse(bits, 0).left();
        System.out.println("Part 1: " + packet.getVersionSum());
        System.out.println("Part 2: " + packet.getValue());
    }

    private static Pair<Packet, Integer> parse(char[] bits, int currentPos) {
        long version = number(bits, currentPos, 3);
        currentPos += 3;
        long type = number(bits, currentPos, 3);
        currentPos += 3;

        Packet packet = new Packet(version, type);
        if (packet.isLiteralPacket()) {
            StringBuilder lit = new StringBuilder();
            while(true) {
                boolean isLastChunk = bits[currentPos++] == '0';
                int chunkSize = LITERAL_CHUNK_SIZE - 1; //Skip the first bit
                while(chunkSize-- > 0) {
                    lit.append(bits[currentPos++]);
                }
                if (isLastChunk) {
                    break;
                }
            }
            long literal = number(lit.toString().toCharArray());
            packet.setLiteral(literal);
        } else {
            int lengthType = bits[currentPos++] - '0';
            if (lengthType == 0) {
                long bitNum = number(bits, currentPos, 15);
                currentPos += 15;
                long finalPos = currentPos + bitNum;
                while(currentPos < finalPos) {
                    var parseResult = parse(bits, currentPos);
                    packet.addPacket(parseResult.left());
                    currentPos = parseResult.right();
                }
            } else {
                long numPackets = number(bits, currentPos, 11);
                currentPos += 11;
                while(numPackets-- > 0) {
                    var parseResult = parse(bits, currentPos);
                    packet.addPacket(parseResult.left());
                    currentPos = parseResult.right();
                }
            }
        }
        return Pair.of(packet, currentPos);
    }

    private static class Packet {
        private final long version;
        private final long type;
        private List<Packet> subPackets;
        private long literal;

        public Packet(long version, long type) {
            this.version = version;
            this.type = type;
            if (!isLiteralPacket()) {
                subPackets = new ArrayList<>();
                literal = -1;
            }
        }

        public void addPacket(Packet packet) {
            this.subPackets.add(packet);
        }

        public void setLiteral(long l) {
            this.literal = l;
        }

        private boolean isLiteralPacket() {
            return type == 4;
        }

        public long getVersionSum() {
            if (isLiteralPacket()) {
                return version;
            } else {
                return subPackets.stream().mapToLong(p -> p.getVersionSum()).sum() + version;
            }
        }

        public long getValue() {
            if (type == 0) {
                long sum = 0;
                for (var packet : subPackets) {
                    sum += packet.getValue();
                }
                return sum;
            } else if (type == 1) {
                long prod = 1;
                for (var packet : subPackets) {
                    prod *= packet.getValue();
                }
                return prod;
            } else if (type == 2) {
                long min = Integer.MAX_VALUE;
                for (var packet : subPackets) {
                    min = Math.min(min, packet.getValue());
                }
                return min;
            } else if (type == 3) {
                long max = Integer.MIN_VALUE;
                for (var packet : subPackets) {
                    max = Math.max(max, packet.getValue());
                }
                return max;
            } else if (type == 4) {
                return literal;
            } else if (type == 5) {
                return subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1 : 0;
            } else if (type == 6) {
                return subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1 : 0;
            } else if (type == 7) {
                return subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1 : 0;
            }
            throw new RuntimeException("Cant handle type: " + type);
        }
    }

    private static long number(char[] bits, int startIndex, int length) {
        long num = 0;
        while(length-- > 0) {
            num += (bits[startIndex++] == '1' ? 1L << length : 0L);
        }
        return num;
    }

    private static long number(char[] bits) {
        return number(bits, 0, bits.length);
    }
}
