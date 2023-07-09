package nl.leobaehre.opengui.model.action;

import lombok.AllArgsConstructor;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Gui;
import nl.leobaehre.opengui.model.ItemAction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class OpenAction extends ItemAction {

    String id;
    OpenGui plugin;

    @Override
    public void execute(Player player) {
        Gui gui = plugin.getGuiManager().getGuiById(id);
        if (gui == null) {
            Bukkit.getLogger().warning("Could not find gui with id: " + id);
            return;
        }
        gui.open(player);
    }
}
