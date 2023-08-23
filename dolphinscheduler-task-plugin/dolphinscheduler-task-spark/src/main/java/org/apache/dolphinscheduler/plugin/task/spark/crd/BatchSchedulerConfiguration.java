package org.apache.dolphinscheduler.plugin.task.spark.crd;

import io.fabric8.kubernetes.api.model.DefaultKubernetesResourceList;

public class BatchSchedulerConfiguration {

  /**
   * (Optional) Queue stands for the resource queue which the application belongs to, it’s being
   * used in Volcano batch scheduler.
   */
  String queue;

  /**
   * (Optional) PriorityClassName stands for the name of k8s PriorityClass resource, it’s being used
   * in Volcano batch scheduler.
   */
  String priorityClassName;

  /**
   * (Optional) Resources stands for the resource list custom request for. Usually it is used to
   * define the lower-bound limit. If specified, volcano scheduler will consider it as the resources
   * requested.
   */
  DefaultKubernetesResourceList resources;

}
