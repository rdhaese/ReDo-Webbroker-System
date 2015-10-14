#ReDo-Webbroker-System#
Project 1 Accademict track

##Running the application
Make sure you have Maven installed, you can then run the following command to create a deployable war:
```
 mvn clean install 
```

Now drop the war file in a web application server such as JBoss or Tomcat to run the application.

**Note:** The production application expects a 'webbrokerproduction' schema on: localhost:3306/webbroker with user 'root' and password 'root'. The tests require a similar schema with the name 'webbroker'.

##Running the tests

The  tests can be run with the following commad: 
```
 mvn test 
```
 
#Project structure

The **ejb-module** contains the bussiness logics and domain models of the application. Persistence to the database is also done in this module. The **web-app** module contains the view (JSF) of the application. 
