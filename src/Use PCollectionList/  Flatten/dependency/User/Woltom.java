PCollection<Void> waitForEvents =
    events.apply(Wait.on(triggers));
