package io.github.dmitrikudrenko.demofeature.network;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.dmitrikudrenko.demofeature.network.dto.ItemDto;
import rx.Observable;

public class ApiImplementation implements Api {
    //это может быть реализация через retrofit
    private static final List<ItemDto> items = new ArrayList<>();

    static {
        items.add(new ItemDto(1, "Name #1"));
        items.add(new ItemDto(2, "Name #2"));
        items.add(new ItemDto(3, "Name #3"));
        items.add(new ItemDto(4, "Name #4"));
        items.add(new ItemDto(5, "Name #5"));
        items.add(new ItemDto(6, "Name #6"));
        items.add(new ItemDto(7, "Name #7"));
        items.add(new ItemDto(8, "Name #8"));
        items.add(new ItemDto(9, "Name #9"));
    }
    @Override
    public Observable<List<ItemDto>> getItems() {
        return Observable.just(items).delay(3, TimeUnit.SECONDS);
    }
}
