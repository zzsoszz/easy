import AssemblyKeys._

name := "easysparkserver" 

organization := "com.sillycat" 

version := "1.0" 

scalaVersion := "2.9.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8") 

resolvers ++= Seq(
	"sonatype releases"  at "https://oss.sonatype.org/content/repositories/releases/",
  	"sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  	"typesafe repo"      at "http://repo.typesafe.com/typesafe/releases/",
  	"spray repo"         at "http://repo.spray.io/",
  	"Spray repo second"  at "http://repo.spray.cc/",
  	"Akka repo"          at "http://repo.akka.io/releases/"
)

libraryDependencies ++= Seq(
    "com.sillycat"		  %%  "easycassandraserver"  	  % "1.0",
    "org.spark-project"   %%  "spark-core"                % "0.7.0" 
)

seq(Revolver.settings: _*)

assemblySettings

mainClass in assembly := Some("com.sillycat.easysparkserver.Boot")

artifact in (Compile, assembly) ~= { art =>
  art.copy(`classifier` = Some("assembly"))
}

excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter {_.data.getName == "parboiled-scala_2.10.0-RC1-1.1.3.jar"}
}

addArtifact(artifact in (Compile, assembly), assembly)


