REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.app.agvManager\target\base.app.agvmanager-1.4.0-SNAPSHOT.jar;base.app.agvManager\target\dependency\*;
REM call the java VM, e.g,
java -cp %BASE_CP% eapli.base.app.agvManager.TcpSrvAgvManager