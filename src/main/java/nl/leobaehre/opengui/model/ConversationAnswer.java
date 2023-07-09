package nl.leobaehre.opengui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
@Getter
public class ConversationAnswer {

    String answer;
    List<ItemAction> actions;

    public void executeActions(Player player) {
        for (ItemAction action : actions) {
            action.execute(player);
        }
    }

    @Override
    public String toString() {
        return "ConversationAnswer{" +
                "answer='" + answer + '\'' +
                ", actions=" + actions.getClass().getSimpleName() +
                '}';
    }
}
