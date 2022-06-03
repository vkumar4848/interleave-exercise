import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Interleave {

    /**
     * When speaking with Jon, he mentioned considering ways in which to make my solution scalable. I've chosen to make
     * my solution work with any number of input files. Words will be interleaved in the same order the file paths are
     * input into the program.
     *
     * Assumptions:
     * input file paths are absolute
     * only text files will be provided as input
     * words are delimited by whitespace
     *
     * Tested using Java 15
     */
    public static void main(String[] args) {
        List<Scanner> fileScanners = new ArrayList<>();
        for (String filePath : args) {
            try {
                // open each file into a scanner object, and set the scanner to use any whitespace as a delimiter
                Scanner scanner = new Scanner(new File(filePath));
                scanner.useDelimiter("\\s+");
                fileScanners.add(scanner);
            } catch (FileNotFoundException ignored) {
                // ignore invalid file paths
            }
        }

        List<String> output = new LinkedList<>();
        // keep iterating while there are open files
        while (!fileScanners.isEmpty()) {
            // for each iteration of the loop, get 1 word from each file
            int i = 0;
            while (i < fileScanners.size()) {
                Scanner fileScanner = fileScanners.get(i);
                if (fileScanner.hasNext()) {
                    output.add(fileScanner.next());
                    i++;
                } else {
                    // if we have reached the end of the file, close the file and remove it from the list
                    fileScanner.close();
                    fileScanners.remove(i);
                }
            }
        }

        System.out.println(output);
    }

}
