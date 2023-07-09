package nl.leobaehre.opengui.model.action;

import lombok.AllArgsConstructor;
import nl.leobaehre.opengui.model.ItemAction;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class CommandAction extends ItemAction {

    String command;
    CommandSender sender;

    public CommandAction(String command) {
        this.command = command;
        this.sender = Bukkit.getConsoleSender();
    }

    @Override
    public void execute(Player player) {
        Bukkit.dispatchCommand(sender, command);
    }
}
