import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
* This program prompts the user to select a file (1, 2, or 3) to process.
* It then reads integer values from the chosen file and
* displays the mean and median of those integers.
*
* @author Remy Skelton
* @version 1.0
* @since 2025-03-07
*/

final class Arrays {
    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private Arrays() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(final String[] args) throws Exception {
        // Create a scanner object
        Scanner scanner = new Scanner(System.in);

        // Initialize the file name
        String fileName = "";

        // Loop until the user enters 'q'
        do {
            // Ask the user for the file name
            System.out.print("Enter the number set you would like "
            + "to use (1, 2 or 3) or 'q' to quit: ");

            // Get the file name
            fileName = scanner.nextLine();

            // If the user enters 'q'
            if (fileName.equals("q")) {
                // Display goodbye message
                System.out.println("Goodbye!");
            } else if (!fileName.equals("1")
                    && !fileName.equals("2")
                    && !fileName.equals("3")) {
                // If the user enters an invalid input
                System.out.print("Invalid input.");
                System.out.println(" Please enter a number set (1, 2, or 3).");
            } else {
                // Set the file name
                fileName = "Unit2-06-set" + fileName + ".txt";

                // Create a file object
                File file = new File(fileName);

                // Create a second scanner object
                Scanner fileScanner = new Scanner(file);

                // Create an array list
                ArrayList<Integer> numbers = new ArrayList<>();

                // Iterate through the file
                while (fileScanner.hasNextInt()) {
                    // Add the integer to the array list
                    numbers.add(fileScanner.nextInt());
                }

                // Close the file scanner
                fileScanner.close();

                // Convert the arrayList of integers into an array
                int[] arrayInt = new int[numbers.size()];
                for (int counter = 0; counter < numbers.size(); counter++) {
                    arrayInt[counter] = numbers.get(counter);
                }

                // Sort the array
                java.util.Arrays.sort(arrayInt);

                // Calculate the mean
                double mean = calculateMean(arrayInt);

                // Calculate the median
                double median = calculateMedian(arrayInt);

                // Create a file writer object
                FileWriter fileWriter =
                new FileWriter(fileName + "-output.txt");

                // Iterate through the array list
                for (int num : arrayInt) {
                    fileWriter.write(num + " ");
                }

                // Newline for spacing
                fileWriter.write("\n");

                // Write the mean to the file
                fileWriter.write("Mean: " + mean + "\n");

                // Write the median to the file
                fileWriter.write("Median: " + median + "\n");

                // Close the file writer
                fileWriter.close();

                // Display a success message
                System.out.println("The file '"
                        + fileName
                        + "' has been written successfully.");
            }
        // Loop until the user enters 'q'
        } while (!fileName.equals("q"));

        // Close the scanner
        scanner.close();
    }

    // Method for calculating the mean
    public static double calculateMean(final int[] array) {
        // Set the sum to 0
        double sum = 0;
        // Iterate through the array
        for (int num : array) {
            // Add the number to the sum
            sum += num;
        }
        // Return the mean
        return sum / array.length;
    }

    // Method for calculating the median
    public static double calculateMedian(final int[] array) {
        // Find the array length
        int length = array.length;
        // Check if there is a remainder when dividing by 2
        if (length % 2 != 0) {
            // If there is a remainder, return the middle number
            return array[length / 2];
        } else {
            // If there is no remainder,
            //return the average of the two middle numbers
            return (array[length / 2 - 1] + array[length / 2]) / 2.0;
        }
    }
}
