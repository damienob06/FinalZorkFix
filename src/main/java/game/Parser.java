package game;

import java.util.Scanner;

public class Parser {
    private CommandWords commands;

    public Parser() {
        commands = new CommandWords();
    }

    public Command parseCommand(String inputLine) {
        return tokenize(inputLine);
    }

    private Command tokenize(String inputLine) {

        String word1 = null;
        String word2 = null;
        String word3 = null;
        String word4 = null;

        if (inputLine != null) {
            Scanner tokenizer = new Scanner(inputLine);
            if (tokenizer.hasNext()) word1 = tokenizer.next();
            if (tokenizer.hasNext()) word2 = tokenizer.next();
            if (tokenizer.hasNext()) word3 = tokenizer.next();
            if (tokenizer.hasNext()) word4 = tokenizer.next();
        }

        if (!commands.isCommand(word1)) {
            return new Command(null, word2);
        }

        // Determine direction from any word
        Direction direction = Direction.fromString(word2);

        Command cmd;

        if (word4 != null) {
            cmd = new Command(word1, word2, word3, word4);
        }
        else if (word3 != null) {
            cmd = new Command(word1, word2, word3);
        }
        else {
            cmd = new Command(word1, word2);
        }

        if (direction != null) {
            cmd.setDirection(direction);
        }

        return cmd;
    }

    public void showCommands() {
        commands.showAll();
    }
}
