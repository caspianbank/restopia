# NeoEats - Software for Cafes and Restaurants

## Setup

In order to start writing code, pull neotech-commons library
from [GitHub repo](https://github.com/caspianbank/neotech-commons) to your machine. Execute following gradle
task on neotech-commons project:

```shell
./gradlew publishToMavenLocal
```

After publishing to local Maven (.m2 folder) repository, execute `./gradlew clean build` command in neoeats project. 