package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverInfo {

  String webUIServiceName;

  /**
   * UI Details for the UI created via ClusterIP service accessible from within the cluster.
   */
  int webUIPort;


  String webUIAddress;

  /**
   * Ingress Details if an ingress for the UI was created.
   */
  String webUIIngressName;


  String webUIIngressAddress;

  String podName;

  @Override
  public String toString() {
    return "DriverInfo{" +
        "webUIServiceName='" + webUIServiceName + '\'' +
        ", webUIPort=" + webUIPort +
        ", webUIAddress='" + webUIAddress + '\'' +
        ", webUIIngressName='" + webUIIngressName + '\'' +
        ", webUIIngressAddress='" + webUIIngressAddress + '\'' +
        ", podName='" + podName + '\'' +
        '}';
  }

  public String getWebUIServiceName() {
    return webUIServiceName;
  }

  public void setWebUIServiceName(String webUIServiceName) {
    this.webUIServiceName = webUIServiceName;
  }

  public int getWebUIPort() {
    return webUIPort;
  }

  public void setWebUIPort(int webUIPort) {
    this.webUIPort = webUIPort;
  }

  public String getWebUIAddress() {
    return webUIAddress;
  }

  public void setWebUIAddress(String webUIAddress) {
    this.webUIAddress = webUIAddress;
  }

  public String getWebUIIngressName() {
    return webUIIngressName;
  }

  public void setWebUIIngressName(String webUIIngressName) {
    this.webUIIngressName = webUIIngressName;
  }

  public String getWebUIIngressAddress() {
    return webUIIngressAddress;
  }

  public void setWebUIIngressAddress(String webUIIngressAddress) {
    this.webUIIngressAddress = webUIIngressAddress;
  }

  public String getPodName() {
    return podName;
  }

  public void setPodName(String podName) {
    this.podName = podName;
  }
}
