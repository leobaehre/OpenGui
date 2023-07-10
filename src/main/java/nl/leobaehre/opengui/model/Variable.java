package nl.leobaehre.opengui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Variable {

    String name;
    Object value;

    public static Variable fromSection(String key, ConfigurationSection section) {
        // type with uuid if player
        Object value = section.get("value");
        if (value == null)
            throw new IllegalArgumentException("Variable " + key + " has no value");
        if (!(value instanceof String) && !(value instanceof Number) && !(value instanceof Boolean))
            throw new IllegalArgumentException("Variable " + key + " has an invalid value type");

        return new Variable(key, value);
    }
}
