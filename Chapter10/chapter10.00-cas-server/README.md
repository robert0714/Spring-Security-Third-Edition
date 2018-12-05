CAS Overlay Template
============================

Generic CAS WAR overlay to exercise the latest versions of CAS. This overlay could be freely used as a starting template for local CAS war overlays. The CAS services management overlay is available [here](https://github.com/apereo/cas-services-management-overlay).

# Versions

```xml
<cas.version>5.3.x</cas.version>
```

# Requirements

* JDK 1.8+

# Configuration

The `etc` directory contains the configuration files and directories that need to be copied to `/etc/cas/config`.

# Git clone
Launch your favorite command prompt and clone the CAS overlay project into your desired project. I am going to create a folder named cas-sample , wherein I will clone the server in the server folder by executing the following command from the cas-sample folder:
```
git clone https://github.com/apereo/cas-overlay-template.git server
```

# Adding additional dependencies
 
The CAS server doesn't allow any client to connect to it. Each client has to be registered with the desired CAS server. There are multiple mechanisms by which we can register a client to the server. We will use the JSON/YML configuration to register our client to the server. Go ahead and add the following dependency to your pom.xml file within the server project that you just cloned:

```
    <dependency>
       <groupId>org.apereo.cas</groupId>
       <artifactId>cas-server-support-json-service-registry</artifactId>
       <version>${cas.version}</version>
    </dependency>
    <dependency>
       <groupId>org.apereo.cas</groupId>
       <artifactId>cas-server-support-yaml-service-registry</artifactId>
       <version>${cas.version}</version>
    </dependency>
```
Most of the versions in the pom.xml file are managed by the parent POM.

# Setting up the resources folder in the project
In the server project, create a folder called src/main/resources . Copy the etc folder within the server folder into src/main/resources :

```
    mkdir -p src/main/resources
    cp -R etc src/main/resources
```
# Creating the application.properties file
Create a file named application.properties :
```
touch src/main/resources/application.properties
```

Now fill in the following details in the *application.properties* file:

```
server.context-path=/cas
server.port=9443
server.ssl.key-store=classpath:/etc/cas/thekeystore
server.ssl.key-store-password=changeit
server.ssl.key-password=changeit
cas.server.name: https://localhost:9443
cas.server.prefix: https://localhost:9443/cas
cas.adminPagesSecurity.ip=127.0.0.1
cas.authn.accept.users=casuser::password
```

The preceding file sets the port and SSL keystore values (a very important step in setting up a CAS server), and also sets up the CAS server *config* folder. Clearly, we need to create a keystore as indicated in this file.

Please note, the overlay project has a file, namely the *build.sh* file, that contains most of these details in it. We are manually doing this to have a clear understanding.

The last line in *application.properties* sets up a test user with the credentials *casuser*/*password* , which can be used to log into the CAS server for various
demo purposes. This approach is not recommended in the production setup.

# Creating a local SSL keystore

Navigate to the Chapter10/chapter10.00-calendar/src/main/resources/etc/cas folder in a shell and execute the following command:

```
keytool -genkey -keyalg RSA -alias  thekeystore  -keystore thekeystore  -storepass password  -validity 360 -keysize 2048
```

The following figure shows the successful execution of the preceding command in a command prompt window:


It's important to note that for the SSL handshake to work properly, most of the values while generating the keystore are put as localhost. This is an important step and needs to be followed without fail.


# Creating the .crt file to be used by the client
For the client to connect to the CAS server, out of the generated keystore, we need to create a .crt file. In the same folder ( Chapter10/chapter10.00-calendar/src/main/resources/etc/cas ),run the following command:

```
keytool -export -alias thekeystore -file thekeystore.crt -keystore thekeystore
```
When asked for a password, provide the same password (we have set the password as *password* ). Executing the preceding command will create *thekeystore.crt* file.

# Exporting the .crt file to Java and the JRE cacert keystore

Execute the following command to find your Java installation directory:
```
/usr/libexec/java_home
```

Alternatively, execute the following command directly to add the .crt file to Java cacerts:

```
keytool -import -alias thekeystore -storepass password -file thekeystore.crt -keystore  "$(/usr/libexec/java_home)\jre\lib\security\cacerts"
```

The following figure shows successful execution of the preceding command in a command prompt window:

When setting up a client, make sure that the JDK used is the same as the one in which we have added the *.crt* file. To reflect the certification addition on to Java, a restart of the machine is suggested.

# Building a CAS server project and running it
From within the cas-sample/cas-server folder, execute the following two commands:
```
./build.sh package
./build.sh run
```

If everything goes well, as shown in the following figure, you should see a log message which says READY:

Now open a browser and navigate to the URL https://localhost:9443/cas . This will navigate you to the default login form of the CAS server. Enter the default credentials ( casuser / Mellon ) and you are in. Most browsers would say that the connection is insecure. Add the domain as an exception and soon after that the application will work fine:

Log in with the demo test user ( testcasuser / password ) and you should be logged in and navigated to a user home page.