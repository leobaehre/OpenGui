package nl.leobaehre.opengui;

import lombok.Getter;
import nl.leobaehre.opengui.command.OpenCommand;
import nl.leobaehre.opengui.manager.GuiManager;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class OpenGui extends JavaPlugin {

    @Getter
    private GuiManager guiManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        createExampleFiles();

        guiManager = new GuiManager(this);
        guiManager.loadGuis();

        PluginCommand MCOpenCommand = getCommand("open");
        OpenCommand openCommand = new OpenCommand(this);
        MCOpenCommand.setExecutor(openCommand);
        MCOpenCommand.setTabCompleter(openCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void createExampleFiles() {
        File guiFolder = new File(getDataFolder(), "guis");
        if (!guiFolder.exists() || !guiFolder.isDirectory()) {
            guiFolder.mkdirs();
        }

        File exampleGuiFile = new File(guiFolder, "example.yml");
        if (!exampleGuiFile.exists()) {
            saveResource("guis/example.yml", false);
        }
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
