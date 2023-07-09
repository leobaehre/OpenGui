package nl.leobaehre.opengui.util;

import nl.leobaehre.opengui.OpenGui;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemCreator {

    ItemStack itemStack;
    ItemMeta meta;

    public ItemCreator(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemCreator setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemCreator setDisplayName(String displayName) {
        this.meta.setDisplayName(OpenGui.colorize(displayName));
        return this;
    }

    public ItemCreator setLore(List<String> lore) {
        lore.replaceAll(OpenGui::colorize);
        this.meta.setLore(lore);
        return this;
    }

    public ItemCreator setGlowing(boolean glowing) {
        if (glowing) {
            this.meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            this.meta.removeEnchant(Enchantment.DURABILITY);
            this.meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemStack create() {
        this.itemStack.setItemMeta(this.meta);
        return this.itemStack;
    }
}
