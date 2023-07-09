package nl.leobaehre.opengui.manager;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Gui;
import nl.leobaehre.opengui.model.GuiItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GuiManager {

    OpenGui plugin;

    List<Gui> loadedGuis;

    public GuiManager(OpenGui plugin) {
        this.plugin = plugin;
        this.loadedGuis = new ArrayList<>();
    }

    public void loadGuis() {
        loadedGuis.clear();

        File guiFolder = new File(plugin.getDataFolder(), "guis");
        if (!guiFolder.exists() || !guiFolder.isDirectory()) {
            guiFolder.mkdirs();
            return;
        }

        for (File file : guiFolder.listFiles()) {
            if (file == null) continue;
            loadedGuis.add(Gui.loadFromFile(file));
        }
    }

    public Gui getGuiById(String id) {
        for (Gui gui : loadedGuis) {
            if (gui.getId().equals(id)) {
                return gui;
            }
        }
        return null;
    }

    public Gui getGuiByTitle(String title) {
        for (Gui gui : loadedGuis) {
            if (gui.getTitle().equals(title)) {
                return gui;
            }
        }
        return null;
    }

    public List<Gui> getLoadedGuis() {
        return loadedGuis;
    }
}
