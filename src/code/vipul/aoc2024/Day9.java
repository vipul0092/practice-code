package code.vipul.aoc2024;

import code.vipul.utils.AoCInputReader;

import java.util.*;

/**
 * https://adventofcode.com/2024/day/9
 */
public class Day9 {

    public static void solve() {
        String line = AoCInputReader.read(Day9.class, false).get(0);

        long s = System.currentTimeMillis();
        FileSystem fileSystem = defragment(line, false); // 6337921897505
        System.out.println("Part 1: " + fileSystem.checkSum() + " | took " + (System.currentTimeMillis() - s) + "ms");

        s = System.currentTimeMillis();
        fileSystem = defragment(line, true); // 6362722604045
        System.out.println("Part 2: " + fileSystem.checkSum() + " | took " + (System.currentTimeMillis() - s) + "ms");
    }

    private static FileSystem defragment(String input, boolean blockFileMove) {
        FileSystem fileSystem = parse(input);

        for (int i = fileSystem.files.size() - 1; i >= 0; i--) {
            Item file = fileSystem.getFile(i);

            int blockMoveIterations = file.size(); // Need to move the whole file
            int blockSizeToSearch = blockFileMove ? file.size() : 1;
            Item blankItem = null;
            while (blockMoveIterations-- > 0) {
                blankItem = blankItem == null ? fileSystem.getBlankItem(blockSizeToSearch) : blankItem;

                if (blankItem == null || blankItem.start > file.start) {
                    break;
                }
                file.removeBlockFromEnd(); // Remove file block from end, this works for both parts
                int blankBlock = blankItem.removeBlockFromStart(); // always remove blank from start
                file.addBlock(blankBlock);
                if (blankItem.size() == 0) {
                    blankItem = null;
                }
            }
            if (blankItem != null) {
                fileSystem.blankItemHeapPerSize.get(blankItem.size()).add(blankItem);
            }
        }
        return fileSystem;
    }

    private static FileSystem parse(String line) {
        List<Item> files = new ArrayList<>();
        LinkedHashMap<Integer, Item> blankSpaces = new LinkedHashMap<>();
        int index = 0;
        for (int i = 0; i < line.length(); i++) {
            int val = line.charAt(i) - '0';
            int id = i / 2;
            if (i % 2 == 0) {
                files.add(new Item(id, index, index + val - 1));
                index += val;
            } else if (val > 0) {
                blankSpaces.put(id, new Item(id, index, index + val - 1));
                index += val;
            }
        }
        List<PriorityQueue<Item>> blankItemHeapPerSize = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            blankItemHeapPerSize.add(new PriorityQueue<>(Comparator.comparingInt(i2 -> i2.start)));
        blankSpaces.values().forEach(bs -> blankItemHeapPerSize.get(bs.size()).add(bs));
        return new FileSystem(files, blankItemHeapPerSize);
    }

    static class Item {
        private final int id;
        private int start;
        private final ArrayDeque<Integer> blocks;

        Item(int id, int s, int e) {
            this.id = id;
            this.start = s;
            this.blocks = new ArrayDeque<>();
            for (int i = s; i <= e; i++) {
                blocks.add(i);
            }
        }

        long checkSum() {
            long checkSum = 0;
            for (int blockId : blocks) {
                checkSum += ((long) blockId * id);
            }
            return checkSum;
        }

        int size() {
            return blocks.size();
        }

        void removeBlockFromEnd() {
            blocks.removeLast();
        }

        int removeBlockFromStart() {
            start++;
            return blocks.removeFirst();
        }

        void addBlock(int id) {
            blocks.addFirst(id);
        }
    }

    record FileSystem(List<Item> files, List<PriorityQueue<Item>> blankItemHeapPerSize) {
        Item getFile(int index) {
            return files.get(index);
        }

        long checkSum() {
            return files.stream().map(Item::checkSum).mapToLong(l -> l).sum();
        }

        Item getBlankItem(int size) {
            int start = Integer.MAX_VALUE, sz = -1;

            for (int i = blankItemHeapPerSize.size() - 1; i >= size; i--) {
                var heap = blankItemHeapPerSize.get(i);
                if (!heap.isEmpty() && heap.peek().start < start) {
                    start = heap.peek().start;
                    sz = i;
                }
            }
            return sz != -1 ? blankItemHeapPerSize.get(sz).poll() : null;
        }
    }
}
