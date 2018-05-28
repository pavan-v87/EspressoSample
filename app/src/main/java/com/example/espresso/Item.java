package com.example.espresso;

public class Item {
    private String imageUrl;
    private String name;
    private String description;

    public Item(String n, String d) {
        name = n;
        description = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
