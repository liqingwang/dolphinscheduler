package org.apache.dolphinscheduler.plugin.task.spark.crd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.fabric8.kubernetes.api.model.Volume;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SparkApplicationSpec {

  /**
   * Type tells the type of the Spark application. can be
   * <p>
   * "Java" "Python" "R" "Scala"
   */
  String type;

  /**
   * SparkVersion is the version of Spark the application uses.
   */
  String sparkVersion;

  /**
   * Mode is the deployment mode of the Spark application. can be "client" "cluster"
   * "in-cluster-client"
   */
  String mode;


  /**
   * String	(Optional) ProxyUser specifies the user to impersonate when submitting the application.
   * It maps to the command-line flag “–proxy-user” in spark-submit.
   */
  String proxyUser;

  /**
   * (Optional) Image is the container image for the driver, executor, and init-container. Any
   * custom container images for the driver, executor, or init-container takes precedence over
   * this.
   */
  String image;

  /**
   * (Optional) ImagePullPolicy is the image pull policy for the driver, executor, and
   * init-container.
   */
  String imagePullPolicy;

  /**
   * (Optional) ImagePullSecrets is the list of image-pull secrets.
   */
  List<String> imagePullSecrets;

  /**
   * (Optional) MainClass is the fully-qualified main class of the Spark application. This only
   * applies to Java/Scala Spark applications.
   */
  String mainClass;

  /**
   * (Optional) MainFile is the path to a bundled JAR, Python, or R file of the application.
   */
  String mainApplicationFile;

  /**
   * (Optional) Arguments is a list of arguments to be passed to the application.
   */
  List<String> arguments;

  /**
   * (Optional) SparkConf carries user-specified Spark configuration properties as they would use
   * the “–conf” option in spark-submit.
   */
  Map<String, String> sparkConf;

  /**
   * (Optional) HadoopConf carries user-specified Hadoop configuration properties as they would use
   * the the “–conf” option in spark-submit. The SparkApplication controller automatically adds
   * prefix “spark.hadoop.” to Hadoop configuration properties.
   */
  Map<String, String> hadoopConf;

  /**
   * (Optional) SparkConfigMap carries the name of the ConfigMap containing Spark configuration
   * files such as log4j.properties. The controller will add environment variable SPARK_CONF_DIR to
   * the path where the ConfigMap is mounted to.
   */
  String sparkConfigMap;

  /**
   * (Optional) HadoopConfigMap carries the name of the ConfigMap containing Hadoop configuration
   * files such as core-site.xml. The controller will add environment variable HADOOP_CONF_DIR to
   * the path where the ConfigMap is mounted to.
   */
  String hadoopConfigMap;

  /**
   * (Optional) Volumes is the list of Kubernetes volumes that can be mounted by the driver and/or
   * executors.
   */
  List<Volume> volumes;

  /**
   * Driver is the driver specification.
   */
  DriverSpec driver;

  /**
   * Executor is the executor specification.
   */
  ExecutorSpec executor;

  /**
   * (Optional) Deps captures all possible types of dependencies of a Spark application.
   */
  Dependencies deps;

  /**
   * RestartPolicy defines the policy on if and in which conditions the controller should restart an
   * application.
   */
  RestartPolicy restartPolicy;

  /**
   * (Optional) NodeSelector is the Kubernetes node selector to be added to the driver and executor
   * pods. This field is mutually exclusive with nodeSelector at podSpec level (driver or executor).
   * This field will be deprecated in future versions (at SparkApplicationSpec level).
   */
  Map<String, String> nodeSelector;

  /**
   * (Optional) FailureRetries is the number of times to retry a failed application before giving
   * up. This is best effort and actual retry attempts can be >= the value specified.
   */
  Integer failureRetries;

  /**
   * (Optional) RetryInterval is the unit of intervals in seconds between submission retries.
   */
  long retryInterval;

  /**
   * (Optional) This sets the major Python version of the docker image used to run the driver and
   * executor containers. Can either be 2 or 3, default 2.
   */
  String pythonVersion;

  /**
   * (Optional) This sets the Memory Overhead Factor that will allocate memory to non-JVM memory.
   * For JVM-based jobs this value will default to 0.10, for non-JVM jobs 0.40. Value of this field
   * will be overridden by Spec.Driver.MemoryOverhead and Spec.Executor.MemoryOverhead if they are
   * set.
   */
  String memoryOverheadFactor;

  /**
   * (Optional) Monitoring configures how monitoring is handled.
   */
  MonitoringSpec monitoring;

  /**
   * (Optional) BatchScheduler configures which batch scheduler will be used for scheduling
   */
  String batchScheduler;


  /**
   * (Optional) TimeToLiveSeconds defines the Time-To-Live (TTL) duration in seconds for this
   * SparkApplication after its termination. The SparkApplication object will be garbage collected
   * if the current time is more than the TimeToLiveSeconds since its termination.
   */
  long timeToLiveSeconds;

  /**
   * (Optional) BatchSchedulerOptions provides fine-grained control on how to batch scheduling.
   */
  BatchSchedulerConfiguration batchSchedulerOptions;

  /**
   * (Optional) SparkUIOptions allows configuring the Service and the Ingress to expose the sparkUI
   */

  SparkUIConfiguration sparkUIOptions;

  /**
   * (Optional) DynamicAllocation configures dynamic allocation that becomes available for the
   * Kubernetes scheduler backend since Spark 3.0.
   */
  DynamicAllocation dynamicAllocation;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSparkVersion() {
    return sparkVersion;
  }

  public void setSparkVersion(String sparkVersion) {
    this.sparkVersion = sparkVersion;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getProxyUser() {
    return proxyUser;
  }

  public void setProxyUser(String proxyUser) {
    this.proxyUser = proxyUser;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getImagePullPolicy() {
    return imagePullPolicy;
  }

  public void setImagePullPolicy(String imagePullPolicy) {
    this.imagePullPolicy = imagePullPolicy;
  }

  public List<String> getImagePullSecrets() {
    return imagePullSecrets;
  }

  public void setImagePullSecrets(List<String> imagePullSecrets) {
    this.imagePullSecrets = imagePullSecrets;
  }

  public String getMainClass() {
    return mainClass;
  }

  public void setMainClass(String mainClass) {
    this.mainClass = mainClass;
  }

  public String getMainApplicationFile() {
    return mainApplicationFile;
  }

  public void setMainApplicationFile(String mainApplicationFile) {
    this.mainApplicationFile = mainApplicationFile;
  }

  public List<String> getArguments() {
    return arguments;
  }

  public void setArguments(List<String> arguments) {
    this.arguments = arguments;
  }

  public Map<String, String> getSparkConf() {
    return sparkConf;
  }

  public void addSparkConf(Map<String, String> sparkConf) {
    if (this.sparkConf == null) {
      this.sparkConf = sparkConf;
    } else {
      this.sparkConf.putAll(sparkConf);
    }
  }

  public Map<String, String> getHadoopConf() {
    return hadoopConf;
  }

  public void setHadoopConf(Map<String, String> hadoopConf) {
    this.hadoopConf = hadoopConf;
  }

  public String getSparkConfigMap() {
    return sparkConfigMap;
  }

  public void setSparkConfigMap(String sparkConfigMap) {
    this.sparkConfigMap = sparkConfigMap;
  }

  public String getHadoopConfigMap() {
    return hadoopConfigMap;
  }

  public void setHadoopConfigMap(String hadoopConfigMap) {
    this.hadoopConfigMap = hadoopConfigMap;
  }

  public List<Volume> getVolumes() {
    return volumes;
  }

  public void setVolumes(List<Volume> volumes) {
    this.volumes = volumes;
  }

  public DriverSpec getDriver() {
    return driver;
  }

  public void setDriver(DriverSpec driver) {
    this.driver = driver;
  }

  public ExecutorSpec getExecutor() {
    return executor;
  }

  public void setExecutor(ExecutorSpec executor) {
    this.executor = executor;
  }

  public Dependencies getDeps() {
    return deps;
  }

  public void setDeps(Dependencies deps) {
    this.deps = deps;
  }

  public RestartPolicy getRestartPolicy() {
    return restartPolicy;
  }

  public void setRestartPolicy(RestartPolicy restartPolicy) {
    this.restartPolicy = restartPolicy;
  }

  public Map<String, String> getNodeSelector() {
    return nodeSelector;
  }

  public void setNodeSelector(Map<String, String> nodeSelector) {
    this.nodeSelector = nodeSelector;
  }

  public Integer getFailureRetries() {
    return failureRetries;
  }

  public void setFailureRetries(Integer failureRetries) {
    this.failureRetries = failureRetries;
  }

  public long getRetryInterval() {
    return retryInterval;
  }

  public void setRetryInterval(long retryInterval) {
    this.retryInterval = retryInterval;
  }

  public String getPythonVersion() {
    return pythonVersion;
  }

  public void setPythonVersion(String pythonVersion) {
    this.pythonVersion = pythonVersion;
  }

  public String getMemoryOverheadFactor() {
    return memoryOverheadFactor;
  }

  public void setMemoryOverheadFactor(String memoryOverheadFactor) {
    this.memoryOverheadFactor = memoryOverheadFactor;
  }

  public MonitoringSpec getMonitoring() {
    return monitoring;
  }

  public void setMonitoring(MonitoringSpec monitoring) {
    this.monitoring = monitoring;
  }

  public String getBatchScheduler() {
    return batchScheduler;
  }

  public void setBatchScheduler(String batchScheduler) {
    this.batchScheduler = batchScheduler;
  }

  public long getTimeToLiveSeconds() {
    return timeToLiveSeconds;
  }

  public void setTimeToLiveSeconds(long timeToLiveSeconds) {
    this.timeToLiveSeconds = timeToLiveSeconds;
  }

  public BatchSchedulerConfiguration getBatchSchedulerOptions() {
    return batchSchedulerOptions;
  }

  public void setBatchSchedulerOptions(
      BatchSchedulerConfiguration batchSchedulerOptions) {
    this.batchSchedulerOptions = batchSchedulerOptions;
  }

  public SparkUIConfiguration getSparkUIOptions() {
    return sparkUIOptions;
  }

  public void setSparkUIOptions(
      SparkUIConfiguration sparkUIOptions) {
    this.sparkUIOptions = sparkUIOptions;
  }

  public DynamicAllocation getDynamicAllocation() {
    return dynamicAllocation;
  }

  public void setDynamicAllocation(
      DynamicAllocation dynamicAllocation) {
    this.dynamicAllocation = dynamicAllocation;
  }

  public void addConfToSparkConf(String name, String value) {
    if (this.sparkConf == null) {
      this.sparkConf = new HashMap<>();
    }
    sparkConf.put(name, value);
  }

  @Override
  public String toString() {
    return "SparkApplicationSpec{" +
        "type='" + type + '\'' +
        ", sparkVersion='" + sparkVersion + '\'' +
        ", mode='" + mode + '\'' +
        ", proxyUser='" + proxyUser + '\'' +
        ", image='" + image + '\'' +
        ", imagePullPolicy='" + imagePullPolicy + '\'' +
        ", imagePullSecrets=" + imagePullSecrets +
        ", mainClass='" + mainClass + '\'' +
        ", mainApplicationFile='" + mainApplicationFile + '\'' +
        ", arguments=" + arguments +
        ", sparkConf=" + sparkConf +
        ", hadoopConf=" + hadoopConf +
        ", sparkConfigMap='" + sparkConfigMap + '\'' +
        ", hadoopConfigMap='" + hadoopConfigMap + '\'' +
        ", volumes=" + volumes +
        ", driver=" + driver +
        ", executor=" + executor +
        ", deps=" + deps +
        ", restartPolicy=" + restartPolicy +
        ", nodeSelector=" + nodeSelector +
        ", failureRetries=" + failureRetries +
        ", retryInterval=" + retryInterval +
        ", pythonVersion='" + pythonVersion + '\'' +
        ", memoryOverheadFactor='" + memoryOverheadFactor + '\'' +
        ", monitoring=" + monitoring +
        ", batchScheduler='" + batchScheduler + '\'' +
        ", timeToLiveSeconds=" + timeToLiveSeconds +
        ", batchSchedulerOptions=" + batchSchedulerOptions +
        ", sparkUIOptions=" + sparkUIOptions +
        ", dynamicAllocation=" + dynamicAllocation +
        '}';
  }
}
