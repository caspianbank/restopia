# NeoEats - Software for Cafes and Restaurants

## Jobs

[![Checkstyle](https://github.com/caspianbank/neoeats/actions/workflows/gradle.yml/badge.svg)](https://github.com/caspianbank/neoeats/actions/workflows/gradle.yml)

## Setup

### Git Submodule

Clone repository by executing following command on your terminal

```shell
git clone --recurse-submodules git@github.com:caspianbank/neoeats.git
```

Consider updating submodules whenever pulling changes from master

```shell
git pull origin master
git submodule update --init --recursive
```

### Library

In order to start writing code, pull neotech-commons library
from [GitHub repo](https://github.com/caspianbank/neotech-commons) to your machine. Execute following gradle
task on neotech-commons project:

```shell
./gradlew publishToMavenLocal
```

After publishing to local Maven (.m2 folder) repository, execute `./gradlew clean build` command in neoeats project.

