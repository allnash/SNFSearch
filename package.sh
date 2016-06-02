#!/bin/bash
git fetch --all && git pull
echo "+ APP READY +"
echo "+ ATTEMPTING TO BUILD SNF SEARCH TOOL +"
./bin/activator compile
echo "+ ATTEMPTING TO BUILD CURRENT .TAR FOR UNIVERSAL SYSTEMS +"
./bin/activator universal:packageBin
echo "+ STAGING BUILD COMPLETED +"
cp target/universal/snfsearch-1.0.zip .
