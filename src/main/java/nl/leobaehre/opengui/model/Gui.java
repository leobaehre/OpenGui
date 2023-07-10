package nl.leobaehre.opengui.model;

import lombok.Getter;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.util.ReplaceUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Gui {

    String id;
    String title;
    int size;

    List<GuiItem> items;

    public Gui(String id, String title, int size, List<GuiItem> items) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.items = items;
    }

    public void open(Player player) {
        String finalTitle = ReplaceUtil.replace(OpenGui.colorize(title), "player", player.getName());

        Inventory inventory = Bukkit.createInventory(null, size, finalTitle);

        for (GuiItem item : items) {
            inventory.setItem(item.getSlot(), item.getItemStack());
        }

        player.openInventory(inventory);
    }

    public GuiItem getItem(int slot) {
        for (GuiItem item : items) {
            if (item.getSlot() == slot) {
                return item;
            }
        }
        return null;
    }

    public static Gui loadFromFile(File file, OpenGui plugin) {

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        String id = yamlConfiguration.getString("id");
        String title = yamlConfiguration.getString("title");
        int size = yamlConfiguration.getInt("size");

        List<GuiItem> items = new ArrayList<>();

        ConfigurationSection itemsSection = yamlConfiguration.getConfigurationSection("items");
        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
                if (itemSection != null) {
                    GuiItem item = GuiItem.fromSection(itemSection, plugin);
                    items.add(item);
                }
            }
        }

        return new Gui(id, title, size, items);
    }

}
