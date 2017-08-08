package io.github.dmitrikudrenko.demofeature.data;


import io.github.dmitrikudrenko.demofeature.cache.Cache;
import io.github.dmitrikudrenko.demofeature.cache.model.Item;
import io.github.dmitrikudrenko.demofeature.data.refreshstrategy.DataRefreshStrategy;
import io.github.dmitrikudrenko.demofeature.network.Api;
import io.github.dmitrikudrenko.demofeature.network.dto.ItemDto;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class DataProviderImplementation implements DataProvider {
    private final DataRefreshStrategy refreshStrategy;
    private final Api api;
    private final Cache cache;

    public DataProviderImplementation(DataRefreshStrategy refreshStrategy, Api api, Cache cache) {
        this.refreshStrategy = refreshStrategy;
        this.api = api;
        this.cache = cache;
    }

    @Override
    public Observable<List<Item>> getItems() {
        return cache.getItems()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (refreshStrategy.updateData()) {
                            updateItems();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void updateItems() {
        api.getItems()
                .map(new Func1<List<ItemDto>, List<Item>>() {
                    @Override
                    public List<Item> call(List<ItemDto> itemsDto) {
                        List<Item> items = new ArrayList<>(itemsDto.size());
                        for (ItemDto itemDto : itemsDto) {
                            items.add(new Item(
                                    itemDto.getId(), itemDto.getName()
                            ));
                        }
                        return items;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<List<Item>>() {
                            @Override
                            public void call(List<Item> items) {
                                cache.merge(items);
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                //log error
                            }
                        }
                );
    }
}
