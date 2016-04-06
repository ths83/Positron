# restJavaSeExample
Some easy examples of REST with only Java SE (with jersey and glizzly)

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
