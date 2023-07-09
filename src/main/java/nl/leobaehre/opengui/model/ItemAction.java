package nl.leobaehre.opengui.model;

import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public abstract class ItemAction {

    abstract public void execute(Player player);
}
