import sbt._
import Keys._
// import DB.DBSeeder._

object DwellingsBuild extends Build {

	
  val hwsettings = Defaults.defaultSettings ++ Seq(
    version      := "1.0-SNAPSHOT",
    scalaVersion := "2.9.1"
  )

	val seed = TaskKey[Unit]("db-seed")
	
	val seedTask = seed := {
		// DBSeeder.seed
	}
	
	


  lazy val project = Project (
    "project",
    file ("."),
    settings = hwsettings ++ Seq(seedTask)
  )
}