#!/bin/bash

# Maven automatically fetches the latest SNAPSHOT on daily basis. 
# You can force Maven to download latest snapshot build using -U 
# switch to any Maven command.

# build file does this: mvn clean resources:resources install -U
./build.sh

# check if build successful
if [[ "$?" -ne 0 ]] ; then
  echo "Build Failed"; exit $rc
fi

# portal file does this: mvn com.unitycloudware.maven.plugins:ucw-maven-plugin:run-portal -U
./run-portal.sh