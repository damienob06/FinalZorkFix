package game;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public static Direction fromString(String input) {
        if (input == null) return null;

        switch (input.toLowerCase()) {
            case "north": return NORTH;
            case "south": return SOUTH;
            case "east":  return EAST;
            case "west":  return WEST;
            default: return null;
        }
    }


}
