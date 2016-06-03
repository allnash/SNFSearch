#!/bin/bash
git fetch --all && git pull
echo "+ APP READY +"
echo "+ ATTEMPTING TO BUILD SNF SEARCH TOOL +"
./bin/activator compile
echo "+ ATTEMPTING TO BUILD CURRENT BINARY FOR UNIVERSAL SYSTEMS +"
./bin/activator pack
echo "+ STAGING BUILD COMPLETED +"
echo "+ INSTALLING 'snf' TO YOUR  $ ~/local/bin/snf"
cp -r target/pack/ installer
# cd installer; make install
./install.sh
export PATH=$HOME/local/bin:$PATH
echo " "
echo "+ SUCCESSFULLY INSTALLED 'snf' , RUN PROGRAM BY TYPING '~/local/bin/snf' +"
echo " "
echo "+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +"
echo "+    Add the following configuration to your .bash_profile, .zsh_profile, etc. for the usability:         +"
echo "+    export PATH= $ HOME/local/bin: $ PATH                                                                + "
echo "+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +"
