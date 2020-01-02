package code.vipul;

import java.util.Objects;

public class Pair<T> {

    public T left() {
        return left;
    }

    public T right() {
        return right;
    }

    private final T left;
    private final T right;

    private Pair(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public static <T> Pair<T> of(T left, T right) {
        return new Pair<>(left, right);
    }

    @Override
    public int hashCode() {
        int h = 5381;
        h += (h << 5) + Objects.hashCode(left);
        h += (h << 5) + Objects.hashCode(right);
        return h;
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
