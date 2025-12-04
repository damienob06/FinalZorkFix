package game;

import java.io.Serializable;

public abstract class NPC implements Interact, Serializable {
    protected String name;

    public NPC(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public abstract void interact(Character player, Room room);
}

class Guide extends NPC implements Talk{
    public Guide(String name) {
        super(name);
    }
    @Override
    public void talk(){
        System.out.println(name + ": Welcome to Keys of the Lost Empire!\n\n" +
                "Explorer, the fallen kingdom of Ardaen awaits. Its seven keys lie hidden across dangerous lands, each guarding \n" +
                "the way to the Vault of Eternum.\n" +
                "\n" +
                "Long ago, Ardaen’s rulers sealed their greatest treasure within that Vault and scattered the keys across the realm. \n" +
                "Only one who proves worthy can reclaim them all.\n" +
                "\n" +
                "You begin at Basecamp.  \n" +
                "Good luck on your journey.\n" +
                "\n" +
                "Type 'help' for assistance.");
    }

    @Override
    public void interact(Character character, Room room) {
        talk();
    }
}

class Protector extends NPC implements Talk{

    private Item itemForTrade;
    private Item itemToTradeFor;

    public Protector(String name, Item itemForTrade, Item itemToTradeFor) {
        super(name);
        this.itemForTrade = itemForTrade;
        this.itemToTradeFor = itemToTradeFor;
    }


    @Override
    public void talk() {
        System.out.println(name + ": Congratulations brave Adventurer\n" +
                "You have only key left to collect before you unlock the treasure\n" +
                "I guard the Key of Kings. Give me a Creamy Pint and I will trade it with you\n\n" +
                "Checking for a Creamy Pint....\n");
        System.out.flush();
    }

    @Override
    public void interact(Character player, Room room) {
        talk();

        new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            javafx.application.Platform.runLater(() -> {

                Item foundItem = null;
                for (Item i : player.getInventory()) {
                    if (i.equals(this.itemToTradeFor)) {
                        foundItem = i;
                        break;
                    }
                }
                if (foundItem != null) {
                    player.getInventory().remove(foundItem);
                    player.getInventory().add(itemForTrade);
                    System.out.println(name + ": Thank you. Here is the Key of Kings\nBest of luck on the rest of your journey\n");
                } else {
                    System.out.println(name + ": You don't have a Creamy Pint to trade\nCome back when you do\n");
                }
                System.out.flush();
            });
        }).start();
    }
}

class Beast extends NPC {
    private boolean isAlive = true;

    public Beast(String name) {
        super(name);
    }

    public boolean isAlive(){
        return isAlive;
    }

    @Override
    public void interact(Character player, Room room) {

        if (!isAlive) {
            System.out.println(name + " is already defeated");
            return;
        }
        System.out.println("The " + name + " growls menacingly. You must use a Sword to defeat it!");
    }

    public void attackWithSword(Character player, Room room) {
        if (!isAlive) {
            System.out.println(name + " is already defeated\n");
            return;
        }

        Item sword = null;
        for (Item i : player.getInventory()) {
            if (i.getName().equalsIgnoreCase("Sword")) {
                sword = i;
                break;
            }
        }

        if (sword != null) {
            isAlive = false;
            System.out.println("You swing your Sword and defeat the " + name + "!\n");
            room.removeNPC(this);
        } else {
            System.out.println("You have nothing to fight with! The " + name + " remains undefeated\n");
        }
    }
}



