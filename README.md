# NeoEats - Software for Cafes and Restaurants

## Jobs

[![Checkstyle](https://github.com/caspianbank/neoeats/actions/workflows/gradle.yml/badge.svg)](https://github.com/caspianbank/neoeats/actions/workflows/gradle.yml)

## Coding Rules for Claude or AI tools

```
Coding Rules:
1. Dependency Injection: Do not use @Autowired annotation, use private final and @RequiredArgsConstructor for constructor dependency injection
2. API Endpoints: Must start with /api/v1/{dynamic} pattern
3. Imports: Use correct imports:
   * import jakarta.validation.Valid;
   * import org.springframework.validation.annotation.Validated;
4. Annotations:
   * Do not use @CrossOrigin annotation
   * Use @Getter and @Setter annotations for getter and setters with Lombok
   * Use @Slf4j annotation for logging
5. Documentation & Structure:
   * No JavaDoc comments
   * Never generate inner classes until specifically requested
   * Use MapStruct mappers for mapping between entity, response and request classes
6. Validation: Write validation messages when using @NotBlank, @NotNull annotations
7. Exception Handling: Use RecordNotFoundException instead of EntityNotFoundException
8. Naming Conventions:
   * Request body classes must contain "Request" suffix
   * Response classes must have "Response" suffix
9. for CRUD implementations, Instead of separate {entity}CreateRequest and {entity}UpdateRequest classes, create:
    1. One Request class (used for both create and update operations)
    2. One Response class
Package Structure:
Base Packages:
* az.restopia.order (Orders)
* az.restopia.business (Employees)
* az.restopia.conversations (Conversations)
* az.restopia.tickets (Tickets)
Standard Sub-packages:
* {base}.controller
* {base}.service
* {base}.service.impl
* {base}.domain.entity
* {base}.domain.request
* {base}.domain.response
* {base}.domain.mapper
* {base}.repository
Common/Shared Packages:
* az.neotech.commons.audit.DateAudit (DateAudit class)
* az.restopia.commons.domain.enums.DeleteStatus (DeleteStatus enum)
* az.restopia.commons.exception.RecordNotFoundException (Exception class)
Service Layer Structure:
* Service interfaces in {base}.service package
* Service implementations in {base}.service.impl package
* Mapper classes in {base}.domain.mapper package
10. Tenant Code: Always receive tenantCode from header where necessary by using @RequestHeader("X-Tenant-Code") @NotBlank(message = "Tenant code is required") String tenantCode instead of path variables
```



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