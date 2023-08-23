package org.apache.dolphinscheduler.plugin.task.spark.crd;

public enum ApplicationStateType {
  COMPLETED,

  FAILED,

  SUBMISSION_FAILED,

  FAILING,

  INVALIDATING,

  PENDING_RERUN,

  RUNNING,

  SUBMITTED,

  SUCCEEDING,

  FINISHED,

  UNKNOWN
}
