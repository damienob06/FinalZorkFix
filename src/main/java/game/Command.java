package game;

public class Command {
    private String commandWord;
    private String secondWord;
    private String thirdWord;
    private String fourthWord;
    private Direction direction;

    public Command(String firstWord, String secondWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }
    public Command(String firstWord, String secondWord, String thirdWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    public Command(String firstWord, String secondWord, String thirdWord, String fourthWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
        this.fourthWord = fourthWord;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public String getThirdWord() {
        return thirdWord;
    }

    public String getFourthWord() {
        return fourthWord;
    }

    public boolean hasSecondWord() {
        return secondWord != null;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean hasDirection() {
        return direction != null;
    }
}
