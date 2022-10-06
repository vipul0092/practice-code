package code.vipul.aoc2020.day17;

import java.util.Objects;

/**
 * Created by vgaur created on 06/10/22
 */
public class Point3d implements Point {

    private final int x;
    private final int y;
    private final int z;

    public Point3d(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public int distanceL1(Point3d that) {
        return Math.abs(that.x - this.x) + Math.abs(that.y - this.y) + Math.abs(that.z - this.z);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Point3d)) {
            return false;
        }

        Point3d tmp = (Point3d) o;

        return this.x == tmp.x && this.y == tmp.y && this.z == tmp.z;
    }
}
