package org.apache.dolphinscheduler.plugin.task.spark.crd;

import java.util.List;

public class Dependencies {

  /**
   * (Optional) Jars is a list of JAR files the Spark application depends on.
   */
  List<String> jars;

  /**
   * (Optional) Files is a list of files the Spark application depends on.
   */
  List<String> files;

  /**
   * (Optional) PyFiles is a list of Python files the Spark application depends on.
   */
  List<String> pyFiles;

  /**
   * (Optional) Packages is a list of maven coordinates of jars to include on the driver and
   * executor classpaths. This will search the local maven repo, then maven central and any
   * additional remote repositories given by the “repositories” option. Each package should be of
   * the form “groupId:artifactId:version”.
   */
  List<String> packages;

  /**
   * (Optional) ExcludePackages is a list of “groupId:artifactId”, to exclude while resolving the
   * dependencies provided in Packages to avoid dependency conflicts.
   */
  List<String> excludePackages;

  /**
   * (Optional) Repositories is a list of additional remote repositories to search for the maven
   * coordinate given with the “packages” option.
   */
  List<String> repositories;
}
