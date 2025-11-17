PCollectionView<List<Trigger>> triggerView = 
    triggers.apply(View.asList());
