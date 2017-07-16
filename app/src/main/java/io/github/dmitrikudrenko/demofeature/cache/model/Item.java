package io.github.dmitrikudrenko.demofeature.cache.model;


public class Item {
    private long id;
    private String name;

    public Item(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
