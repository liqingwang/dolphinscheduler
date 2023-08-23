package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestartPolicy {

  /**
   * Type specifies the RestartPolicyType. can be: "Always" "Never" "OnFailure"
   */
  String type;

  /**
   * (Optional) OnSubmissionFailureRetries is the number of times to retry submitting an application
   * before giving up. This is best effort and actual retry attempts can be >= the value specified
   * due to caching. These are required if RestartPolicy is OnFailure.
   */
  Integer onSubmissionFailureRetries;

  /**
   * (Optional) OnFailureRetries the number of times to retry running an application before giving
   * up.
   */
  Integer onFailureRetries;

  /**
   * (Optional) OnSubmissionFailureRetryInterval is the interval in seconds between retries on
   * failed submissions.
   */
  Long onSubmissionFailureRetryInterval;

  /**
   * (Optional) OnFailureRetryInterval is the interval in seconds between retries on failed runs.
   */
  Long onFailureRetryInterval;

  public RestartPolicy() {
  }

  public RestartPolicy(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getOnSubmissionFailureRetries() {
    return onSubmissionFailureRetries;
  }

  public void setOnSubmissionFailureRetries(Integer onSubmissionFailureRetries) {
    this.onSubmissionFailureRetries = onSubmissionFailureRetries;
  }

  public Integer getOnFailureRetries() {
    return onFailureRetries;
  }

  public void setOnFailureRetries(Integer onFailureRetries) {
    this.onFailureRetries = onFailureRetries;
  }

  public Long getOnSubmissionFailureRetryInterval() {
    return onSubmissionFailureRetryInterval;
  }

  public void setOnSubmissionFailureRetryInterval(long onSubmissionFailureRetryInterval) {
    this.onSubmissionFailureRetryInterval = onSubmissionFailureRetryInterval;
  }

  public Long getOnFailureRetryInterval() {
    return onFailureRetryInterval;
  }

  public void setOnFailureRetryInterval(long onFailureRetryInterval) {
    this.onFailureRetryInterval = onFailureRetryInterval;
  }
}
