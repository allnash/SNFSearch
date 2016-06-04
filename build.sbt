name := "SNFSearch"

version := "1.0"

scalaVersion := "2.11.8"

maintainer := "Nash Gadre <gadre@gadre.xyz>"

packageSummary := "Skilled Nursing Provider Search Tool"

packageDescription := """Get the list of closest SNF (Skilled Nursing Providers)"""

mainClass in (Compile, run) := Some("com.snf.Main")

mainClass in (Compile, packageBin) := Some("com.snf.Main")

libraryDependencies += "com.sparkjava" % "spark-core" % "2.5"

compileOrder := CompileOrder.JavaThenScala

// If you need to specify main classes manually, use packSettings and packMain
packSettings

packResourceDir += (baseDirectory.value / "data" -> "data")

// [Optional] Creating `hello` command that calls org.mydomain.Hello#main(Array[String])
packMain := Map("snf" -> "com.snf.Main")

enablePlugins(JavaAppPackaging)