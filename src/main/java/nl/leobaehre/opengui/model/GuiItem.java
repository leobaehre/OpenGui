package nl.leobaehre.opengui.model;

import lombok.Getter;
import nl.leobaehre.opengui.model.action.CloseAction;
import nl.leobaehre.opengui.model.action.CommandAction;
import nl.leobaehre.opengui.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GuiItem {

    int slot;

    Material material;
    int amount;
    String displayName;
    List<String> lore;
    List<ItemAction> actions;

    public GuiItem(int slot, Material material, int amount, String displayName, List<String> lore, List<ItemAction> actions) {
        this.slot = slot;
        this.material = material;
        this.amount = amount;
        this.displayName = displayName;
        this.lore = lore;
        this.actions = actions;
    }

    public ItemStack getItemStack() {
        ItemCreator itemCreator = new ItemCreator(material);
        itemCreator.setAmount(amount);
        itemCreator.setDisplayName(displayName);
        itemCreator.setLore(lore);
        return itemCreator.create();
    }

    public void executeActions(Player player) {
        for (ItemAction action : actions) {
            action.execute(player);
        }
    }

    public static GuiItem fromSection(ConfigurationSection section) {
        int slot = section.getInt("slot");
        Material material = Material.valueOf(section.getString("material"));
        int amount = section.getInt("amount");
        String displayName = section.getString("display-name");
        List<String> lore = section.getStringList("lore");

        List<ItemAction> actions = new ArrayList<>();

        ConfigurationSection actionsSection = section.getConfigurationSection("actions");
        if (actionsSection != null) {
            for (String key : actionsSection.getKeys(false)) {

                List<ConfigurationSection> actionSections = actionsSection.getConfigurationSection(key).getValues(false)
                        .values()
                        .stream()
                        .map(o -> (ConfigurationSection) o)
                        .toList();

                for (ConfigurationSection actionSection : actionSections) {
                    String actionType = actionSection.getString("type");

                    if (actionType == null) throw new RuntimeException("Action type is null");

                    switch (actionType) {
                        case "command" -> {
                            String command = actionSection.getString("command");
                            ItemAction commandAction = new CommandAction(command);
                            actions.add(commandAction);
                        }
                        case "close" -> {
                            ItemAction closeAction = new CloseAction();
                            actions.add(closeAction);
                        }
                    }
                }
            }
        }

        return new GuiItem(slot, material, amount, displayName, lore, actions);
    }


}
