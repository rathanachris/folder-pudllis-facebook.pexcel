// 5) Ensure update triggers waits for events to finish
PCollection<Trigger> triggersToUpdate = triggers.apply("WaitForEvents", Wait.on(results));

// 6) Update status and write back to Bigtable
triggersToUpdate
  .apply("FinalizeStatus", ParDo.of(new UpdateTriggerStatusFn(BNCConstant.COMPLETE_STATUS)))
  .apply("ToMutations", ParDo.of(new TriggerPojoToMutationFn()))
  .apply("WriteTriggersBack", BigtableIO.write()
      .withProjectId(projectId)
      .withInstanceId(instanceId)
      .withTableId("trgr_sta"));
