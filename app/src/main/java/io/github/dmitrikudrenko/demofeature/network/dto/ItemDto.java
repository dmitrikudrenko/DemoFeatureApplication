package io.github.dmitrikudrenko.demofeature.network.dto;


public class ItemDto {
    private long id;
    private String name;

    public ItemDto(long id, String name) {
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
