# JSON Simple Enhanced

An enhanced version of the [json-simple](https://github.com/fangyidong/json-simple) library, with added features to simplify working with JSON in Java.

## Features

-   **Dotted JSON Traversal**: Access and modify nested JSON values using dot notation (e.g., `"country.code"`).

# Getting started

## Manual installation

Download the latest version from my [Maven repository](https://nexus.jasonkaranik.com) and add it to your project's classpath.

## Maven

Add this repository and dependency to your `pom.xml`:

### Repository:
```xml
<repository>
    <id>jasonkaranik-nexus</id>
    <url>https://nexus.jasonkaranik.com/repository/maven-releases/</url>
</repository>
```

### Dependency:
```xml
<dependency>
    <groupId>com.jasonkaranik</groupId>
    <artifactId>json-simple-enhanced</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage Example

### Import the `JSONObject` class:

```java
import com.jasonkaranik.jsonsimpleenhanced.JSONObject;
```

### Parse a JSON string and access nested values with dot notation:

```java
JSONObject json = new JSONObject("{ \"first_name\": \"John\", \"last_name\": \"Doe\", \"country\": { \"code\": \"US\", \"calling_code\": \"+1\" } }");

System.out.println(json.get("first_name")); // John

System.out.println(json.get("country.code")); // US
```

### Handling Missing or Incorrect Keys & Modifying values:

```java
System.out.println(json.get("date_of_birth")); // null

if (!json.containsKey("phone_number")) {
    json.put("phone_number", "+1 (123) 456-7890");
}
```

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for more details.