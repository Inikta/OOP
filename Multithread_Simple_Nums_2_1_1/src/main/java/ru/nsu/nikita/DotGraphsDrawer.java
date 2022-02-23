package ru.nsu.nikita;

import java.util.List;

public class DotGraphsDrawer {

    private List<Long> sequentialStreamResults;
    private List<Long> parallelStreamsResults;
    private List<Long> threadsResults;
    private List<String> testsNames;

    public DotGraphsDrawer(List<Long> sequentialStreamResults,
                           List<Long> parallelStreamsResults,
                           List<Long> threadsResults,
                           List<String> testsNames) {
        this.sequentialStreamResults = sequentialStreamResults;
        this.parallelStreamsResults = parallelStreamsResults;
        this.threadsResults = threadsResults;
        this.testsNames = testsNames;
    }

    public void drawAll() {

    }

}
