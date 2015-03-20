name := """uisge"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "com.wordnik" %% "swagger-play2" % "1.3.12" exclude ("org.reflections", "reflections"),
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "net.logstash.logback" % "logstash-logback-encoder" % "4.2",
  "nl.grons" %% "metrics-scala" % "3.3.0_a2.3",
  "com.sksamuel.elastic4s" %% "elastic4s" % "1.4.13",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "com.kenshoo" %% "metrics-play" % "2.3.0_0.1.8"
)
