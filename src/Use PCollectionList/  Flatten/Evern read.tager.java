PCollection<Event> events = pipeline
    .apply(BigtableHelper.getBigtableIORead("event"))
    .apply(
      ParDo.of(new FilterSrcAggrEventRowsFn(triggerView))
           .withSideInputs(triggerView))
    .apply(Event.bigTableRowToPojo());
