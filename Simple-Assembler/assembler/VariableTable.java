package assembler;

import java.util.HashMap;
import java.util.Map;

public class VariableTable {

    private static final Map<String, String> map = new HashMap<>();

    public static void addVariable(String label, String address) {
        map.put(label, address);
    }

    public static String getAddress(String variable) {
        if (isPresent(variable))
            return map.get(variable);
        return null;
    }

    public static void updateAddresses(int address) {
        for(String var : map.keySet()) {
            map.replace(var, String.valueOf(address++));
        }
    }

    public static boolean isPresent(String variable) {
        return map.containsKey(variable);
    }

    public static int getSize() {
        return map.size();
    }
}
