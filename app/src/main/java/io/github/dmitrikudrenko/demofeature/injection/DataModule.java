package io.github.dmitrikudrenko.demofeature.injection;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.dmitrikudrenko.demofeature.cache.Cache;
import io.github.dmitrikudrenko.demofeature.cache.CacheImplementation;
import io.github.dmitrikudrenko.demofeature.cache.DemoCacheImplementation;
import io.github.dmitrikudrenko.demofeature.data.DataProvider;
import io.github.dmitrikudrenko.demofeature.data.DataProviderImplementation;
import io.github.dmitrikudrenko.demofeature.network.Api;
import io.github.dmitrikudrenko.demofeature.network.ApiImplementation;

@Module
public class DataModule {
    private boolean demo;

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    @Provides
    @Singleton
    public Api provideApi() {
        return new ApiImplementation();
    }

    @Provides
    @Named("real_cache")
    @Singleton
    public Cache provideRealCache() {
        return new CacheImplementation();
    }

    @Provides
    @Named("demo_cache")
    @Singleton
    public Cache provideDemoCache() {
        return new DemoCacheImplementation();
    }

    @Provides
    public DataProvider provideDataProvider(Api api, @Named("real_cache") Cache realCache, @Named("demo_cache") Cache demoCache) {
        if (demo) {
            return new DataProviderImplementation(true, api, demoCache);
        } else {
            return new DataProviderImplementation(false, api, realCache);
        }
    }
}
