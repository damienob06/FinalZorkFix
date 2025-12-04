# ZORKUL – TEXT ADVENTURE GAME

**ZorkUL** is a Java-based text adventure inspired by classic interactive fiction games. You explore a large interconnected world filled with environmental hazards, items, non-player characters, puzzles, and a final boss encounter. Your objective is to explore the regions, collect the seven keys, defeat the Treasure Beast, and unlock the final Treasure Room.

---

## GAME OVERVIEW

You play as an explorer traveling through themed regions:

- **Rushing Rapids** 
- **Caves of Nightfall**
- **Canyon**
- **Volcano** 
- **Skyreach Mountain**
- **Emerald Swamp**
- **Ancient Ruins and Fortress**

Each region contains hazards that require specific items, NPCs that offer lore or interaction, and hidden or locked areas and items.

---

## GAME FEATURES

### **Exploration**
- Move freely through rooms connected by directional exits.
- Each room provides detailed descriptions, available items, exits, and NPCs.

### **Traversal-Based Puzzles**
- Certain areas cannot be crossed without specific tools:
    - Raft for crossing the rapids
    - Hiking Boots for walking on volcano surfaces
    - Ice Pick for climbing the icy mountain face
    - Axe for cutting down trees
    - Rope for lowering yourself from high places
    - Torch to light the Cave

### **Inventory and Items**
- Pick up and use items. 

### **NPC Interactions**
- Speak with characters to obtain lore and clues.
- Trade with Protector for the Key of Kings
- The Treasure Beast must be defeated using the sword before reaching the Treasure Room.

### **Audio Support**
- Background music with looping functionality is included.

---

## PLAYER COMMANDS

| Command          | Description                                     |
|------------------|-------------------------------------------------|
| `go <direction>` | Move to another room (north, south, east, west) |
| `use <item>`     | Use an item in your inventory                   |
| `take <item>`    | Pick up an item from the room or chest          |
| `inventory`      | Displays items you are carrying                 |
| `talk <npc>`     | Speak with an NPC in the room                   |
| `put`            | Put item into chest                             |
| `open `          | Opens a treasure chest                          |
| `help`           | Show all available commands                     |
| `quit`           | Exit the game                                   |
| `save`           | Save current game                               |
| `load`           | Load previous game                              |

---

## ITEM-BASED PROGRESSION RULES

### **Rushing Rapids**
- You cannot cross west over the rapids without the raft.
- You cannot return east from the River Shrine without using the raft again.

### **Volcano**
- The lava surface cannot be crossed without the hiking boots.
- Returning requires the same item.

### **Skyreach Mountain**
- The steep cliff cannot be climbed or descended without using the ice pick.

### **Caves of Nightfall** 
- The Key of Night is invisible until the torch is lit.

### **Canyon**
- The Key of Stone is invisible until the rope is lowered.

### **Swamp**
- The Key of Life is invisible until the Tree of Life is cut down with the axe.

### **Locked Treasure Room Door**
- The Treasure Beast blocks access to the final area until all 7 keys are used.

---

## HOW TO RUN THE GAME

### Running through an IDE
- Works in IntelliJ or any other IDE
- Must install Java(version 25 preferable)
- Must have javaFX downloaded

---

## PROJECT STRUCTURE (OVERVIEW)

- **game package** – main game logic, parser, commands, rooms, items, doors, NPCs, chests and directions
- **gui package** – GUI code, music player and looping logic
- **resources** - images and .wav music file for GUI

---

## CREDITS

- Created by: *Damien O Brien*
