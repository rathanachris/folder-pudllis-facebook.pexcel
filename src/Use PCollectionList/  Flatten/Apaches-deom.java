PCollectionView<List<Trigger>> triggerView = page
    triggers.apply(View.asList());
