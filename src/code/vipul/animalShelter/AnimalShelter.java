package code.vipul.animalShelter;

import java.util.Stack;

/**
 * Created by vgaur on 08/12/18.
 */
public class AnimalShelter {

    private Node top = null;
    private Node tail = null;
    private Node dogTop = null;
    private Node prevDog = null;
    private Node catTop = null;
    private Node prevCat = null;

    public static void doStuff() {
        AnimalShelter shelter = new AnimalShelter();
        shelter.enqueue(Node.getCat());
        shelter.enqueue(Node.getDog());
        shelter.enqueue(Node.getDog());
        shelter.enqueue(Node.getCat());
        shelter.enqueue(Node.getDog());
        shelter.enqueue(Node.getCat());
        shelter.enqueue(Node.getDog());
        shelter.enqueue(Node.getCat());

        shelter.printQueue();

        shelter.dequeueDog();
        shelter.printQueue();

        shelter.dequeueCat();
        shelter.printQueue();

        shelter.dequeueCat();
        shelter.printQueue();

        shelter.dequeueCat();
        shelter.printQueue();

        shelter.enqueue(Node.getCat());
        shelter.printQueue();
        shelter.enqueue(Node.getDog());
        shelter.printQueue();

        shelter.dequeueAny();
        shelter.printQueue();

        shelter.dequeueCat();
        shelter.printQueue();

        shelter.dequeueAny();
        shelter.printQueue();

        shelter.dequeueDog();
        shelter.printQueue();
    }

    public void enqueue(Node ob) {
        Node prev = null;

        if (tail == null) {
            tail = ob;
            top = tail;
        } else {
            tail.next = ob;
            prev = tail;
            tail = ob;
        }

        if (ob.isDog() && dogTop == null) {
            dogTop = tail; prevDog = prev;
        }

        if (ob.isCat() && catTop == null) {
            catTop = tail; prevCat = prev;
        }
    }

    public void dequeueAny() {
        System.out.println("Dequeuing from top of queue...");
        boolean isCat = top.isCat();
        if (isCat) {
            dequeueCat();
        } else {
            dequeueDog();
        }
    }

    public void dequeueDog() {
        System.out.println("Dequeuing a dog from the queue...");
        Node currentPtr = dogTop;
        Node prevPtr = null;

        while(currentPtr != null) {
            prevPtr = currentPtr;
            currentPtr = currentPtr.next;
            if (currentPtr != null && currentPtr.isDog()) {
                break;
            }
        }

        if (prevDog != null) {
            prevDog.next = dogTop.next;
        }

        if (top == dogTop) {
            if (top == prevCat) {
                prevCat = null;
            }
            top = top.next;
        }

        dogTop = currentPtr;
        prevDog = prevPtr;
    }

    public void dequeueCat() {
        System.out.println("Dequeuing a cat from the queue...");
        Node currentPtr = catTop;
        Node prevPtr = null;

        while(currentPtr != null) {
            prevPtr = currentPtr;
            currentPtr = currentPtr.next;
            if (currentPtr != null && currentPtr.isCat()) {
                break;
            }
        }

        if (prevCat != null) {
            prevCat.next = catTop.next;
        }

        if (top == catTop) {
            if (top == prevDog) {
                prevDog = null;
            }
            top = top.next;
        }

        catTop = currentPtr;
        prevCat = prevPtr;
    }

    public void printQueue() {
        Node ptr = top;
        Stack<Node> stack = new Stack();
        while (ptr != null) {
            stack.push(ptr);
            ptr = ptr.next;
        }

        System.out.print("Push here");
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(" <-- " + (node.isCat() ? "C" : "D") + "(" + node.number  + ")");
        }
        System.out.print(" << DQ from here");
        System.out.println();
    }

}
