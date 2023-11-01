# devfrk

## 1. Introduction

The project's goal is to simply develop spring-based app with automated unit testing and various data access.


### 1.1 Authors

- wuheng@iscas.ac.cn

### 1.2 Supported

- ShanDong Provincial Key Research and Development Program, China (2021CXGC010101)
 
## 2. Quick start

We assume your IDE supports Maven.

### 2.1 pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>[Your Project GroupId]</groupId>
	<artifactId>[Your Project ArtifactId]</artifactId>
	<version>[Youre Project Version]/version>
	<packaging>jar</packaging>
	<name>[Your Project Name]</name>
  
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.5</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<dependencies>
		<dependency>
			<groupId>io.github.kubesys</groupId>
			<artifactId>httpfrk-springboot</artifactId>
			<version>2.2.3</version>
		</dependency>
	</dependencies>
	
	<repositories>
		<repository>
			<id>pdos-repos</id>
			<name>PDOS Releases</name>
			<url>https://nexus3.iecas.cn/repository/maven-public/</url>
		</repository>
	</repositories>
</project>
```

maven setting

```
<settings>
    <!-- omitted xml -->
    <servers>
        <server>
            <id>g-ubjg5602-iscas-system-maven</id>
            <username>wuheng@iscas.ac.cn</username>
            <password>[PASSWORD]</password>
        </server>
    </servers>
</settings>
```

pom setting

```
<profiles>
    <profile>
        <id>Repository Proxy</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <repositories>
            <repository>
                <id>g-ubjg5602-iscas-system-maven</id>
                <name>maven</name>
                <url>https://g-ubjg5602-maven.pkg.coding.net/repository/iscas-system/jars/</url>
                <releases>
                    <enabled>true</enabled>
                </releases>
                <snapshots>
                    <enabled>true</enabled>
                </snapshots>
            </repository>
        </repositories>
    </profile>
</profiles>
```

### 2.2 application.yml

```
# General configuration
server:
  port: 9080
  servlet:
    context-path: /httpfrk
    
spring:
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
  
swagger:
  enabled: true
```

### 2.3 DemoService.Java

```
package demo;

import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

// class name must be ends with 'Service', mark with @ServiceDefinition, and has a parent named AbstractHttpHandler
@ServiceDefinition
public class DemoService extends AbstractHttpHandler {
	
	public String echoString( /* 参数注解 */
			@Valid @Size(min = 5, max = 20) String name) {
		return "Hello " + name + "!";
	}
	
}
```

### 2.4 DemoServer.java

```
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import io.github.kubesys.devfrk.spring.HttpServer;

// value is package name of DemoService
@ComponentScan(value = { "demo" })
public class DemoServer extends HttpServer  {

	public static void main(String[] args) {
		SpringApplication.run(DemoServer.class, args);
	}

}
```

### 2.4 Test

- Run DemoServer.java in your IDE
- Open your browser and access   http://localhost:9080/httpfrk/demo/echoString?name=123456

## Roadmap
- 2.1.0 support postgres, neo4j and elastic
- 2.2.0 support JSR303 validator
- 2.3.0 support generating testcases
- 2.4.0 support apifox docs
- 2.5.0 support test report


