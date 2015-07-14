#!/bin/bash
mvn versions:set -DnewVersion="$1"
mvn clean deploy -Prelease
git reset HEAD --
git co -- .
