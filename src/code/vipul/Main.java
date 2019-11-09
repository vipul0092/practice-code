package code.vipul;

import code.vipul.animalShelter.AnimalShelter;
import code.vipul.linkedlist.PalindromeList;
import code.vipul.linkedlist.RearrangeList;
import code.vipul.search.SearchWithoutSize;

public class Main {

    public static void main(String[] args) {
        //PermutationInOther.solve("abbc", "cbabadcbbabbcbabaabccbabc");

        //RotateMatrix.rotate(RotateMatrix.getDummyMatrix());

        //SortStack.doWork(print(getRandomStack()));

        // AnimalShelter.doStuff();
        //MinHeap.doStuff();
        //SortedArrayToBST.doStuff();

        //LowestCommonAncestor.solve();

        //System.out.println(RecursiveMultiply.multiply(32, 32));

        //Parenthesis.solve(3);

        //CurrencySum.solve(100);
        //BoxStacks.solve();
        //BooleanEvaluation.solve("0&0&0&1", false);
        //System.out.println("Answers: " + BooleanEvaluation.countEval("0&0&0&1", false));

        //SearchWithoutSize.solve();

        //SpaceShift.solve("Mr John Smith Clark      ", 19);

        //NumberWriter.solve(1000070);
        // RearrangeList.makeDataAndRun();
       //  PalindromeList.makeDataAndRun();

        KthNumberWithFactors.solve(10);
    }
}


// Page 72
// Practice heaps, hash tables

// all perms of abbc in cbabadcbbabbcbabaabccbabc
//BooleanEvaluation.solve("1^0|0|1", false); Ans=2; BooleanEvaluation.solve("0&0&0&1^1|0", true); ans=10;