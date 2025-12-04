package game;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Character implements Serializable {
    private Room currentRoom;
    private List<Item> inventory;
    private int maxInventorySize = 10;

    public Character(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void pickUpItem(String itemName) {
        if(inventory.size() >= maxInventorySize){
            System.out.println("You can't pick up more items!");
            return;
        }

        Item item = currentRoom.getItemByName(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            item.pickUp();
            inventory.add(item);
            System.out.println("You picked up the " + item.getName() + "\n");
        } else {
            System.out.println("There is no " + itemName + " here to pick up");
        }
    }

    public void useItem(String itemName) {
        Item item = null;

        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                item = i;
                break;
            }
        }
        if (item != null){
            item.interact(this, currentRoom);
        } else {
            if(itemName.equalsIgnoreCase("key")){
                boolean usedAny = false;
                for (Item i : inventory) {
                    if (i instanceof Key) {
                        i.interact(this, currentRoom);
                        usedAny = true;
                    }
                }
                if(!usedAny){
                    System.out.println("You have no keys to use");
                }
            } else {
                System.out.println("You don't have a " + itemName + " in your inventory\n");
            }
        }
    }


    public void showInventory() {
        if(inventory.isEmpty()){
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory: ");
            for (Item item : inventory){
                System.out.println("- " + item);
            }
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }
}




