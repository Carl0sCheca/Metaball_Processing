import java.util.HashMap;
import java.util.Map;

public class Keyboard {

    public static Map<String, Boolean> keys = new HashMap<>();

    public static void initialize() {
        Keyboard.keys.put("UP", false);
        Keyboard.keys.put("RIGHT", false);
        Keyboard.keys.put("DOWN", false);
        Keyboard.keys.put("LEFT", false);
        Keyboard.keys.put("ACTION", false);
    }

    private static String getKey(int code) {
        switch (code) {
            case 87:
                return "UP";
            case 65:
                return "LEFT";
            case 83:
                return "RIGHT";
            case 68:
                return "DOWN";
            case 32:
                return "ACTION";
        }
        return null;
    }

    private static void toggleKey(int keySelected, boolean enabled) {
        String key = getKey(keySelected);
        if (key != null) {
            keys.put(key, enabled);
//            System.out.println(key);
        }
    }

    public static void keyPressed(int keyPressed) {
        toggleKey(keyPressed, true);
    }

    public static void keyReleased(int keyReleased) {
        toggleKey(keyReleased, false);
    }

}
