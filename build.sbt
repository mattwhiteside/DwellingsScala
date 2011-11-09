

organization := "org.dyndns"

name := "dwellings"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)                         

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "jboss repo" at "http://repository.jboss.org/nexus/content/groups/public-jboss/"

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.1",
  "org.scalatra" %% "scalatra-scalate" % "2.0.1",
  "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "jetty",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
	"org.squeryl" %% "squeryl" % "0.9.4" % "compile",
	//doesn't work yet   
  //"org.squeryl" %% "squeryl" % "0.9.5-SNAPSHOT" % "compile" from "http://scala-tools.org/repo-snapshots/org/squeryl/squeryl_2.9.1/0.9.5-SNAPSHOT/squeryl_2.9.1-0.9.5-SNAPSHOT.jar",  
  "org.ccil.cowan.tagsoup" % "tagsoup" % "1.2.1" %  "compile->default",
	"com.eltimn" % "scalaxml" %	"0.5-SNAPSHOT" from "http://li331-157.members.linode.com/scalaxml_2.8.1-0.3.jar",
  "postgresql" % "postgresql" % "9.0-801.jdbc4",
  "dd-plist" % "dd-plist" % "r46" from "http://li331-157.members.linode.com/dd-plist.jar",
  "log4j" % "log4j" % "1.2.16",
  "org.slf4j" % "slf4j-log4j12" % "1.6.2" ,
  "c3p0" % "c3p0" % "0.9.1.2" 
)

