# Build and Run

mvn clean
mvn exec:java


# steps to have a custom embed tomcat without connection close.


## 1. Clone and build custom tomcat fork

```
git clone https://github.com/bercab/tomcat.git
cd tomcat
git checkout 10.1.x-wb
ant clean
ant -Dbase.path=/tmp/tomcat-deps \
    -Dembed.path=/tmp/tomcat-embed \
    clean embed
```


## 2. Install the version in the local maven repos: 

```
cd output/embed

mvn install:install-file \
  -Dfile=tomcat-embed-core.jar \
  -DgroupId=org.apache.tomcat.embed \
  -DartifactId=tomcat-embed-core \
  -Dversion=10.1.35-wb \
  -Dpackaging=jar

mvn install:install-file \
  -Dfile=tomcat-embed-jasper.jar \
  -DgroupId=org.apache.tomcat.embed \
  -DartifactId=tomcat-embed-jasper \
  -Dversion=10.1.35-wb \
  -Dpackaging=jar
```
  
  
## 3. On your project, change the embed tomcat deps:


```
    <dependencies>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-core</artifactId>
            <version>10.1.35-wb</version>
        </dependency>
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <version>10.1.35-wb</version>
        </dependency>
    </dependencies>
```
