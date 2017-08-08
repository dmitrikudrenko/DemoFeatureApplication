package io.github.dmitrikudrenko.demofeature.injection;


import dagger.Module;
import dagger.Provides;
import io.github.dmitrikudrenko.demofeature.cache.Cache;
import io.github.dmitrikudrenko.demofeature.cache.CacheImplementation;
import io.github.dmitrikudrenko.demofeature.cache.DemoCacheImplementation;
import io.github.dmitrikudrenko.demofeature.data.DataProvider;
import io.github.dmitrikudrenko.demofeature.data.DataProviderImplementation;
import io.github.dmitrikudrenko.demofeature.data.refreshstrategy.DataRefreshStrategy;
import io.github.dmitrikudrenko.demofeature.data.refreshstrategy.DemoRefreshStrategyImplementation;
import io.github.dmitrikudrenko.demofeature.data.refreshstrategy.RealRefreshStrategyImplementation;
import io.github.dmitrikudrenko.demofeature.network.Api;
import io.github.dmitrikudrenko.demofeature.network.ApiImplementation;

import javax.inject.Singleton;

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
    @RealCache
    @Singleton
    public Cache provideRealCache() {
        return new CacheImplementation();
    }

    @Provides
    @DemoCache
    @Singleton
    public Cache provideDemoCache() {
        return new DemoCacheImplementation();
    }

    @Provides
    @RealRefreshStrategy
    @Singleton
    public DataRefreshStrategy provideRealDataRefreshStrategy() {
        return new RealRefreshStrategyImplementation();
    }

    @Provides
    @DemoRefreshStrategy
    @Singleton
    public DataRefreshStrategy provideDemoDataRefreshStrategy() {
        return new DemoRefreshStrategyImplementation();
    }

    @Provides
    public DataProvider provideDataProvider(
            Api api,
            @RealCache Cache realCache, @DemoCache Cache demoCache,
            @RealRefreshStrategy DataRefreshStrategy realDataRefreshStrategy,
            @DemoRefreshStrategy DataRefreshStrategy demoDataRefreshStrategy) {
        if (demo) {
            return new DataProviderImplementation(demoDataRefreshStrategy, api, demoCache);
        } else {
            return new DataProviderImplementation(realDataRefreshStrategy, api, realCache);
        }
    }
}
