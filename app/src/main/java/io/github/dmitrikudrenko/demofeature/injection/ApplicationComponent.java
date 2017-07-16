package io.github.dmitrikudrenko.demofeature.injection;

import javax.inject.Singleton;

import dagger.Component;
import io.github.dmitrikudrenko.demofeature.ui.MainActivity;

@Singleton
@Component(modules = DataModule.class)
public interface ApplicationComponent {

    void inject(MainActivity activity);
}
