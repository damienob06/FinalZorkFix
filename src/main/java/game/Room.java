package game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private Map<Direction, Room> exits;
    private ArrayList<Item> items;
    private ArrayList<NPC> npcs;
    private Chest<Item> chest;

    public Room(String id, String description) {
        this.id = id;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.npcs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    // Exits
    public void setExit(Direction direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(Direction direction) {
        return exits.get(direction);
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (Direction direction : exits.keySet()) {
            sb.append(direction.toString().toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }

    public void addItem(Item item) {
        items.add(item);
        item.setLocation(this.description);
    }

    public boolean removeItem(Item item) {
        boolean removed = items.remove(item);
        if (removed) item.setLocation(null);
        return removed;
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.isVisible() && item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> getVisibleItems() {
        List<Item> visible = new ArrayList<>();
        for (Item item : items) {
            if (item.isVisible()) {
                visible.add(item);
            }
        }
        return visible;
    }

    public String getItemString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (Item item : items) {
            if (item.isVisible()) {
                if (count > 0) {
                    sb.append(", ");
                }
                sb.append(item.getName()).append(" ");
                count++;
            }
        }
        return count == 0 ? "No visible items here." : sb.toString().trim();
    }

    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return description
                + "\nExits: " + getExitString()
                + "\nItems: " + getItemString()
                + "\nNPCs: " + getNPCString()
                + (chest != null? "\nChest: Take and put items here" : "")
                + "\n";

    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    public NPC getNPC(String name) {
        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(name)) {
                return npc;
            }
        }
        return null;
    }

    public List<NPC> getNPCs() {
        return npcs;
    }

    public String getNPCString() {
        if(npcs.isEmpty()) {
            return "No one is here";
        }

        StringBuilder sb = new StringBuilder();
        for (NPC npc : npcs) {
            sb.append(npc.getName()).append(" ");
        }
        return sb.toString().trim();
    }

    public void addChest(Chest<Item> chest) {
        this.chest = chest;
    }

    public Chest<Item> getChest() {
        return chest;
    }
}
