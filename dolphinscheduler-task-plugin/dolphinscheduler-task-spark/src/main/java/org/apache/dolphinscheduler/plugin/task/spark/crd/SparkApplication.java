package org.apache.dolphinscheduler.plugin.task.spark.crd;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;

@Version(SparkApplication.apiVersion)
@Group(SparkApplication.group)
@Kind(SparkApplication.kind)
public class SparkApplication extends
    CustomResource<SparkApplicationSpec, SparkApplicationStatus> implements
    Namespaced {

  public static final String apiVersion = "v1beta2";
  public static final String group = "sparkoperator.k8s.io";
  public static final String kind = "SparkApplication";

  public SparkApplication() {
  }

  @Override
  public String toString() {
    return "SparkApplication{" +
        "spec=" + spec +
        '}';
  }

}
