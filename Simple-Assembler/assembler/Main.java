package assembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static ArrayList<ArrayList<String>> input;

    public static void init(ArrayList<ArrayList<String>> array) {
        input = new ArrayList<>(array);
    }

    public static void main(String[] args) {
        InstructionTable.populate();

        boolean isSyntaxCorrect = ErrorGen.init();

        if(!isSyntaxCorrect) {
            return;
        }

        // First Pass
        // Changing second type of mov to mvr
        for (ArrayList<String> line : input) {
            String first = line.get(0);
            if(isLabel(first)) {
                String second = line.get(1);
                if(second.equals("mov")) {
                    String fourth = line.get(3);
                    if(Decipher.isRegister(fourth)) {
                        line.set(1, "mvr");
                    }
                }
            }
            else if(first.equals("mov")) {
                String third = line.get(2);
                if(Decipher.isRegister(third)) {
                    line.set(0, "mvr");
                }
            }
        }

        // Second Pass
        // Populating Label and Variable Tables
        int size = 0;
        for (ArrayList<String> line : input) {
            String first = line.get(0);
            boolean variable = first.equals("var");
            if (variable) {
                String second = line.get(1);
                VariableTable.addVariable(second, "");
            }
            else if(isLabel(first)) {
                LabelTable.addLabel(first, String.valueOf(size++));
            }
            else {
                size++;
            }
        }
        VariableTable.updateAddresses(size);

        // Third Pass
        // Iterating on code to generate its binary
        int codeStart = VariableTable.getSize();
        for (int i = codeStart; i < input.size(); i++) {
            ArrayList<String> line = input.get(i);
            String first = line.get(0);
            if(isLabel(first)) {
                ArrayList<String> temp = new ArrayList<>(line);
                temp.remove(0);
                Encode.generate(temp);
            }
            else {
                Encode.generate(line);
            }
        }

        Encode.printBinary();
    }

    private static boolean isLabel(String token) {
        return Decipher.isLabel(token);
    }
}