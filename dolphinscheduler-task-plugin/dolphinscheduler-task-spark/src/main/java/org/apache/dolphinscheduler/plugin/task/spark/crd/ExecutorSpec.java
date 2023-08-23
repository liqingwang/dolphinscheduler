package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.fabric8.kubernetes.api.model.Affinity;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.EnvFromSource;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.HostAlias;
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
public class ExecutorSpec {

  /**
   * (Optional) Instances is the number of executor instances.
   */
  int instances;

  /**
   * (Optional) CoreRequest is the physical CPU core request for the executors. Maps to
   * spark.kubernetes.executor.request.cores that is available since Spark 2.4.
   */
  String coreRequest;

  /**
   * (Optional) JavaOptions is a String of extra JVM options to pass to the executors. For instance,
   * GC settings or other logging.
   */
  String javaOptions;

  /**
   * (Optional) DeleteOnTermination specify whether executor pods should be deleted in case of
   * failure or normal termination. Maps to spark.kubernetes.executor.deleteOnTermination that is
   * available since Spark 3.0.
   */
  boolean deleteOnTermination;

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
   * (Optional) This sets the Memory Overhead Factor that will allocate memory to non-JVM memory.
   * For JVM-based jobs this value will default to 0.10, for non-JVM jobs 0.40. Value of this field
   * will be overridden by Spec.Driver.MemoryOverhead and Spec.Executor.MemoryOverhead if they are
   * set.
   */
  String memoryOverheadFactor = "0.20";

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

  public ExecutorSpec() {
  }

  public void addEnv(EnvVar var) {
    if (this.env == null) {
      this.env = new ArrayList<>();
    }
    this.env.add(var);
  }

}
