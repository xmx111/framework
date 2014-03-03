svn update
mvn clean jetty:run -Djetty.port=8090 -Dmaven.test.skip=true -Djava.awt.headless=true