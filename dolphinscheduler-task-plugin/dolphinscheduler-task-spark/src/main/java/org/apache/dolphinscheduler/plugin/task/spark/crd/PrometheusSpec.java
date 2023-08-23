package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrometheusSpec {

  /**
   * JmxExporterJar is the path to the Prometheus JMX exporter jar in the container.
   */
  String jmxExporterJar;


  /**
   * (Optional) Port is the port of the HTTP server run by the Prometheus JMX exporter. If not
   * specified, 8090 will be used as the default.
   */
  int port;

  /**
   * (Optional) PortName is the port name of prometheus JMX exporter port. If not specified,
   * jmx-exporter will be used as the default.
   */
  String portName;

  /**
   * (Optional) ConfigFile is the path to the custom Prometheus configuration file provided in the
   * Spark image. ConfigFile takes precedence over Configuration, which is shown below.
   */
  String configFile;

  /**
   * (Optional) Configuration is the content of the Prometheus configuration needed by the
   * Prometheus JMX exporter. If not specified, the content in spark-docker/conf/prometheus.yaml
   * will be used. Configuration has no effect if ConfigFile is set.
   */
  String configuration;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getJmxExporterJar() {
    return jmxExporterJar;
  }

  public void setJmxExporterJar(String jmxExporterJar) {
    this.jmxExporterJar = jmxExporterJar;
  }

  public String getConfigFile() {
    return configFile;
  }

  public void setConfigFile(String configFile) {
    this.configFile = configFile;
  }

  @Override
  public String toString() {
    return "PrometheusSpec{" +
        "jmxExporterJar='" + jmxExporterJar + '\'' +
        ", port=" + port +
        ", portName='" + portName + '\'' +
        ", configFile='" + configFile + '\'' +
        ", configuration='" + configuration + '\'' +
        '}';
  }
}
