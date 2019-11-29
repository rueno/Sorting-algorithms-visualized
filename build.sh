#!/bin/bash
if [ ! -d "target"]
then
    echo "Target directory not found. Creating..."
    mkdir ./target
    echo "File created."
fi

cd $TRAVIS_BUILD_DIR/target
mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V