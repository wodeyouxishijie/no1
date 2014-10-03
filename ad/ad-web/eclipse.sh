#!/bin/bash
mvn clean eclipse:eclipse -Dmaven.test.skip=true -DdownloadSource=true -X
