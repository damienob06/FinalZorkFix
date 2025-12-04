package game;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public abstract class Item implements Interact, Serializable {
    private String description;
    private String name;
    private String location;
    private boolean isVisible;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.isVisible = true;
    }

    public void pickUp() {
        setLocation("Inventory");
        setVisible(false);
    }

    // Drop items
    public void drop(Room room) {
        setLocation(room.getDescription());
        setVisible(true);
    }

    @Override
    public String toString() {
        if (this instanceof Key) {
            return name;
        } else {
            return name + ": " + description;
        }
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }
}


    class Key extends Item implements Interact {
        public Key(String name, String description) {
            super(name, description);
        }

        @Override
        public void interact(Character character, Room room) {
            boolean foundDoor = false;
            for (Item item : room.getVisibleItems()) {
                if (item instanceof Door) {
                    Door door = (Door) item;
                    door.tryInsertKey(character, this);
                    foundDoor = true;
                    break;
                }
            }
            if (!foundDoor) {
                System.out.println("There's nothing here that the key can unlock");

            }
        }
    }

    class Door extends Item implements Interact {
        private boolean isLocked;
        private Room leadsTo;
        private List<String> requiredKeys;
        private Set<String> insertedKeys = new HashSet<>();

        public Door(String name, String description, Room leadsTo, List<String> requiredKeys) {
            super(name, description);
            this.leadsTo = leadsTo;
            this.requiredKeys = requiredKeys;
            this.isLocked = true;
        }



        @Override
        public void interact(Character character, Room room) {
            for (NPC npc : room.getNPCs()) {
                if (npc instanceof Beast && ((Beast) npc).isAlive()) {
                    System.out.println("The " + npc.getName() + " snarls and blocks the door! Defeat it first.");
                    return;
                }
            }

            if (!isLocked) {
                enterDoor(character);
            } else {
                System.out.println("The door is locked. It has " + (requiredKeys.size() - insertedKeys.size()) + " empty keyholes remaining.");
            }
        }

        public void tryInsertKey(Character character, Key key) {
            for (NPC npc : character.getCurrentRoom().getNPCs()) {
                if (npc instanceof Beast && ((Beast) npc).isAlive()) {
                    System.out.println("The " + npc.getName() + " blocks the door! You must defeat it before using any keys.");
                    return;
                }
            }

            String keyName = key.getName();

            if (!requiredKeys.contains(keyName)) {
                System.out.println("The " + keyName + " doesn't fit this door.");
                return;
            }

            if (insertedKeys.contains(keyName)) {
                System.out.println("The " + keyName + " has already been inserted.");
                return;
            }

            insertedKeys.add(keyName);
            character.getInventory().remove(key);
            System.out.println("You insert the " + keyName + " into the door. (" + insertedKeys.size() + "/" + requiredKeys.size() + " keys used)");


            if (insertedKeys.size() == requiredKeys.size()) {
                unlockDoor();
            }
        }

        public Room getLeadsTo() {
            return leadsTo;
        }

        public boolean isLocked(){
            return isLocked;
        }


        private void unlockDoor() {
            isLocked = false;
            System.out.println("The stone door unlocks with a satisfying clunk\nYou push the heavy door to reveal the mighty Treasure Room\nGo North to enter");
        }

        private void enterDoor(Character character) {
            character.setCurrentRoom(leadsTo);
            System.out.println("You walk through the open door into the Treasure Room");
        }
    }


    class Torch extends Item implements Interact {
        private boolean isLit;

        public Torch(String name, String description) {
            super(name, description);
            this.isLit = false;
        }

        @Override
        public void interact(Character character, Room room) {
            if (!isLit) {
                isLit = true;
                System.out.println("The torch is lit. You can now see in the dark");
            } else {
                System.out.println("The torch is already lit");
            }

            for (Item item : room.getItems()) {
                if (item instanceof Key && item.getName().equalsIgnoreCase("Key of Night")) {
                    item.setVisible(true);
                    System.out.println("You can now see the Key of Night");
                }
            }
        }
    }

    class Rope extends Item implements Interact {
        private boolean isLowered;

        public Rope(String name, String description) {
            super(name, description);
            this.isLowered = false;
        }

        @Override
        public void interact(Character character, Room room) {
            if (!isLowered) {
                isLowered = true;
                System.out.println("The rope is lowered. You descend down into the canyon");
            } else {
                System.out.println("The rope is already lowered");
            }

            for (Item item : room.getItems()) {
                if (item instanceof Key && item.getName().equalsIgnoreCase("Key of Stone")) {
                    item.setVisible(true);
                    System.out.println("You can now see the Key of Stone");
                }
            }
        }
    }

    class Axe extends Item implements Interact {
        private boolean treeCut;

        public Axe(String name, String description) {
            super(name, description);
            this.treeCut = false;
        }

        @Override
        public void interact(Character character, Room room) {
            String currentRoom = room.getDescription().toLowerCase();

            if (currentRoom.contains("swamp")) {
                if (!treeCut) {
                    treeCut = true;
                    System.out.println("You cut down the the ancient tree and the Key of Life is revealed within its heart");

                    for (Item item : room.getItems()) {
                        if (item instanceof Key && item.getName().equalsIgnoreCase("Key of Life")) {
                            item.setVisible(true);
                            System.out.println("You can now see the Key of Life");
                        }
                    }
                } else {
                    System.out.println("The tree has already been cut down");
                }
            } else {
                System.out.println("There is nothing here to chop down");
            }
        }
    }

    class Raft extends Item implements Interact {
        private Room river;
        private Room riverShrine;

        public Raft(String name, String description, Room river, Room riverShrine) {
            super(name, description);
            this.river = river;
            this.riverShrine = riverShrine;
        }

        @Override
        public void interact(Character character, Room room) {
            if (room == river) {
                System.out.println("You place the raft on the water and start paddling towards the River Shrine");
                character.setCurrentRoom(riverShrine);
                System.out.println(character.getCurrentRoom().getLongDescription());
            } else if (room == riverShrine) {
                System.out.println("You place the raft in the water and paddle back to the river bank");
                character.setCurrentRoom(river);
                System.out.println(character.getCurrentRoom().getLongDescription());
            } else {
                System.out.println("You can't use the raft here. There's no water nearby");
            }
        }
    }

    class HikingBoots extends Item implements Interact {
        private Room volcano;
        private Room lavaTemple;

        public HikingBoots(String name, String description, Room volcano, Room lavaTemple) {
            super(name, description);
            this.volcano = volcano;
            this.lavaTemple = lavaTemple;
        }

        @Override
        public void interact(Character character, Room room) {
            if (room.getId().equals(volcano.getId())) {
                System.out.println("You put on the boots and hike up the volcano to the Lava Temple");
                character.setCurrentRoom(lavaTemple);
                System.out.println(character.getCurrentRoom().getLongDescription());
            } else if (room == lavaTemple) {
                System.out.println("You put back on the boots and hike back down the Volcano");
                character.setCurrentRoom(volcano);
                System.out.println(character.getCurrentRoom().getLongDescription());
            } else {
                System.out.println("You can't use the boots here");
            }
        }
    }

    class IcePick extends Item implements Interact {
        private Room mountains;
        private Room mountainTomb;

        public IcePick(String name, String description, Room mountains, Room mountainTomb) {
            super(name, description);
            this.mountains = mountains;
            this.mountainTomb = mountainTomb;
        }

        @Override
        public void interact(Character character, Room room) {
            if (room == mountains) {
                System.out.println("You use the ice pick to scale up the icy faces of Skyreach Mountain");
                character.setCurrentRoom(mountainTomb);
                System.out.println(character.getCurrentRoom().getLongDescription());
            } else if (room == mountainTomb) {
                System.out.println("You use the ice pick to carefully descend Skyreach Mountain");
                character.setCurrentRoom(mountains);
                System.out.println(character.getCurrentRoom().getLongDescription());
            } else {
                System.out.println("You can't use the ice pick here");
            }
        }
    }

    class CreamyPint extends Item implements Interact {

        public CreamyPint(String name, String description) {
            super(name, description);
        }

        @Override
        public void interact(Character character, Room room) {
            System.out.println("The book is useful to trade");

        }
    }

    class Sword extends Item implements Interact {
        public Sword(String name, String description) {
            super(name, description);
        }

        @Override
        public void interact(Character character, Room room) {
            System.out.println(" ");
        }
    }







