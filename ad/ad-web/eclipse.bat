@echo off
mvn clean eclipse:eclipse -Dmaven.test.skip=true -DdownloadSource=true -e
pause