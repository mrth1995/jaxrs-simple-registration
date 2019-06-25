# Simple registration page
This application use Vue.js for webclient and Java EE for server

## Setup Web Client
This web client run at http://localhost:8080 at default, but if port 8080 occopied by another service, this web run at http://localhost:8081. Following code is to run web client. Make sure you have installed node.js
```
cd webclient
npm install
npm run dev
```
### Registration Page
To access registration page you can access http://localhost:8081/#/registration

## Setup Database
This server using postgresql version 11 as database. Make sure you have installed postgresql 11 or you can download at https://www.postgresql.org/download/windows/.  
After postgresql installed, create database named ``reg_server``.

## Setup Server
This server run on wildfly-15.0.1-Final, you can download at https://wildfly.org/downloads/. Wildfly server run at http://localhost:8080. This application run with standalone-full.xml configuration. Following is steps to configure wildfly server.

### Add JDBC Driver
Add following line to add JDBC driver to server. You can download postgresql JDBC driver here: https://jdbc.postgresql.org/download.html. Make sure to download latest version. Put jdbc driver at ``/wildfly-15.0.1.Final/temp``.

Start wildfly using following command:
```
../wildfly-15.0.1.Final/bin/standalone.sh -c standalone-full.xml
```

After wildfly started, connect to jboss-cli using following command:
```
/Users/mrth1995/Documents/Server/wildfly-15.0.1.Final/bin/jboss-cli.sh -c
```

Add JDBC module using following command:
```
module add\
 --name=org.postgresql\
 --resources=../temp/postgresql-42.2.5.jar\
 --resource-delimiter=,\
 --dependencies=javax.api,javax.transaction.api
```
After that add postgresql jdbc driver using following command:
```
/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,\
driver-module-name=org.postgresql,\
driver-class-name=org.postgresql.Driver,\
driver-datasource-class-name=org.postgresql.ds.PGSimpleDataSource,\
driver-xa-datasource-class-name=org.postgresql.xa.PGXADataSource)
```
### Add Datasource  
To add datasource, stop server and input following code to add datasource to ``standalone-full.xml`` at ``/wildfly-15.0.1.Final/standalone/configuration/standalone-full.xml``
```
<datasource jndi-name="java:/jboss/datasources/reg_server" pool-name="reg_server" enabled="true" use-java-context="true">
    <connection-url>jdbc:postgresql://localhost:5432/reg_server</connection-url>
    <connection-property name="ServerName">
        localhost
    </connection-property>
    <connection-property name="PortNumber">
        5432
    </connection-property>
    <connection-property name="DatabaseName">
        reg_server
    </connection-property>
    <driver>postgresql</driver>
    <security>
        <user-name>postgres</user-name>
        <password>postgres</password>
    </security>
    <validation>
        <check-valid-connection-sql>select 1</check-valid-connection-sql>
        <background-validation>true</background-validation>
        <background-validation-millis>5000</background-validation-millis>
    </validation>
</datasource>
```

If datasource already added, start wildfly again.

## Deploying Server Apps

Compile the server code using Java 1.8. Compiled code will be at ``../registration-server/target/registration-server-1.0-SNAPSHOT.war``.

While server is already started, copy war file to following folder: ``wildfly-15.0.1.Final/standalone/deployments/``

That's it, server apps deployed. You can access webclient and try to register user.