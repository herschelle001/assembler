package assembler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        InstructionTable.populate();

        ArrayList<ArrayList<String>> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int k=0;

        // Standard Input
        while (true) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            ArrayList<String> tokens = new ArrayList<>(Arrays.asList(splitLine));
            input.add(k++, tokens);
            if(splitLine[splitLine.length-1].equals("hlt")) {
                break;
            }
        }

        // First Pass
        // Changing second type of mov to mvr
        for (ArrayList<String> line : input) {
            String first = line.get(0);
            if(isTokenLabel(first)) {
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
            else if(isTokenLabel(first)) {
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
            if(isTokenLabel(first)) {
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

    static boolean isTokenLabel(String token) {
        return Decipher.isLabel(token);
    }
}












 /*
var x
mov R1 $4
mov R2 $4
cmp R1 R2
mov R3 FLAGS
mov R4 $1
cmp R3 R4
jgt label
label: hlt


for (int i = 0; i < 9; i++) {
    input.add(i, new ArrayList<>());
}
input.get(0).add("var"); input.get(0).add("x");
input.get(1).add("mov"); input.get(1).add("R1"); input.get(1).add("$4");
input.get(2).add("mov"); input.get(2).add("R2"); input.get(2).add("$4");
input.get(3).add("cmp"); input.get(3).add("R1"); input.get(3).add("R2");
input.get(4).add("mov"); input.get(4).add("R3"); input.get(4).add("FLAGS");
input.get(5).add("mov"); input.get(5).add("R1"); input.get(5).add("$1");
input.get(6).add("cmp"); input.get(6).add("R3"); input.get(6).add("R4");
input.get(7).add("jgt"); input.get(7).add("label");
input.get(8).add("label:"); input.get(8).add("hlt");

  */