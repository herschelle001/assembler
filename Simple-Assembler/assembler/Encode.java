package assembler;

import java.util.ArrayList;

public class Encode {

    private static final ArrayList<String> binary = new ArrayList<>();

    public static void printBinary() {
        for(String s : binary) {
            System.out.println(s);
        }
    }

    public static void generate(ArrayList<String> line) {
        String first = line.get(0);
        char type = Decipher.getType(first);
        if(type == 'A') {
            typeA(line);
        }
        else if(type == 'B') {
            typeB(line);
        }
        else if(type == 'C') {
            typeC(line);
        }
        else if(type == 'D') {
            typeD(line);
        }
        else if(type == 'E') {
            typeE(line);
        }
        else {
            typeF();
        }
    }

    private static void typeA(ArrayList<String> line) {
        String instruction = line.get(0);
        String reg1 = line.get(1);
        String reg2 = line.get(2);
        String reg3 = line.get(3);

        String opcode = InstructionTable.getInstruction(instruction).getOpcode();
        String filler = "00";
        String reg1Code = Decipher.getRegisterCode(reg1);
        String reg2Code = Decipher.getRegisterCode(reg2);
        String reg3Code = Decipher.getRegisterCode(reg3);

        String res = opcode + filler + reg1Code + reg2Code + reg3Code;
        binary.add(res);
    }

    private static void typeB(ArrayList<String> line) {
        String instruction = line.get(0);
        String reg = line.get(1);
        String immediate = line.get(2);

        String opcode = InstructionTable.getInstruction(instruction).getOpcode();
        String regCode = Decipher.getRegisterCode(reg);
        String immInBin = Decipher.immediateToBinary(immediate);

        String res = opcode + regCode + immInBin;
        binary.add(res);
    }

    private static void typeC(ArrayList<String> line) {
        String instruction = line.get(0);
        String reg1 = line.get(1);
        String reg2 = line.get(2);

        String opcode = InstructionTable.getInstruction(instruction).getOpcode();
        String filler = "00000";
        String reg1Code = Decipher.getRegisterCode(reg1);
        String reg2Code = Decipher.getRegisterCode(reg2);

        String res = opcode + filler + reg1Code + reg2Code;
        binary.add(res);
    }

    private static void typeD(ArrayList<String> line) {
        String instruction = line.get(0);
        String reg = line.get(1);
        String variable = line.get(2);

        String opcode = InstructionTable.getInstruction(instruction).getOpcode();
        String regCode = Decipher.getRegisterCode(reg);
        String variableAddress = Decipher.getVariableAddress(variable);

        String res = opcode + regCode + variableAddress;
        binary.add(res);
    }

    private static void typeE(ArrayList<String> line) {
        String instruction = line.get(0);
        String label = line.get(1);

        String opcode = InstructionTable.getInstruction(instruction).getOpcode();
        String filler = "000";
        String address = Decipher.getLabelAddress(label);

        String res = opcode + filler + address;
        binary.add(res);
    }

    private static void typeF() {
        binary.add("1001100000000000");
    }
}
