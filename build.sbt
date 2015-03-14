name := """uisge"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.2",
  "nl.grons" %% "metrics-scala" % "3.3.0_a2.3",
  "com.sksamuel.elastic4s" %% "elastic4s" % "1.4.13",
  "com.typesafe.play" %% "play-slick" % "0.8.1"
)
