package game;/* goThis game is a classic text-based adventure set in a university environment.
   The player starts outside the main entrance and can navigate through different rooms like a
   lecture theatre, campus pub, computing lab, and admin office using simple text commands (e.g., "go east", "go west").
    The game provides descriptions of each location and lists possible exits.

Key features include:
game.Room navigation: Moving among interconnected rooms with named exits.
Simple command parser: Recognizes a limited set of commands like "go", "help", and "quit".
Player character: Tracks current location and handles moving between rooms.
Text descriptions: Provides immersive text output describing the player's surroundings and available options.
Help system: Lists valid commands to guide the player.
Overall, it recreates the classic Zork interactive fiction experience with a university-themed setting,
emphasizing exploration and simple command-driven gameplay
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

public class ZorkULGame {
    private Parser parser;
    private Character player;
    private Map<String, Room> allRooms;
    private Room river;
    private Room riverShrine;
    private Room volcano;
    private Room lavaTemple;
    private Room mountains;
    private Room mountainTomb;

    public ZorkULGame() {
        allRooms = new HashMap<>();
        createRooms();
        parser = new Parser();
    }

    public String handleCommand(String input){
        Command command = parser.parseCommand(input);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream oldOutput = System.out;

        System.setOut(printStream);
        processCommand(command);
        System.setOut(oldOutput);
        return outputStream.toString();
    }

    public void createRooms() {
        Room basecamp, canyonObstacle, caves, swamp, lostCityRuins, throneRoom, treasureRoomEntrance, treasureRoom, fortress, path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11;

        // create rooms
        basecamp = new Room("basecamp", "You are beside the campfire at Basecamp");
        river = new Room("river", "You are at Rushing Rapids of the Jungle\nYou see the River Shrine across the water to the West");
        riverShrine = new Room("riverShrine", "You are by the River Shrine where the Key of Tides resides");
        canyonObstacle = new Room("canyonObstacle", "You are at the edge of the vast Sandstone Canyon where the Key of Stone calls home\nYou must find a rope to lower yourself down and take the key");
        volcano = new Room("volcano", "You are at the mighty Hellfire Volcano\nYou see the firey Lava Temple to the West");
        lavaTemple = new Room("lavaTemple", "You enter the Lava Temple built inside the volcano’s rim where the Key of Fire resides");
        caves = new Room("caves", "You enter the pitch black underground labyrinth of echoing caves that protects the Key of Night\nYou must find a torch to light the way");
        swamp = new Room("swamp", "You wade into The Emerald Swamp which possess the Tree of Breath that guards The Key of Life");
        mountains = new Room("mountains", "You are at the foot of the towering peak called Skyreach Mountain\nYou see The Mountain Tomb to the East");
        mountainTomb = new Room("mountainTomb", "You enter The Tomb of the Warriors that holds The Key of Ice");
        lostCityRuins = new Room("lostCityRuins", "You enter The ruined capital of Ardaen\nYou see the capital's fortress on the hill to the North");
        fortress = new Room("fortress", "You enter the crumbling, vine-covered fortress, lost in the jungle\nYou see the King's Throne Room to the West");
        throneRoom = new Room("throneRoom", "You enter the King's Throne Room, half-collapsed and flooded with golden light through broken domes\nYou see a stairs to the West behind the throne");
        treasureRoomEntrance = new Room("treasureRoomEntrance", "You go down the stairs\nYou enter a dimly lit vast chamber with a stone door, carved with elaborate artwork\nThere are 7 key holes in the door");
        treasureRoom = new Room("treasureRoom", "\n\nCongratulations, brave treasure hunter!\n\nYou have discovered the glorious treasure chamber\nIt is overflowing with gold, jewels, scattered coins, and glittering crowns\nEvery surface sparkles with forgotten riches, a reward for your courage and determination\nYour adventure ends here, but your legend will live on\n\nThe game is complete\n");

        path1 = new Room("path1", "You see the Rushing Rapids of the Jungle to the North and Basecamp to the South");
        path2 = new Room("path2", "You see the Caves of Nightfall to the North and Basecamp to the South");
        path3 = new Room("path3", "You see the impressive Sandstone Canyon to the North and Basecamp to the South");
        path4 = new Room("path4", "You see The Hellfire Volcano to the North, The Caves of Nightfall to the East and the Rushing Rapids to the South");
        path5 = new Room("path5", "You see the mesmerising Emerald Swamp to the North and The Caves of Nightfall to the West");
        path6 = new Room("path6", "You see The Caves of Nightfall to the East, the Rushing Rapids and the Hellfire Volcano to the West");
        path7 = new Room("path7", "You see The Caves of Nightfall to the West, The Emerald Swamp and the Sandstone Canyon to the East");
        path8 = new Room("path8", "You see The Lost City Of Ardea in the distance to the North and The Hellfire Volcano to the South");
        path9 = new Room("path9", "You see the mighty Skyreach Mountains to the North and The Caves of Nightfall to the South");
        path10 = new Room("path10", "You see The Lost City Of Ardea in the distance to the North and The Emerald Swamp to the South");
        path11 = new Room("path11", "You see The Lost City Of Ardea in the distance to the North and The Skyreach Mountains to the South");

        allRooms.put(basecamp.getId(), basecamp);
        allRooms.put(river.getId(), river);
        allRooms.put(riverShrine.getId(), riverShrine);
        allRooms.put(canyonObstacle.getId(), canyonObstacle);
        allRooms.put(volcano.getId(), volcano);
        allRooms.put(lavaTemple.getId(), lavaTemple);
        allRooms.put(caves.getId(), caves);
        allRooms.put(swamp.getId(), swamp);
        allRooms.put(mountains.getId(), mountains);
        allRooms.put(mountainTomb.getId(), mountainTomb);
        allRooms.put(lostCityRuins.getId(), lostCityRuins);
        allRooms.put(fortress.getId(), fortress);
        allRooms.put(throneRoom.getId(), throneRoom);
        allRooms.put(treasureRoomEntrance.getId(), treasureRoomEntrance);
        allRooms.put(treasureRoom.getId(), treasureRoom);
        allRooms.put(path1.getId(), path1);
        allRooms.put(path2.getId(), path2);
        allRooms.put(path3.getId(), path3);
        allRooms.put(path4.getId(), path4);
        allRooms.put(path5.getId(), path5);
        allRooms.put(path6.getId(), path6);
        allRooms.put(path7.getId(), path7);
        allRooms.put(path8.getId(), path8);
        allRooms.put(path9.getId(), path9);
        allRooms.put(path10.getId(), path10);
        allRooms.put(path11.getId(), path11);


        // initialise room exits
        basecamp.setExit(Direction.WEST, path1);
        basecamp.setExit(Direction.NORTH, path2);
        basecamp.setExit(Direction.EAST, path3);

        path1.setExit(Direction.NORTH, river);
        path1.setExit(Direction.SOUTH, basecamp);

        path2.setExit(Direction.NORTH, caves);
        path2.setExit(Direction.SOUTH, basecamp);

        path3.setExit(Direction.NORTH, canyonObstacle);
        path3.setExit(Direction.SOUTH, basecamp);

        river.setExit(Direction.WEST, riverShrine);
        river.setExit(Direction.NORTH, path4);
        river.setExit(Direction.SOUTH, path1);

        riverShrine.setExit(Direction.EAST, river);

        path4.setExit(Direction.NORTH, volcano);
        path4.setExit(Direction.SOUTH, river);
        path4.setExit(Direction.EAST, path6);

        canyonObstacle.setExit(Direction.NORTH, path5);
        canyonObstacle.setExit(Direction.SOUTH, path3);

        path5.setExit(Direction.NORTH, swamp);
        path5.setExit(Direction.WEST, path7);
        path5.setExit(Direction.SOUTH, canyonObstacle);

        path6.setExit(Direction.WEST, path4);
        path6.setExit(Direction.EAST, caves);

        path7.setExit(Direction.EAST, path5);
        path7.setExit(Direction.WEST, caves);

        caves.setExit(Direction.NORTH, path9);
        caves.setExit(Direction.SOUTH, path2);
        caves.setExit(Direction.WEST, path6);
        caves.setExit(Direction.EAST, path7);

        volcano.setExit(Direction.NORTH, path8);
        volcano.setExit(Direction.SOUTH, path4);
        volcano.setExit(Direction.WEST, lavaTemple);

        lavaTemple.setExit(Direction.EAST, volcano);

        path8.setExit(Direction.NORTH, lostCityRuins);
        path8.setExit(Direction.SOUTH, volcano);

        path9.setExit(Direction.NORTH, mountains);
        path9.setExit(Direction.SOUTH, caves);

        swamp.setExit(Direction.NORTH, path10);
        swamp.setExit(Direction.SOUTH, path5);

        path10.setExit(Direction.NORTH, lostCityRuins);
        path10.setExit(Direction.SOUTH, swamp);

        mountains.setExit(Direction.NORTH, path11);
        mountains.setExit(Direction.SOUTH, path9);
        mountains.setExit(Direction.EAST, mountainTomb);

        mountainTomb.setExit(Direction.WEST, mountains);

        path11.setExit(Direction.NORTH, lostCityRuins);
        path11.setExit(Direction.SOUTH, mountains);

        lostCityRuins.setExit(Direction.NORTH, fortress);
        lostCityRuins.setExit(Direction.WEST, path8);
        lostCityRuins.setExit(Direction.EAST, path10);
        lostCityRuins.setExit(Direction.SOUTH, path11);

        fortress.setExit(Direction.WEST, throneRoom);
        fortress.setExit(Direction.SOUTH, lostCityRuins);

        throneRoom.setExit(Direction.WEST, treasureRoomEntrance);
        throneRoom.setExit(Direction.EAST, fortress);

        treasureRoomEntrance.setExit(Direction.NORTH, treasureRoom);
        treasureRoomEntrance.setExit(Direction.EAST, throneRoom);

        treasureRoom.setExit(Direction.SOUTH, treasureRoomEntrance);


        // Create 7 keys and locations
        Key key1 = new Key("Key of Tides", "");
        riverShrine.addItem(key1);
        Key key2 = new Key("Key of Stone","");
        key2.setVisible(false);
        canyonObstacle.addItem(key2);
        Key key3 = new Key("Key of Night","");
        key3.setVisible(false);
        caves.addItem(key3);
        Key key4 = new Key("Key of Fire","");
        lavaTemple.addItem(key4);
        Key key5 = new Key("Key of Life","");
        key5.setVisible(false);
        swamp.addItem(key5);
        Key key6 = new Key("Key of Ice","");
        mountainTomb.addItem(key6);
        Key key7 = new Key("Key of Kings","");

        Chest<Item> basecampChest = new Chest<>();
        basecamp.addChest(basecampChest);

        Door treasureRoomDoor = new Door("Treasure Room Door", "A colossal door with seven keyholes, guarding the treasure room.", treasureRoom, treasureKeys);

        Torch torch = new Torch("Torch", "Use the torch to light dark places");
        volcano.addItem(torch);

        Rope rope = new Rope("Rope", "Use to lower you from heights");
        riverShrine.addItem(rope);

        Raft raft = new Raft("Raft", "Use the raft to paddle across water", river, riverShrine);
        basecampChest.addItem(raft);

        Axe axe = new Axe("Axe", "Use the axe to cut down trees");
        fortress.addItem(axe);

        HikingBoots boots = new HikingBoots("Hiking Boots", "Use boots to cross hot surfaces", volcano, lavaTemple);
        mountains.addItem(boots);

        IcePick icePick = new IcePick("Ice Pick", "Use to climb and descend steep mountains", mountains, mountainTomb);
        mountains.addItem(icePick);

        CreamyPint book = new CreamyPint("Creamy Pint", "Use to trade");
        river.addItem(book);

        Sword sword = new Sword("Sword", "Use to slay the Beast");
        path10.addItem(sword);

        treasureRoomEntrance.addItem(treasureRoomDoor);

        Guide guide = new Guide("Guide");
        basecamp.addNPC(guide);

        Protector guardian = new Protector("Protector", key7, book);
        throneRoom.addNPC(guardian);

        Beast treasureBeast = new Beast("Treasure Beast");
        treasureRoomEntrance.addNPC(treasureBeast);

        player = new Character(basecamp);
    }

    // Treasure door that links to treasure room
    List<String> treasureKeys = List.of(
            "Key of Tides",
            "Key of Stone",
            "Key of Night",
            "Key of Fire",
            "Key of Life",
            "Key of Ice",
            "Key of Kings"
    );

    public Character getPlayer() {
        return player;
    }

    public boolean processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "take":
                pickUpItem(command);
                break;
            case "inventory":
                player.showInventory();
                break;
            case "use":
                use(command);
                break;
            case "talk":
                interact(command);
                break;
            case "save" :
                saveGame("SavedGame");
                break;
            case "load" :
                resumeGame("SavedGame");
                break;
            case "givekeys":
                giveAllKeys();
                break;
            case "put":
                putInChest(command);
                break;
            case "open":
                openChest();
                break;

            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are lost\nYou are alone\nYou wander around the lost kingdom\nFind the 7 keys to unlock the lost treasure\nUse items that you find along the way to help gather the keys");
        System.out.println("Your command words are: ");
        parser.showCommands();
        System.out.println();
    }

    private void goRoom(Command command) {

        if (!command.hasSecondWord() && !command.hasDirection()) {
            System.out.println("Go where?");
            return;
        }

        Direction direction = command.getDirection();

        if (direction == null){
            direction = Direction.fromString(command.getSecondWord());
            if (direction == null){
                System.out.println("Invalid direction");
                return;
            }
        }

        Room current = player.getCurrentRoom();
        Room nextRoom = current.getExit(direction);

        if(current == river && direction == Direction.WEST) {
            System.out.println("The rapids are too strong to cross without a raft!");
            return;
        }

        if(current == riverShrine && direction == Direction.EAST) {
            System.out.println("You can’t swim back! Use the raft to return east");
            return;
        }

        if(current == volcano && direction == Direction.WEST) {
            System.out.println("The volcano surface is too hot to walk across without hiking boots!");
            return;
        }

        if(current == lavaTemple && direction == Direction.EAST) {
            System.out.println("You can’t walk back over the hot surface!\nUse the hiking boots to return east");
            return;
        }

        if(current == mountains && direction == Direction.EAST) {
            System.out.println("The face of Skyreach Mountain is too steep and icy\nUse ice pick to climb");
            return;
        }

        if(current == mountainTomb && direction == Direction.WEST) {
            System.out.println("The face of Skyreach Mountain is too steep and icy\nUse ice pick to descend");
            return;
        }

        for(Item i : current.getItems()) {
            if (i instanceof Door door) {
                if (door.getLeadsTo() == nextRoom) {
                    NPC beastNPC = current.getNPC("Treasure Beast");
                    if(beastNPC != null && beastNPC instanceof Beast) {
                        Beast beast = (Beast) beastNPC;
                        if (beast.isAlive()) {
                            System.out.println("The Treasure Beast blocks the way! You cannot pass.");
                            return;
                        }
                    }
                    if (door.isLocked()) {
                        System.out.println("The door is locked. You cannot pass.");
                        return;
                    }
                }
            }
        }

        if (nextRoom == null) {
            System.out.println("You can't go that way\n");
            return;
        }

        player.setCurrentRoom(nextRoom);
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void pickUpItem(Command command) {

        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        StringBuilder itemNameBuilder = new StringBuilder(command.getSecondWord());
        if (command.getThirdWord() != null) itemNameBuilder.append(" ").append(command.getThirdWord());
        if (command.getFourthWord() != null) itemNameBuilder.append(" ").append(command.getFourthWord());
        String itemName = itemNameBuilder.toString().trim();

        Room room = player.getCurrentRoom();

        Item roomItem = room.getItemByName(itemName);
        if (roomItem != null) {
            player.pickUpItem(itemName);
            return;
        }

        Chest<Item> chest = room.getChest();
        if (chest != null && !chest.isEmpty()) {
            Item chestItem = null;
            for (Item i : chest.getContents()) {
                if (i.getName().equalsIgnoreCase(itemName)) {
                    chestItem = i;
                    break;
                }
            }
            if (chestItem != null) {
                chest.removeItem(chestItem);
                player.getInventory().add(chestItem);
                System.out.println("You take the " + chestItem.getName() + " from the chest\n");
                return;
            }
        }

        System.out.println("There is no " + itemName + " here to take\n");
    }


    private void use(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (command.getThirdWord() != null) itemName += " " + command.getThirdWord();
        if (command.getFourthWord() != null) itemName += " " + command.getFourthWord();
        itemName = itemName.trim();

        for(NPC npc : player.getCurrentRoom().getNPCs()) {
            if (npc instanceof Beast) {
                if (itemName.equalsIgnoreCase("sword")) {
                    ((Beast) npc).attackWithSword(player, player.getCurrentRoom());
                    return;
                }
            }
        }
        player.useItem(itemName);
    }

    private void interact(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Interact with who?");
            return;
        }
        String target = command.getSecondWord();
        NPC npc = player.getCurrentRoom().getNPC(target);

        if (npc == null) {
            System.out.println("There is no " + target + " here");
            return;
        }
        npc.interact(player, player.getCurrentRoom());
    }
    public void saveGame(String filename){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){

            out.writeObject(player);
            out.writeObject(allRooms);

            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void resumeGame(String filename){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){

            player = (Character) in.readObject();
            allRooms = (Map<String, Room>) in.readObject();

            System.out.println("Game loaded successfully!");
            System.out.println(player.getCurrentRoom().getLongDescription());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }

    private void giveAllKeys(){
        for(String keyName : treasureKeys) {
            Key key = new Key(keyName, "");
            player.getInventory().add(key);
            System.out.println(keyName + " added to your inventory");

        }
    }

    private void putInChest(Command command){
        StringBuilder itemNameBuilder = new StringBuilder(command.getSecondWord());
        if (command.getThirdWord() != null) itemNameBuilder.append(" ").append(command.getThirdWord());
        if (command.getFourthWord() != null) itemNameBuilder.append(" ").append(command.getFourthWord());
        String itemName = itemNameBuilder.toString().trim();

        Room currentRoom = player.getCurrentRoom();
        Chest<Item> chest = currentRoom.getChest();

        if(chest == null){
            System.out.println("There is no item in the chest");
        }
        Item itemToPut = null;
        for(Item i : player.getInventory()){
            if(i.getName().equalsIgnoreCase(itemName)){
                itemToPut = i;
                break;
            }
        }
        if(itemToPut != null){
            player.getInventory().remove(itemToPut);
            chest.addItem(itemToPut);
            System.out.println("You put the " + itemName + " into the chest");
        } else{
            System.out.println("You don't have a " + itemName + " in your inventory");
        }
    }

    private void openChest() {
        Room current = player.getCurrentRoom();
        Chest<Item> chest = current.getChest();

        if (chest == null) {
            System.out.println("There is no chest here");
            return;
        }

        if (chest.isEmpty()) {
            System.out.println("The chest is empty");
            return;
        }

        System.out.println("The chest contains:");
            for (Item item : chest.getContents()) {
                System.out.println("- " + item.getName());
            }
        }
    }

