package code.vipul.aoc2018.grid;

import java.util.Objects;

/**
 * Created by vgaur created on 11/10/22
 */
public class Posxy implements Comparable<Posxy> {
    private final int x;
    private final int y;

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    private int hash = -1;

    private Posxy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Posxy of(int x, int y) {
        return new Posxy(x, y);
    }

    public Posxy moveRight() {
        return of(x + 1, y);
    }

    public Posxy moveLeft() {
        return of(x - 1, y);
    }

    public Posxy moveUp() {
        return of(x, y - 1);
    }

    public Posxy moveDown() {
        return of(x, y + 1);
    }

    public boolean isValid() {
        return x >= 0 && y >= 0;
    }

    public boolean isOrigin() {
        return x == 0 && y == 0;
    }


    @Override
    public int hashCode() {
        if (hash == -1) {
            hash = 5381;
            hash += (hash << 5) + Objects.hashCode(x);
            hash += (hash << 5) + Objects.hashCode(y);
        }
        return hash;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        return another instanceof Posxy && equalTo((Posxy) another);
    }

    @Override
    public String toString() {
        return String.format("x: %s, y: %s", x, y);
    }

    private boolean equalTo(Posxy another) {
        return Objects.equals(x, another.x) && Objects.equals(y, another.y);
    }

    @Override
    public int compareTo(Posxy p) {
        int ret = this.x - p.x;
        // Equal i so fall back to comparing j.
        if (ret == 0) {
            ret = this.x - p.x;
        }
        return ret;
    }
}
