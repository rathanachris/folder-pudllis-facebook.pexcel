// 4) Process events -> call external APIs
PCollection<ProcessedResult> results = events
    .apply("BatchOrProcess", ParDo.of(new CallExternalApisFn()))
    .setCoder(...) ;
