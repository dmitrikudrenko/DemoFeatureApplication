package io.github.dmitrikudrenko.demofeature.cache;


import java.util.ArrayList;
import java.util.List;

import io.github.dmitrikudrenko.demofeature.cache.model.Item;
import rx.Observable;

public class DemoCacheImplementation implements Cache {
    //эти данные могут храниться в заранее подготовленной БД
    private static final List<Item> demoItems = new ArrayList<>();

    static {
        demoItems.add(new Item(1, "Demo Item 1"));
        demoItems.add(new Item(2, "Demo Item 2"));
        demoItems.add(new Item(3, "Demo Item 3"));
        demoItems.add(new Item(4, "Demo Item 4"));
        demoItems.add(new Item(5, "Demo Item 5"));
        demoItems.add(new Item(6, "Demo Item 6"));
        demoItems.add(new Item(7, "Demo Item 7"));
        demoItems.add(new Item(8, "Demo Item 8"));
        demoItems.add(new Item(9, "Demo Item 9"));
    }

    @Override
    public Observable<List<Item>> getItems() {
        return Observable.just(demoItems);
    }

    @Override
    public void merge(List<Item> items) {

    }
}
