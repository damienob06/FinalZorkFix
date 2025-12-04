package game;

import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private Map<String, String> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("use", "Use an item");
        validCommands.put("take", "Pick up the item or take out of chest");
        validCommands.put("save", "Save Game");
        validCommands.put("load", "Load Game");
        validCommands.put("inventory", "Show what you're carrying");
        validCommands.put("talk", "Talk with NPCs");
        validCommands.put("givekeys", "Give all keys");
        validCommands.put("put", "Put items in the chest");
        validCommands.put("open", "View items in the chest");
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public void showAll() {
        System.out.print("Valid commands are: ");
        for (String command : validCommands.keySet()) {
            System.out.print(command + " ");
        }
        System.out.println();
    }
}
