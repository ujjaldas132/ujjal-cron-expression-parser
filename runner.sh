#!/bin/bash

# Clean the project and build the jar
chmod +x ./gradlew
./gradlew clean build

# runner command . update the cron expression
java -cp build/libs/deliveroo-cron-parser-1.0-SNAPSHOT.jar  deliveroo.Parser "*/15 0 1,15  * 1-5 a"
