package dnd;

import java.util.LinkedHashMap;

public abstract class Location {
    private String description;
    private int columns;
    private int rows;

    LinkedHashMap<Integer, Character> characterLocationMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, InanimateObjects> objectLocationMap = new LinkedHashMap<>();

    public void printLocationMap() {

    }


}
