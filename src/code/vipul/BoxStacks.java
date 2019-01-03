package code.vipul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vgaur on 27/12/18.
 */
public class BoxStacks {

    static int globalMax = 0;
    static Map<Box, Integer> map = new HashMap<>();

    public static void solve() {
        List<Box> boxes = new ArrayList<>();

        boxes.add(new Box(8, 2, 9));
        boxes.add(new Box(3, 5, 4));
        boxes.add(new Box(6, 7, 8));
        boxes.add(new Box(15, 8, 12));
        boxes.add(new Box(12, 12, 12));
        boxes.add(new Box(10, 10, 10));

        for(Box b : boxes) {
            getLink(boxes, b, 0);
        }
        System.out.println("Largest stack size: " + globalMax);
    }

    static int getLink(List<Box> boxes, Box box, int height) {
        int maxsum = box.h;

        if(map.containsKey(box)) return map.get(box);

        for(Box b : boxes) {
            if (box.h < b.h && box.w < b.w && box.d < b.d) {
                int subheight = getLink(boxes, b, height + box.h) + box.h;
                maxsum = maxsum < subheight ? subheight : maxsum;
            }
        }

        map.put(box, maxsum);
        globalMax = globalMax < maxsum ? maxsum : globalMax;
        return maxsum;
    }

    static class Box {
        final int w;
        final int d;
        final int h;
        final int hash;

        static final int INIT = 5381;

        @Override
        public int hashCode() {
            return hash;
        }

        Box(int w, int d, int h) {
            this.w = w;
            this.d = d;
            this.h = h;

            int hash = INIT;
            hash += (hash << 5) + w;
            hash += (hash << 5) + d;
            hash += (hash << 5) + h;

            this.hash = hash;
        }
    }
}
