package assembler;

import java.util.ArrayList;

public class Error {
    
    public static void init(ArrayList<ArrayList<String>> input) throws IllegalStateException {
        imm_contain_alphabets(input);
        imm_outside_range(input);
        error_in_hlt(input);
    }

    private static void error_in_hlt(ArrayList<ArrayList<String>> input) throws IllegalStateException {
        int count = 0, size = input.size();
        for(ArrayList<String> line : input) {
            for(String token : line) {
                if(token.equals("hlt")) {
                    count++;
                }
            }
        }
        if(count != 1) {
            throw new IllegalStateException("There should be exactly one hlt in the whole program");
        }
        if(!input.get(size - 1).get(0).equals("hlt") && (input.get(size-1).size() > 1 && !input.get(size-1).get(1).equals("hlt"))) {
            throw new IllegalStateException("hlt not present at the end or wrong syntax");
        }
    }

    private static void imm_contain_alphabets(ArrayList<ArrayList<String>> input) throws IllegalStateException {
        int lineNumber = 1;
        for(ArrayList<String> line : input) {
            for(String token : line) {
                if(UtilityFunctions.isImmediate(token)) {
                    try {
                        Integer.parseInt(token.substring(1));
                    } 
                    catch (IllegalStateException e) {
                        throw new IllegalStateException("Error at line " + lineNumber + "\nImmediate should contain only integers");
                    }
                }
            }
            lineNumber++;
        }
    }

    private static void imm_outside_range(ArrayList<ArrayList<String>> input) throws IllegalStateException {
        int lineNumber = 1;
        for(ArrayList<String> line : input) {
            for(String token : line) {
                if(UtilityFunctions.isImmediate(token)) {
                    int value = Integer.parseInt(token.substring(1));
                    if(value < 0 || value > 255) {
                        throw new IllegalStateException("Error at line " + lineNumber + "\nImmediate value out of range");
                    }
                }
            }
            lineNumber++;
        }
    }
}
