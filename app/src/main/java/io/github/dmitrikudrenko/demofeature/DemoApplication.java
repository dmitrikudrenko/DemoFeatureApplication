package io.github.dmitrikudrenko.demofeature;

import android.app.Application;

import io.github.dmitrikudrenko.demofeature.injection.ApplicationComponent;
import io.github.dmitrikudrenko.demofeature.injection.DaggerApplicationComponent;
import io.github.dmitrikudrenko.demofeature.injection.DataModule;


public class DemoApplication extends Application {
    public static ApplicationComponent injectionComponent;

    private DataModule dataModule = new DataModule();

    @Override
    public void onCreate() {
        super.onCreate();
        injectionComponent = DaggerApplicationComponent.builder()
                .dataModule(dataModule)
                .build();
    }

    public void setDemo(boolean demo) {
        dataModule.setDemo(demo);
    }
}
