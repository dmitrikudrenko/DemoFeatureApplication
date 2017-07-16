package io.github.dmitrikudrenko.demofeature.data;


import java.util.List;

import io.github.dmitrikudrenko.demofeature.cache.model.Item;
import rx.Observable;

public interface DataProvider {
    Observable<List<Item>> getItems();
}
