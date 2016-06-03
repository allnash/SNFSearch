Skilled Nursing Facility Search
===============================
Project to search Skilled Nursing Providers close to a Users Location within 10 mile radius.

##This project uses following OpenSource Projects
* Jackson JSON

##This project needs the following on your machine
* Java 7.0+
* Activator lib (included)
* GIT

	`git clone https://github.com/allnash/SNFSearch.git`

	`cd SNFSearch`

##Install SNFSearch tool 'snf'

run the following command in your terminal:

	./install.sh

snf will be installed in your local path. You can run it by typing the following.

	~/local/bin/snf --help

##To Build SNFSearch tool and install it

run the following command in your terminal:

	./package.sh
	# This process may take time if its the first time you are using Scala.

##SNF Outputs Providers JSON array in `output.json` file in the directory it runs

	# Example to run SNFSearch
	~/local/bin/snf -lat 42.358506 -lon -71.060142
	~/local/bin/snf -lat 42.358506 -lon -71.060142 -radius 5.0
