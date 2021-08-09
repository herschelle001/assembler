package assembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ErrorGen {

    private static ArrayList<ArrayList<String>> input = new ArrayList<>();;

    public static boolean init() {
        Scanner scanner = new Scanner(System.in);
        int lineNo = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            ArrayList<String> tokens = new ArrayList<>(Arrays.asList(splitLine));
            input.add(lineNo++, tokens);
        }
        Main.init(input);
        return check();
    }

    private static boolean check() {
        return true;
    }
}
