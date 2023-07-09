package nl.leobaehre.opengui.listener;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Gui;
import nl.leobaehre.opengui.model.GuiItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {
    private final OpenGui plugin;

    public GuiListener(OpenGui plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedStack = event.getCurrentItem();

        if (clickedStack == null) return;

        String title = event.getView().getTitle();
        Gui clickedGui = plugin.getGuiManager().getGuiByTitle(title);

        if (clickedGui != null) {
            event.setCancelled(true);
            handleGuiClick(event, player, clickedGui);
        }
    }

    private void handleGuiClick(InventoryClickEvent event, Player player, Gui clickedGui) {
        int clickedSlot = event.getSlot();
        GuiItem clickedGuiItem = clickedGui.getItem(clickedSlot);
        if (clickedGuiItem == null) return;

        clickedGuiItem.executeActions(player);
    }
}