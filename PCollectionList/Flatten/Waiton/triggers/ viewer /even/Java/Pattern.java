// 1) Read triggers
PCollection<Trigger> triggers = pipeline
    .apply("ReadTriggers", BigtableIO.read(...).withTableId("trgr_sta"))
    .apply("RowToTrigger", ParDo.of(new BigTableRowToTriggerFn()))
    .apply("FilterReady", Filter.by(new FilterreadyInProgressTriggerFn()))
    .apply("MarkInProgress", ParDo.of(new MarkInProgressFn())); // optionally write IN_PROGRESS here

// 2) Create side view
PCollectionView<List<Trigger>> triggerView = triggers.apply(View.asList());

// 3) Read events and filter by trigger list (uses side input)
PCollection<Event> events = pipeline
    .apply("ReadEvents", BigtableIO.read(...).withTableId("event"))
    .apply("FilterEventsByTriggers", ParDo.of(new DoFn<Row, Event>() {
        @ProcessElement
        public void processElement(ProcessContext c) {
            List<Trigger> triggersList = c.sideInput(triggerView);
            if (triggersList == null || triggersList.isEmpty()) {
                // nothing to do
                return;
            }
            Row row = c.element();
            // applyFilter using triggersList -> if matched emit Event
            if (matches(row, triggersList)) {
                c.output(convertToEvent(row));
            }
        }
    }).withSideInputs(triggerView))
    .apply("RowToEventPojo", ParDo.of(new RowToEventFn()));
