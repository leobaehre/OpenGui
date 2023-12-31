package nl.leobaehre.opengui.command;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Gui;
import nl.leobaehre.opengui.util.TabUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class OpenCommand implements CommandExecutor, TabCompleter {

    OpenGui openGui;
    public OpenCommand(OpenGui openGui) {
        this.openGui = openGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("Usage: /open <gui>");
            return true;
        }

        String guiId = args[0];

        Gui gui = openGui.getGuiManager().getGuiById(guiId);
        if (gui == null) {
            player.sendMessage("Gui not found");
            return true;
        }

        gui.open(player);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 1) {
            return TabUtil.complete(args[0], openGui.getGuiManager().getLoadedGuis().stream().map(Gui::getId).toList());
        }

        return null;
    }
}
