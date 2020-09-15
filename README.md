# Group gr2015 repository

The repo contains group 15's project for IT1901. The project can be accessed in the folder "mememedb". 

## Maven Goals

* `validate` - validate the project is correct and all necessary information is
available
* `compile` - compile the source code of the project
* `test` - test the compiled source code using a suitable unit testing framework.
These tests should not require the code be packaged or deployed
* `package` - take the compiled code and package it in its distributable format,
such as a JAR.
* `verify` - run any checks on results of integration tests to ensure quality
criteria are met
* `install` - install the package into the local repository, for use as a dependency
in other projects locally
* `deploy` - done in the build environment, copies the final package to the remote
repository for sharing with other developers and projects.
* `jacoco:report` - generate test coverage report to target/site/jacoco
* `javafx:run` - run the javafx app