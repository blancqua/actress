#!/bin/bash
mvn release:clean release:prepare release:perform -B -e | tee maven-central-deploy.log
