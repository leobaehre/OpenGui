package nl.leobaehre.opengui.util;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Variable;

import java.util.List;

public class ReplaceUtil {

    public static String replace(String text, String... replacements) {

        for (int i = 0; i < replacements.length; i++) {
            if (i % 2 == 0) {
                text = text.replace("%" + replacements[i] + "%", replacements[i + 1]);
            }
        }

        List<Variable> variables = OpenGui.getInstance().getVariableManager().getVariables();
        for (Variable variable : variables) {
            text = text.replace("%" + variable.getName() + "%", variable.getValue().toString());
        }
        return OpenGui.colorize(text);
    }
}
