# NeoEats - Software for Cafes and Restaurants

## Jobs

[![Checkstyle](https://github.com/caspianbank/neoeats/actions/workflows/gradle.yml/badge.svg)](https://github.com/caspianbank/neoeats/actions/workflows/gradle.yml)

## Setup

### Git Submodule

Clone the repository by executing the following command on your terminal

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

### Coding Guidelines

Internationalization messages format should be like this:

```properties
# validation messages
validation.required=The field "{0}" is required.
validation.invalid.email=Please enter a valid email address.

# error messages
error.database=Database error occurred: {0}
error.not.found=The requested resource was not found: {0}
error.unauthorized=You are not authorized to perform this action.

# success messages
success.operation.completed=The operation was completed successfully.
success.item.created=The item was created successfully: {0}
success.item.updated=The item was updated successfully: {0}

# info messages
info.processing=Processing your request, please wait...
info.data.loaded=Data loaded successfully.
info.action.completed=Action completed successfully: {0}

# warning messages
warning.unsaved.changes=You have unsaved changes. Do you want to proceed?
warning.deletion.confirmation=Are you sure you want to delete the item: {0}?
warning.unsupported.operation=This operation is not supported in the current context.

# System/technical messages
system.startup=System is starting up...
system.shutdown=System is shutting down...

# Security/authentication messages
security.login.success=Login successful for user: {0}
security.login.failure=Login failed for user: {0}. Please check your credentials.

# Pagination/sorting/messages
pagination.page.size={0} items per page
pagination.page.number=Page {0} of {1}
pagination.page=Page {0} of {1}
pagination.total.records=Total records: {0}
filter.invalid=Invalid filter value for "{0}"
sort.unsupported=Unsupported sort parameter: "{0}"

# Form or field labels (optional but useful)
form.username=Username
form.password=Password
form.email=Email Address
label.username=Username
label.password=Password
label.email=Email Address
```