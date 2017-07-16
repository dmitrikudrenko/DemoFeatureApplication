package io.github.dmitrikudrenko.demofeature.cache;


import java.util.List;

import io.github.dmitrikudrenko.demofeature.cache.model.Item;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public class CacheImplementation implements Cache {
    //здесь может быть реализация через sqlite или realm
    private BehaviorSubject<List<Item>> itemsObservable = BehaviorSubject.create();

    @Override
    public Observable<List<Item>> getItems() {
        return itemsObservable;
    }

    @Override
    public void merge(List<Item> items) {
        itemsObservable.onNext(items);
    }
}
