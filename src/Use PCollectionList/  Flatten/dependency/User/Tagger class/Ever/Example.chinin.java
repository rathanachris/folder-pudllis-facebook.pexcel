triggers
  .apply("update status",
      ParDo.of(new TriggerStatus.updateTriggerStatus(BNCConstant.COMPLETE_STATUS)))
  .apply(ParDo.of(new TriggerStatus.pojoToMutation()))
  .apply(BigtableHelper.writeToBigtable("trgr_sta"));
