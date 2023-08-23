package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonitoringSpec {

  /**
   * ExposeDriverMetrics specifies whether to expose metrics on the driver.
   */
  boolean exposeDriverMetrics;

  /**
   * ExposeExecutorMetrics specifies whether to expose metrics on the executors.
   */
  boolean exposeExecutorMetrics;

  /**
   * (Optional) MetricsProperties is the content of a custom metrics.properties for configuring the
   * Spark metric system. If not specified, the content in spark-docker/conf/metrics.properties will
   * be used.
   */
  String metricsProperties;

  /**
   * (Optional) MetricsPropertiesFile is the container local path of file metrics.properties for
   * configuring the Spark metric system. If not specified, value
   * /etc/metrics/conf/metrics.properties will be used.
   */
  String metricsPropertiesFile;

  /**
   * (Optional) Prometheus is for configuring the Prometheus JMX exporter.
   */
  PrometheusSpec prometheus;

  public boolean isExposeDriverMetrics() {
    return exposeDriverMetrics;
  }

  public void setExposeDriverMetrics(boolean exposeDriverMetrics) {
    this.exposeDriverMetrics = exposeDriverMetrics;
  }

  public boolean isExposeExecutorMetrics() {
    return exposeExecutorMetrics;
  }

  public void setExposeExecutorMetrics(boolean exposeExecutorMetrics) {
    this.exposeExecutorMetrics = exposeExecutorMetrics;
  }

  public String getMetricsProperties() {
    return metricsProperties;
  }

  public void setMetricsProperties(String metricsProperties) {
    this.metricsProperties = metricsProperties;
  }

  public String getMetricsPropertiesFile() {
    return metricsPropertiesFile;
  }

  public void setMetricsPropertiesFile(String metricsPropertiesFile) {
    this.metricsPropertiesFile = metricsPropertiesFile;
  }

  public PrometheusSpec getPrometheus() {
    return prometheus;
  }

  public void setPrometheus(PrometheusSpec prometheus) {
    this.prometheus = prometheus;
  }

  @Override
  public String toString() {
    return "MonitoringSpec{" +
        "exposeDriverMetrics=" + exposeDriverMetrics +
        ", exposeExecutorMetrics=" + exposeExecutorMetrics +
        ", metricsProperties='" + metricsProperties + '\'' +
        ", metricsPropertiesFile='" + metricsPropertiesFile + '\'' +
        ", prometheus=" + prometheus +
        '}';
  }
}
