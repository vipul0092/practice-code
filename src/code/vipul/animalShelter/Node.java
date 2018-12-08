package code.vipul.animalShelter;

/**
 * Created by vgaur on 08/12/18.
 */
public class Node {

    private static int total = 0;

    public Node next = null;
    private final boolean isDog;
    public final int number;

    public boolean isDog() {
        return isDog;
    }

    public boolean isCat() {
        return !isDog;
    }

    private Node(boolean isDog) {
        this.isDog = isDog;
        total++;
        this.number = total;
    }

    public static Node getDog() {
        return new Node(true);
    }

    public static Node getCat() {
        return new Node(false);
    }
}
