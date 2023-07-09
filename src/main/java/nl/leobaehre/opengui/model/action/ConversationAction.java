package nl.leobaehre.opengui.model.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.manager.ConversationManager;
import nl.leobaehre.opengui.model.ConversationAnswer;
import nl.leobaehre.opengui.model.ItemAction;
import org.bukkit.entity.Player;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ConversationAction extends ItemAction {

    String question;
    Map<String, ConversationAnswer> answers;

    @Override
    public void execute(Player player) {
        ConversationManager.startConversation(player, question, answer -> {
            if (answer.equalsIgnoreCase("cancel")) {
                player.sendMessage(OpenGui.colorize("&cYou have cancelled the conversation!"));
                return;
            }
            if (!answers.containsKey(answer)) {
                player.sendMessage(OpenGui.colorize("&cThat is not a valid answer!"));
                return;
            }
            answers.get(answer).executeActions(player);
        });
    }
}
