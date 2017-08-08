package io.github.dmitrikudrenko.demofeature.data.refreshstrategy;

public class RealRefreshStrategyImplementation implements DataRefreshStrategy {
    @Override
    public boolean updateData() {
        return true;
    }
}
