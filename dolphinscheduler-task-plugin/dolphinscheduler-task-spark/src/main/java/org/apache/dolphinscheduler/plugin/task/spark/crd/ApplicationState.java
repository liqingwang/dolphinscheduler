package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationState {

  ApplicationStateType state;

  String errorMessage;


  public ApplicationStateType getState() {
    return state;
  }

  public void setState(ApplicationStateType state) {
    this.state = state;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  @Override
  public String toString() {
    return "ApplicationState{" +
        "state=" + state +
        ", errorMessage='" + errorMessage + '\'' +
        '}';
  }
}
