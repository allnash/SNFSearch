name := "SNFSearch"

version := "1.0"

scalaVersion := "2.11.8"

maintainer := "Nash Gadre <gadre@gadre.xyz>"

packageSummary := "Skilled Nursing Provider Search Tool"

packageDescription := """Get the list of closest SNF (Skilled Nursing Providers)"""

mainClass in (Compile, run) := Some("com.snf.Main")

mainClass in (Compile, packageBin) := Some("com.snf.Main")

compileOrder := CompileOrder.JavaThenScala

enablePlugins(JavaAppPackaging)