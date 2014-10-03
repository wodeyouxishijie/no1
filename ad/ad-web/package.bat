@echo off
mvn clean install package -Dmaven.test.skip=true -X
pause