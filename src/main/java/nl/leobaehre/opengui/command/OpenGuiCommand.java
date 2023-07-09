package nl.leobaehre.opengui.command;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.util.TabUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class OpenGuiCommand implements CommandExecutor, TabCompleter {

    OpenGui openGui;
    public OpenGuiCommand(OpenGui openGui) {
        this.openGui = openGui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!sender.hasPermission("opengui.reload")) {
            sender.sendMessage("You do not have permission to use this command");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(getUsage());
            return true;
        }

        switch (args[0]) {
            case "reload":
                sender.sendMessage("Reloading OpenGui");
                openGui.getGuiManager().loadGuis();

                break;
            default:
                sender.sendMessage(getUsage());
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

            if (args.length == 1) {
                return TabUtil.complete(args[0], List.of("reload"));
            }

            return null;
    }

    private String getUsage() {
        return OpenGui.colorize("&cUsage: /opengui reload");
    }
}
