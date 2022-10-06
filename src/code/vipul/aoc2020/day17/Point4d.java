package code.vipul.aoc2020.day17;

import java.util.Objects;

/**
 * Created by vgaur created on 06/10/22
 */
public class Point4d implements Point {

    private final int x;
    private final int y;
    private final int z;
    private final int w;

    public Point4d(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    public int w() {
        return w;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + ", " + w + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Point4d)) {
            return false;
        }

        Point4d tmp = (Point4d) o;

        return this.x == tmp.x && this.y == tmp.y && this.z == tmp.z && tmp.w == this.w;
    }

}
