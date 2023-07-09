package nl.leobaehre.opengui.util;

import nl.leobaehre.opengui.OpenGui;

public class ReplaceUtil {

    public static String replace(String text, String... replacements) {

        for (int i = 0; i < replacements.length; i++) {
            if (i % 2 == 0) {
                text = text.replace("%" + replacements[i] + "%", replacements[i + 1]);
            }
        }

        return OpenGui.colorize(text);
    }
}
