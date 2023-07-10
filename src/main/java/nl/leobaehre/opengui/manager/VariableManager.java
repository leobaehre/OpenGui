package nl.leobaehre.opengui.manager;

import lombok.Getter;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Variable;
import nl.leobaehre.opengui.model.VariableType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VariableManager {
    OpenGui plugin;

    @Getter
    List<Variable> variables;

    public VariableManager(OpenGui plugin) {
        this.plugin = plugin;
        this.variables = new ArrayList<>();
    }

    public void setVariable(String name, Object value) {
        Variable variable = new Variable(name, value);
        this.variables.add(variable);
    }

    public void removeVariable(Variable variable) {
        this.variables.remove(variable);
    }

    public Variable getVariable(String name) {
        for (Variable variable : this.variables) {
            if (variable.getName().equalsIgnoreCase(name)) {
                return variable;
            }
        }
        return null;
    }

    public void loadVariables() {

        variables.clear();

        File file = new File(plugin.getDataFolder(), "data/variables.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration dataFile = YamlConfiguration.loadConfiguration(file);

        for (String key : dataFile.getKeys(false)) {
            ConfigurationSection section = dataFile.getConfigurationSection(key);
            if (section == null) continue;

            Variable variable = Variable.fromSection(key, section);
            this.variables.add(variable);
        }

    }

    public void saveVariables() {
        File file = new File(plugin.getDataFolder(), "data/variables.yml");
        if (!file.exists()) return;

        YamlConfiguration dataFile = YamlConfiguration.loadConfiguration(file);

        for (Variable variable : this.variables) {
            dataFile.set(variable.getName() + ".value", variable.getValue());
        }

        try {
            dataFile.save(new File(plugin.getDataFolder(), "data/variables.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
