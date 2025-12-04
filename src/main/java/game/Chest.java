package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chest<T> implements Serializable {
    private List<T> contents;

    public Chest() {
        contents = new ArrayList<>();
    }

    public void addItem(T item) {
        contents.add(item);
    }

    public boolean removeItem(T item) {
        return contents.remove(item);
    }

    public List<T> getContents() {
        return contents;
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public String toString() {
        return "Chest containing: " + contents;
    }
}
