package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressTLS;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkUIConfiguration {

  /**
   * (Optional) ServicePort allows configuring the port at service level that might be different
   * from the targetPort. TargetPort should be the same as the one defined in spark.ui.port
   */
  Integer servicePort;

  /**
   * (Optional) ServicePortName allows configuring the name of the service port. This may be useful
   * for sidecar proxies like Envoy injected by Istio which require specific ports names to treat
   * traffic as proper HTTP. Defaults to spark-driver-ui-port.
   */
  String servicePortName;

  /**
   * (Optional) ServiceType allows configuring the type of the service. Defaults to ClusterIP.
   */
  String serviceType;

  /**
   * (Optional) ServiceAnnotations is a map of key,value pairs of annotations that might be added to
   * the service object.
   */
  Map<String, String> serviceAnnotations;

  /**
   * (Optional) IngressAnnotations is a map of key,value pairs of annotations that might be added to
   * the ingress object. i.e. specify nginx as ingress.class
   */
  Map<String, String> ingressAnnotations;

  /**
   * (Optional) TlsHosts is useful If we need to declare SSL certificates to the ingress object
   */
  List<IngressTLS> ingressTLS;

  public Integer getServicePort() {
    return servicePort;
  }

  public void setServicePort(Integer servicePort) {
    this.servicePort = servicePort;
  }

  public String getServicePortName() {
    return servicePortName;
  }

  public void setServicePortName(String servicePortName) {
    this.servicePortName = servicePortName;
  }

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  public Map<String, String> getServiceAnnotations() {
    return serviceAnnotations;
  }

  public void setServiceAnnotations(Map<String, String> serviceAnnotations) {
    this.serviceAnnotations = serviceAnnotations;
  }

  public Map<String, String> getIngressAnnotations() {
    return ingressAnnotations;
  }

  public void setIngressAnnotations(Map<String, String> ingressAnnotations) {
    this.ingressAnnotations = ingressAnnotations;
  }

  public List<IngressTLS> getIngressTLS() {
    return ingressTLS;
  }

  public void setIngressTLS(
      List<IngressTLS> ingressTLS) {
    this.ingressTLS = ingressTLS;
  }
}
