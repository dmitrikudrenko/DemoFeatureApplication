package io.github.dmitrikudrenko.demofeature.cache;


import java.util.List;

import io.github.dmitrikudrenko.demofeature.cache.model.Item;
import rx.Observable;

public interface Cache {
    Observable<List<Item>> getItems();

    void merge(List<Item> items);
}
