lazy val hub = (project in file("hub")).
  enablePlugins(JavaAppPackaging).
  settings(
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "com.udata"  %%  "hub"        % "0.0.30-SNAPSHOT",
      "com.udata"  %%  "mongo"      % "0.0.31-SNAPSHOT",
      "com.udata"  %%  "aws"        % "0.0.31-SNAPSHOT"
    )
  )
