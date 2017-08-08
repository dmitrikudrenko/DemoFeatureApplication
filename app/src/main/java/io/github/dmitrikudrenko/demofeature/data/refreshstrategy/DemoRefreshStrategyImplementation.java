package io.github.dmitrikudrenko.demofeature.data.refreshstrategy;

public class DemoRefreshStrategyImplementation implements DataRefreshStrategy {
    @Override
    public boolean updateData() {
        return false;
    }
}
