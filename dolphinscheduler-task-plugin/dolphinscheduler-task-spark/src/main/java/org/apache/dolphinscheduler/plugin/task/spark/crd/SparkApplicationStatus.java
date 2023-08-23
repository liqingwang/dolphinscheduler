package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.util.Map;

@JsonDeserialize()
@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkApplicationStatus implements KubernetesResource {


  /**
   * SparkApplicationID is set by the spark-distribution(via spark.app.id config) on the driver and
   * executor pods
   */
  String sparkApplicationId;


  /**
   * SubmissionID is a unique ID of the current submission of the application.
   */
  String submissionID;


  /**
   * LastSubmissionAttemptTime is the time for the last application submission attempt.
   */
  String lastSubmissionAttemptTime;

  /**
   * CompletionTime is the time when the application runs to completion if it does.
   */
  String terminationTime;


  /**
   * DriverInfo has information about the driver.
   */
  DriverInfo driverInfo;

  /**
   * AppState tells the overall application state.
   */
  ApplicationState applicationState;

  /**
   * records the state of executors by executor Pod names.
   */
  Map<String, String> executorState;

  /**
   * ExecutionAttempts is the total number of attempts to run a submitted application to completion.
   * Incremented upon each attempted run of the application and reset upon invalidation.
   */
  int executionAttempts;

  /**
   * SubmissionAttempts is the total number of attempts to submit an application to run. Incremented
   * upon each attempted submission of the application and reset upon invalidation and rerun.
   */
  int submissionAttempts;

  @Override
  public String toString() {
    return "SparkApplicationStatus{" +
        "sparkApplicationId='" + sparkApplicationId + '\'' +
        ", submissionID='" + submissionID + '\'' +
        ", lastSubmissionAttemptTime='" + lastSubmissionAttemptTime + '\'' +
        ", terminationTime='" + terminationTime + '\'' +
        ", driverInfo=" + driverInfo +
        ", applicationState=" + applicationState +
        ", executorState='" + executorState + '\'' +
        ", executionAttempts=" + executionAttempts +
        ", submissionAttempts=" + submissionAttempts +
        '}';
  }

  public String getSparkApplicationId() {
    return sparkApplicationId;
  }

  public void setSparkApplicationId(String sparkApplicationId) {
    this.sparkApplicationId = sparkApplicationId;
  }

  public String getSubmissionID() {
    return submissionID;
  }

  public void setSubmissionID(String submissionID) {
    this.submissionID = submissionID;
  }

  public String getLastSubmissionAttemptTime() {
    return lastSubmissionAttemptTime;
  }

  public void setLastSubmissionAttemptTime(String lastSubmissionAttemptTime) {
    this.lastSubmissionAttemptTime = lastSubmissionAttemptTime;
  }

  public String getTerminationTime() {
    return terminationTime;
  }

  public void setTerminationTime(String terminationTime) {
    this.terminationTime = terminationTime;
  }

  public DriverInfo getDriverInfo() {
    return driverInfo;
  }

  public void setDriverInfo(DriverInfo driverInfo) {
    this.driverInfo = driverInfo;
  }

  public ApplicationState getApplicationState() {
    return applicationState;
  }

  public void setApplicationState(
      ApplicationState applicationState) {
    this.applicationState = applicationState;
  }

  public Map<String, String> getExecutorState() {
    return executorState;
  }

  public void setExecutorState(Map<String, String> executorState) {
    this.executorState = executorState;
  }

  public int getExecutionAttempts() {
    return executionAttempts;
  }

  public void setExecutionAttempts(int executionAttempts) {
    this.executionAttempts = executionAttempts;
  }

  public int getSubmissionAttempts() {
    return submissionAttempts;
  }

  public void setSubmissionAttempts(int submissionAttempts) {
    this.submissionAttempts = submissionAttempts;
  }
}
