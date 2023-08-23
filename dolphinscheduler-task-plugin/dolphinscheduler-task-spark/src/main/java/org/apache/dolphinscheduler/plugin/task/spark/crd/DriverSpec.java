package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.fabric8.kubernetes.api.model.Affinity;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.EnvFromSource;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.HostAlias;
import io.fabric8.kubernetes.api.model.Lifecycle;
import io.fabric8.kubernetes.api.model.PodDNSConfig;
import io.fabric8.kubernetes.api.model.Toleration;
import io.fabric8.kubernetes.api.model.VolumeMount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class DriverSpec {


  /**
   * (Optional) PodName is the name of the driver pod that the user creates. This is used for the
   * in-cluster client mode in which the user creates a client pod where the driver of the user
   * application runs. It’s an error to set this field if Mode is not in-cluster-client.
   */
  String podName;

  /**
   * (Optional) CoreRequest is the physical CPU core request for the driver. Maps to
   * spark.kubernetes.driver.request.cores that is available since Spark 3.0.
   */
  String coreRequest;

  /**
   * (Optional) JavaOptions is a String of extra JVM options to pass to the driver. For instance, GC
   * settings or other logging.
   */
  String javaOptions;

  /**
   * (Optional) Lifecycle for running preStop or postStart commands
   */
  Lifecycle lifecycle;

  /**
   * (Optional) KubernetesMaster is the URL of the Kubernetes master used by the driver to manage
   * executor pods and other Kubernetes resources. Default to https://kubernetes.default.svc.
   */
  String kubernetesMaster;

  /**
   * (Optional) ServiceAnnotations defines the annotations to be added to the Kubernetes headless
   * service used by executors to connect to the driver.
   */
  Map<String, String> serviceAnnotations;

  /**
   * (Optional) Ports settings for the pods, following the Kubernetes specifications.
   */
  List<Port> ports;


  /**
   * (Optional) Cores maps to spark.driver.cores or spark.executor.cores for the driver and
   * executors, respectively.
   */
  Integer cores;

  /**
   * CoreLimit specifies a hard limit on CPU cores for the pod. Optional
   */
  String coreLimit;

  /**
   * (Optional) Memory is the amount of memory to request for the pod.
   */
  String memory;


  /**
   * (Optional) MemoryOverhead is the amount of off-heap memory to allocate in cluster mode, in MiB
   * unless otherwise specified.
   */
  String memoryOverhead;


  /**
   * (Optional) Image is the container image to use. Overrides Spec.Image if set.
   */
  String image;


  /**
   * (Optional) ConfigMaps carries information of other ConfigMaps to add to the pod.
   */
  List<NamePath> configMaps;


  /**
   * (Optional) Env carries the environment variables to add to the pod.
   */
  List<EnvVar> env;

  /**
   * (Optional) EnvVars carries the environment variables to add to the pod. Deprecated. Consider
   * using env instead.
   */
  Map<String, String> envVars;

  /**
   * (Optional) EnvFrom is a list of sources to populate environment variables in the container.
   */
  List<EnvFromSource> envFrom;


  /**
   * (Optional) Labels are the Kubernetes labels to be added to the pod.
   */
  Map<String, String> labels;

  /**
   *
   */
  Map<String, String> annotations;

  /**
   * (Optional) VolumeMounts specifies the volumes listed in “.spec.volumes” to mount into the main
   * container’s filesystem.
   */
  List<VolumeMount> volumeMounts;

  /**
   * (Optional) Affinity specifies the affinity/anti-affinity settings for the pod.
   */
  Affinity affinity;

  /**
   * (Optional) Tolerations specifies the tolerations listed in “.spec.tolerations” to be applied to
   * the pod.
   */
  List<Toleration> tolerations;

  /**
   * (Optional) PodSecurityContext specifies the PodSecurityContext to apply.
   */
  io.fabric8.kubernetes.api.model.PodSecurityContext podSecurityContext;

  /**
   * (Optional) SecurityContext specifies the container’s SecurityContext to apply.
   */
  io.fabric8.kubernetes.api.model.SecurityContext securityContext;

  /**
   * (Optional) SchedulerName specifies the scheduler that will be used for scheduling
   */
  String schedulerName;

  /**
   * (Optional) Sidecars is a list of sidecar containers that run along side the main Spark
   * container.
   */
  List<Container> sidecars;

  /**
   * (Optional) InitContainers is a list of init-containers that run to completion before the main
   * Spark container.
   */
  List<Container> initContainers;

  /**
   * (Optional) HostNetwork indicates whether to request host networking for the pod or not.
   */
  boolean hostNetwork;

  /**
   * (Optional) NodeSelector is the Kubernetes node selector to be added to the driver and executor
   * pods. This field is mutually exclusive with nodeSelector at SparkApplication level (which will
   * be deprecated).
   */
  Map<String, String> nodeSelector;

  /**
   * (Optional) DnsConfig dns settings for the pod, following the Kubernetes specifications.
   */
  PodDNSConfig dnsConfig;

  /**
   * (Optional) Termination grace period seconds for the pod
   */
  long terminationGracePeriodSeconds;

  /**
   * (Optional) ServiceAccount is the name of the custom Kubernetes service account used by the
   * pod.
   */
  String serviceAccount;

  /**
   * (Optional) HostAliases settings for the pod, following the Kubernetes specifications.
   */
  List<HostAlias> hostAliases;
  /**
   * (Optional) ShareProcessNamespace settings for the pod, following the Kubernetes
   * specifications.
   */
  boolean shareProcessNamespace;

  public DriverSpec() {
  }

  public void addEnv(EnvVar var) {
    if (this.env == null) {
      this.env = new ArrayList<>();
    }
    this.env.add(var);
  }

}
