package nl.leobaehre.opengui.command;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Variable;
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

        switch (args.length) {
            case 1 -> {
                switch (args[0]) {
                    case "reload" -> {
                        sender.sendMessage("Reloading OpenGui");
                        openGui.getGuiManager().loadGuis();
                    }
                    default -> sender.sendMessage(getUsage());
                }
            }
            case 2 -> {
                switch (args[0]) {
                    case "getvariable" -> sender.sendMessage("Value of variable " + args[1] + ": " + openGui.getVariableManager().getVariable(args[1]).getValue());

                    default -> sender.sendMessage(getUsage());
                }
            }
            default -> sender.sendMessage(getUsage());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

            switch (args.length) {
                case 1 -> {
                    return TabUtil.complete(args[0], List.of("reload", "getvariable"));
                }
                case 2 -> {
                    return TabUtil.complete(args[1], openGui.getVariableManager().getVariables().stream().map(Variable::getName).toList());
                }
            }

            return null;
    }

    private String getUsage() {
        return OpenGui.colorize("&cUsage: /opengui <reload|getvariable>");
    }
}
