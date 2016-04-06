# restJavaSeExample
Some easy examples of REST with only Java SE (with jersey and grizzly)

# execute the main class (run server)

    mvn exec:java  -Dexec.mainClass="fr.univtln.bruno.exemple.simplerest.Main" 

# examples with Curl client after running the java project :

Curl examples :

    // Get :
    // All sms
    curl -H "Accept: application/json"  http://localhost:9998/bank/allsms
    
    {"cSmsReceived":[{"dateReceiving":"janvier","phone":"+33565456","data":"coords X Y","ID":"0"},{"data":"coords X Y","ID":"1"}]}
    
    // Get SMS ID 0
    curl -H "Accept: application/json"  http://localhost:9998/bank/sms/0

    {"dateReceiving":"janvier","phone":"+33565456","data":"coords X Y","ID":"0"}

    // Get SMS ID 0
    curl -H "Accept: application/json"  http://localhost:9998/bank/sms/1
    
    {"data":"coords X Y","ID":"1"}

# Docker?

I try to mvn:execute my main class with docker (that conaints maven and java contener):

    docker run -p 9998:9998 -it --rm --name my-maven-project1 -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven maven:3.2-jdk-7 mvn exec:java  -Dexec.mainClass="fr.univtln.bruno.exemple.simplerest.SmsClient" 

the server seems to be running :

    INFO: Scanning for root resource and provider classes in the packages:
      fr.univtln.bruno.exemple.simplerest
    Apr 06, 2016 8:24:31 PM com.sun.jersey.api.core.ScanningResourceConfig logClasses
    INFO: Root resource classes found:
      class fr.univtln.bruno.exemple.simplerest.Sms
    Apr 06, 2016 8:24:31 PM com.sun.jersey.api.core.ScanningResourceConfig init
    INFO: No provider classes found.
    Starting grizzly2...
    Apr 06, 2016 8:24:31 PM com.sun.jersey.server.impl.application.WebApplicationImpl _initiate
    INFO: Initiating Jersey application, version 'Jersey: 1.18.1 02/19/2014 03:28 AM'
    Apr 06, 2016 8:24:32 PM org.glassfish.grizzly.http.server.NetworkListener start
    INFO: Started listener bound to [localhost:9998]
    Apr 06, 2016 8:24:32 PM org.glassfish.grizzly.http.server.HttpServer start
    INFO: [HttpServer] Started.
    Jersey app started with WADL available at http://localhost:9998/application.wadl
    Hit enter to stop it...


but when i wanna try to access with curl client, 

    curl -H "Accept: application/json"  http://localhost:9998/bank/allsms

i have some error :

    curl 56 recv failure connection reset by peer

I don't know how to fix it 
