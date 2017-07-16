package io.github.dmitrikudrenko.demofeature.network;


import java.util.List;

import io.github.dmitrikudrenko.demofeature.network.dto.ItemDto;
import rx.Observable;

public interface Api {
    Observable<List<ItemDto>> getItems();
}
