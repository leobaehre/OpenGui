package nl.leobaehre.opengui.model.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.manager.ConversationManager;
import nl.leobaehre.opengui.model.ConversationAnswer;
import nl.leobaehre.opengui.model.ItemAction;
import nl.leobaehre.opengui.model.VariableType;
import org.bukkit.entity.Player;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ConversationAction extends ItemAction {

    String question;
    String variableName;
    Map<String, ConversationAnswer> answers;
    String otherAnswer;

    @Override
    public void execute(Player player) {
        ConversationManager.startConversation(player, question, answer -> {
            if (answer.equalsIgnoreCase("cancel")) {
                player.sendMessage(OpenGui.colorize("&cYou have cancelled the conversation!"));
                return;
            }
            if (!answers.containsKey(answer)) {
                player.sendMessage(OpenGui.colorize(otherAnswer));
                return;
            }
            OpenGui.getInstance().getVariableManager().setVariable("answer-" + player.getUniqueId(), answer);
            answers.get(answer).executeActions(player);
        });
    }
}
