package nl.leobaehre.opengui.model;

import lombok.Getter;
import nl.leobaehre.opengui.OpenGui;
import nl.leobaehre.opengui.model.action.CloseAction;
import nl.leobaehre.opengui.model.action.CommandAction;
import nl.leobaehre.opengui.model.action.ConversationAction;
import nl.leobaehre.opengui.model.action.OpenAction;
import nl.leobaehre.opengui.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

@Getter
public class GuiItem {

    int slot;

    Material material;
    int amount;
    String displayName;
    List<String> lore;
    List<ItemAction> actions;

    public GuiItem(int slot, Material material, int amount, String displayName, List<String> lore, List<ItemAction> actions) {
        this.slot = slot;
        this.material = material;
        this.amount = amount;
        this.displayName = displayName;
        this.lore = lore;
        this.actions = actions;
    }

    public ItemStack getItemStack() {
        ItemCreator itemCreator = new ItemCreator(material);
        itemCreator.setAmount(amount);
        itemCreator.setDisplayName(displayName);
        itemCreator.setLore(lore);
        return itemCreator.create();
    }

    public void executeActions(Player player) {
        for (ItemAction action : actions) {
            action.execute(player);
        }
    }

    public static GuiItem fromSection(ConfigurationSection section, OpenGui plugin) {
        int slot = section.getInt("slot");
        Material material = Material.valueOf(section.getString("material"));
        int amount = section.getInt("amount", 1);
        String displayName = section.getString("display-name");
        List<String> lore = section.getStringList("lore");
        List<?> actionsList = section.getList("actions");

        List<ItemAction> actions = parseActions(actionsList, plugin);

        return new GuiItem(slot, material, amount, displayName, lore, actions);
    }

    private static List<ItemAction> parseActions(List<?> actionsList, OpenGui plugin) {
        if (actionsList == null) {
            return new ArrayList<>();
        }

        List<ItemAction> actions = new ArrayList<>();

        for (Object actionObj : actionsList) {
            if (!(actionObj instanceof Map<?, ?> actionMap)) {
                throw new IllegalArgumentException("Action is not a valid map");
            }

            String actionType = (String) actionMap.get("type");

            if (actionType == null) {
                throw new IllegalArgumentException("Action type is null");
            }

            switch (actionType) {
                case "command" -> {
                    String command = (String) actionMap.get("command");
                    actions.add(new CommandAction(command));
                }
                case "close" -> {
                    actions.add(new CloseAction());
                }
                case "open" -> {
                    String id = (String) actionMap.get("id");
                    actions.add(new OpenAction(id, plugin));
                }
                case "conversation" -> {
                    String question = (String) actionMap.get("question");
                    String variableName = (String) actionMap.get("variable-name");
                    Map<String, ConversationAnswer> answers = parseConversationAnswers((List<?>) actionMap.get("answers"), plugin);
                    String otherAnswer = parseActions((List<?>) actionMap.get("other-answer"), plugin).get(0).toString();
                    actions.add(new ConversationAction(question, variableName, answers, otherAnswer));
                }
                default -> throw new IllegalArgumentException("Unknown action type: " + actionType);
            }
        }

        return actions;
    }

    private static Map<String, ConversationAnswer> parseConversationAnswers(List<?> answersList, OpenGui plugin) {
        Map<String, ConversationAnswer> answers = new HashMap<>();

        for (Object answerObj : answersList) {
            if (!(answerObj instanceof Map<?, ?> answerMap)) {
                throw new IllegalArgumentException("Answer is not a valid map");
            }

            String answerText = (String) answerMap.get("answer");
            List<ItemAction> answerActions = parseActions((List<?>) answerMap.get("actions"), plugin);
            ConversationAnswer conversationAnswer = new ConversationAnswer(answerText, answerActions);
            answers.put(answerText, conversationAnswer);
        }

        return answers;
    }

}
