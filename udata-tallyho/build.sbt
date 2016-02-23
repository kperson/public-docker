lazy val hub = (project in file("hub")).
  enablePlugins(JavaAppPackaging).
  settings(
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "com.udata"  %%  "hub"        % "0.0.41-SNAPSHOT",
      "com.udata"  %%  "mongo"      % "0.0.43-SNAPSHOT",
      "com.udata"  %%  "aws"        % "0.0.43-SNAPSHOT"
    )
  )
