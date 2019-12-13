#!/bin/bash

set -e

./gradlew clean war

rsync -av dynamic/build/libs/OpenActivities-website-dynamic-0.0.1-production.war \
root@web1.topobyte.de:/opt/tomcat/tomcat-main/rootapps/www.openactivities.org/ROOT.war
