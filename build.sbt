ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.11.11"

val Tookitaki =
  "Tookitaki".at(
    "http://tookitaki-artifacts.tookitaki.com/artifactory/maven-local"
  ).withAllowInsecureProtocol(true)

val TookitakiRelease =
  "TookitakiRelease".at(
    "http://tookitaki-artifacts.tookitaki.com/artifactory/tookitaki-releases"
  ).withAllowInsecureProtocol(true)

val CdhResolver =
  "Cloudera Maven Repository".at("https://repository.cloudera.com/artifactory/cloudera-repos")

val RedHatGA              = "Redhat GA".at("https://maven.repository.redhat.com/ga/")
val TypesafeReleases      = Classpaths.typesafeReleases

val allResolvers = Seq(Tookitaki,
  TookitakiRelease,
  TypesafeReleases,
  RedHatGA,
  CdhResolver)

val phoenixVersion = "5.1.0-HBase-2.0-SNAPSHOT-client"
val hadoopCdh601ClientVersion = "3.0.0-cdh6.0.1"
val hbaseCdh601ClientVersion  = "2.0.0-cdh6.0.1"

lazy val root = (project in file("."))
  .settings(
    name := "5.1.0-HBase-2.0-SNAPSHOT-client",
    credentials += Credentials(Path.userHome / ".ivy2" / "credentials"),
    resolvers ++= allResolvers,
    libraryDependencies ++= Seq(
      "org.scalikejdbc"       %% "scalikejdbc"        % "3.4.0",
      "com.h2database"         % "h2"                 % "1.4.200",
      "ch.qos.logback"         % "logback-classic"    % "1.2.3",
      "org.apache.phoenix"     % "phoenix"            % phoenixVersion,
      "org.apache.hbase"       % "hbase-common"       % hbaseCdh601ClientVersion,
      "org.apache.hbase"       % "hbase-protocol"     %	hbaseCdh601ClientVersion,
      "org.apache.hadoop"      % "hadoop-client"      % hadoopCdh601ClientVersion,
      "org.apache.hadoop"	     % "hadoop-common"	    % hadoopCdh601ClientVersion,
      "org.apache.hadoop"	     % "hadoop-mapreduce-client-common" %	hadoopCdh601ClientVersion,
      "com.fasterxml.woodstox" % "woodstox-core"      % "5.0.3"
    ),
    excludeDependencies ++= Seq(
      ExclusionRule("org.slf4j", "slf4j-log4j12"),
      ExclusionRule("org.apache", "hbase.thirdparty"),
      ExclusionRule("org.apache.hbase", "thirdparty"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-thirdparty"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-shaded-netty"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-shaded-miscellaneous"),
      ExclusionRule("org.apache.hbase.thirdparty", "hbase-shaded-protobuf"),
      ExclusionRule("org.apache.hbase", "hbase-client"),
      ExclusionRule("org.apache.hbase", "hbase-common"),
      ExclusionRule("org.apache.commons" ,"commons-math3"),
      ExclusionRule("org.apache.yetus", "audience-tools"),
      ExclusionRule("org.apache.yetus", "audience-annotations")
      )
  )

