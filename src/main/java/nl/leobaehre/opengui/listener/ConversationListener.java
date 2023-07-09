package nl.leobaehre.opengui.listener;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.manager.ConversationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ConversationListener implements Listener {

    OpenGui plugin;

    public ConversationListener(OpenGui plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (ConversationManager.inConversation.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!event.getMessage().equalsIgnoreCase("cancel")) {
                        ConversationManager.inConversation.get(player.getUniqueId()).call(event.getMessage());
                    }
                    ConversationManager.inConversation.remove(player.getUniqueId());
                }
            }.runTask(plugin);
        }
    }
}
