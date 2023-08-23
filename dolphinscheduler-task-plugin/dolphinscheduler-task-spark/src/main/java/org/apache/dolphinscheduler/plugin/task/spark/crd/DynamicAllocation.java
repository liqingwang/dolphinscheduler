package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicAllocation {

  /**
   * bool Enabled controls whether dynamic allocation is enabled or not.
   */
  boolean enabled;

  /**
   * int32 (Optional) long InitialExecutors is the initial number of executors to request. If
   * .spec.executor.instances is also set, the initial number of executors is set to the bigger of
   * that and this option.
   */
  int initialExecutors;

  /**
   * int32 (Optional) MinExecutors is the lower bound for the number of executors if dynamic
   * allocation is enabled.
   */
  int minExecutors;

  /**
   * int32 (Optional) MaxExecutors is the upper bound for the number of executors if dynamic
   * allocation is enabled.
   */
  int maxExecutors;

  /**
   * int64 (Optional) ShuffleTrackingTimeout controls the timeout in milliseconds for executors that
   * are holding shuffle data if shuffle tracking is enabled (true by default if dynamic allocation
   * is enabled).
   */
  long shuffleTrackingTimeout;

  public DynamicAllocation(int maxExecutors) {
    this.maxExecutors = maxExecutors;
    this.enabled = true;
    this.initialExecutors = maxExecutors / 4;
    this.minExecutors = 1;
    this.shuffleTrackingTimeout = 300000;
  }

  public DynamicAllocation() {
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public int getInitialExecutors() {
    return initialExecutors;
  }

  public void setInitialExecutors(int initialExecutors) {
    this.initialExecutors = initialExecutors;
  }

  public int getMinExecutors() {
    return minExecutors;
  }

  public void setMinExecutors(int minExecutors) {
    this.minExecutors = minExecutors;
  }

  public int getMaxExecutors() {
    return maxExecutors;
  }

  public void setMaxExecutors(int maxExecutors) {
    this.maxExecutors = maxExecutors;
  }

  public long getShuffleTrackingTimeout() {
    return shuffleTrackingTimeout;
  }

  public void setShuffleTrackingTimeout(long shuffleTrackingTimeout) {
    this.shuffleTrackingTimeout = shuffleTrackingTimeout;
  }

}
