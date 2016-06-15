import ByteConversions._

organization := "com.typesafe.zookeeper"
name := "zookeeper"
version := "3.4.8"

licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.8"
libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.18"
libraryDependencies += "log4j" % "log4j" % "1.2.17"

// Bundle configuration

BundleKeys.nrOfCpus := 2.0
BundleKeys.memory := 512.MiB
BundleKeys.diskSpace := 100.MB
BundleKeys.roles := Set("zookeeper")

BundleKeys.endpoints := Map(
  "zookeeper_client" -> Endpoint("tcp", services = Set(uri("tcp://:2181")))
//  "zookeeper_peer" ->  Endpoint("tcp", services = Set(uri("tcp://:2888"))),
//  "zookeeper_election" -> Endpoint("tcp", services = Set(uri("tcp://:3888")))
)

BundleKeys.executableScriptPath in Bundle := (file((normalizedName in Bundle).value) / "bin" / "bootstrap").getPath
BundleKeys.checks := Seq(uri("$ZOOKEEPER_CLIENT_HOST?retry-count=10&retry-delay=3")
)

javaOptions in Bundle := Seq.empty

// Bundle publishing configuration

inConfig(Bundle)(Seq(
  bintrayVcsUrl := Some("https://github.com/typesafehub/conductr-zookeeper"),
  bintrayOrganization := Some("typesafe")
))

lazy val root = project.in(file(".")).enablePlugins(JavaServerAppPackaging)
