#!/bin/sh
mvn clean install package -Dmaven.test.skip=true
rm -rf /Users/chenjie/servers/Tomcat7/webapps/*
mv /Users/chenjie/sources/apps/apps/target/apps/* /Users/chenjie/servers/Tomcat7/webapps/