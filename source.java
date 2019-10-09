import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    // this algorithm assumes that the elements will be consecutive and non-negative

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

        // min will be used to hold the smallest and thus left most integer
        int min = arr[0]; // must be defined outside the loop for scope
        int currentCycleNodeCount = 0;
        int currentNodeValue = 0;
        int swapSum = 0;
        int tempHolder = 0;
        boolean continueCycle = true;
        // if the integers do not start at zero, then we use the offset value to account

        // find the smallest integer which will be the left most integer
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] < min) {
                min = arr[k];
            }
        }
        System.out.println("The smallest value is: " + min);

        int offset = min;

        // start checking the value against it's position, from left to right
        for (int k = 0; k < arr.length; k++) {

            // if we have found an value that is not at the correct index
            // if we were guaranteed that the values started at zero we wouldn't need "min"

            // if an element is situated in the wrong index location
            // does 9 == 4? no...

            if (arr[k] != k + offset) {

                // System.out.println("We have found a value that is in the wrong spot, the
                // value is: " + arr[k] + " which is in position " + k + " when it should be at
                // position " + (arr[k] - offset));

                currentCycleNodeCount = 1;

                // copy the old value out
                currentNodeValue = arr[k];

                // fix the value to what it should have been
                // put a 4 at index position 1
                arr[k] = k + offset;

                // System.out.println("We have replaced the value at position " + k + " with " +
                // (k+offset));

                do {
                    currentCycleNodeCount++;

                    // this checks if the loop has enclosed on itself yet
                    if (arr[currentNodeValue - offset] == k + offset) {
                        continueCycle = false;
                    }

                    tempHolder = currentNodeValue;

                    // copy out the old value to see where we jump to next before correcting it
                    currentNodeValue = arr[tempHolder - offset];
                    // 8 = arr[ 9 - 3]

                    // rewrite the old value to what it SHOULD be since it has been accounted for
                    arr[tempHolder - offset] = tempHolder;

                    // System.out.println("We have replaced the value at position " +
                    // (tempHolder-offset) + " with " + tempHolder);

                } while (continueCycle);

                System.out.println("This cycle has finished and had " + currentCycleNodeCount
                        + "nodes.  The number of swaps before was " + swapSum);

                swapSum += (--currentCycleNodeCount);

                System.out.println("The swapSum total now is: " + swapSum);

            }

        }
        System.out.println("The final answer is :" + swapSum);
        return swapSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);
        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();
        bufferedWriter.close();
        scanner.close();
    }
}
