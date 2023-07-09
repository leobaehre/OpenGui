package nl.leobaehre.opengui.manager;

import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.Callback;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConversationManager {

    public static Map<UUID, Callback<String>> inConversation = new HashMap<>();

    public static void startConversation(Player player, String question, Callback<String> callback) {
        inConversation.put(player.getUniqueId(), callback);
        player.sendMessage(OpenGui.colorize(question + " &7[Type &ccancel&7 to cancel]"));
    }
}
