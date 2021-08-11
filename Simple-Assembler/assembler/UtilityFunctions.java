package assembler;

public class UtilityFunctions {

    static String getVariableAddress(String variable) {
        int value = Integer.parseInt(VariableTable.getAddress(variable));
        String res = Integer.toBinaryString(value);
        int l = res.length();
        for (int i = 0; i < 8 - l; i++) {
            res = "0" + res;
        }
        return res;
    }

    static String getLabelAddress(String label) {
        int value = Integer.parseInt(LabelTable.getAddress(label));
        String res = Integer.toBinaryString(value);
        int l = res.length();
        for (int i = 0; i < 8 - l; i++) {
            res = "0" + res;
        }
        return res;
    }

    static String immediateToBinary(String immediate) {
        int value = Integer.parseInt(immediate.substring(1));
        String res = Integer.toBinaryString(value);
        int l = res.length();
        for (int i = 0; i < 8 - l; i++) {
            res = "0" + res;
        }
        return res;
    }

    static String getRegisterCode(String register) {
        if(register.equals("FLAGS")) {
            return "111";
        }
        int value = Integer.parseInt(String.valueOf(register.charAt(1)));
        String res = Integer.toBinaryString(value);
        int l = res.length();
        for (int i = 0; i < 3 - l; i++) {
            res = "0" + res;
        }
        return res;
    }

    static String getOpcode(String instruction) throws Exception {
        return InstructionTable.getInstruction(instruction).getOpcode();
    }

    static char getType(String instruction) throws Exception {
        return InstructionTable.getInstruction(instruction).getType();
    }

    static boolean isRegister(String token) {
        return token.charAt(0) == 'R' || token.equals("FLAGS");
    }

    static boolean isVariableKeyWord(String token) {
        return token.equals("var");
    }

    static boolean isLabel(String token) {
        return token.charAt(token.length()-1) == ':';
    }

    static boolean isAddress(String token) {
        return LabelTable.isPresent(token);
    }

    static boolean isImmediate(String token) {
        return token.charAt(0) == '$';
    }

    static boolean isVariable(String token) {
        return VariableTable.isPresent(token);
    }
}
