package assembler;

import java.util.HashMap;
import java.util.Map;

public class LabelTable {

    private static final Map<String, String> map = new HashMap<>();

    public static void addLabel(String label, String address) {
        int l = label.length();
        map.put(label.substring(0, l-1), address);
    }

    public static String getAddress(String label) {
        if(!isPresent(label)) {
            throw new NullPointerException("Typo in label name or label not declared - '" + label + "'");
        }
        return map.get(label);
    }

    public static boolean isPresent(String label) {
        return map.containsKey(label);
    }
}
