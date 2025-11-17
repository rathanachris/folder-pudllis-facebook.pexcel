PCollection<Trigger> triggers =
pipeline
  .apply(BigtableHelper.getBigtableIORead("trgr_sta"))
  .apply(TriggerStatus.bigTableRowToPojo())
  .apply(Filter.by(new FilterreadyInProgressTriggerFn()))
  .apply(ParDo.of(
      new TriggerStatus.updateTriggerStatus(Constant.IN_PROGRESS_STATUS)));
