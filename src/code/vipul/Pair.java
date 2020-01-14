package code.vipul;

import java.util.Objects;

/**
 * Not thread safe
 */
public class Pair<T1, T2> {

    public T1 left() {
        return left;
    }

    public T2 right() {
        return right;
    }

    private final T1 left;
    private final T2 right;
    private int hash = -1;

    private Pair(T1 left, T2 right) {
        this.left = left;
        this.right = right;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 left, T2 right) {
        return new Pair<>(left, right);
    }

    @Override
    public int hashCode() {
        if (hash == -1) {
            hash = 5381;
            hash += (hash << 5) + Objects.hashCode(left);
            hash += (hash << 5) + Objects.hashCode(right);
        }
        return hash;
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        return another instanceof Pair && equalTo((Pair) another);
    }

    @Override
    public String toString() {
        return String.format("left: %s, right: %s", left.toString(), right.toString());
    }

    private boolean equalTo(Pair another) {
        return Objects.equals(left, another.left) && Objects.equals(right, another.right);
    }
}
