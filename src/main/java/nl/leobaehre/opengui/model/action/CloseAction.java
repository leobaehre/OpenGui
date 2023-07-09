package nl.leobaehre.opengui.model.action;

import lombok.AllArgsConstructor;
import nl.leobaehre.opengui.model.ItemAction;
import org.bukkit.entity.Player;

public class CloseAction extends ItemAction {

    @Override
    public void execute(Player player) {
        player.closeInventory();
    }
}
