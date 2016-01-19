# restJavaSeExample
Some easy examples of REST with only Java SE (with jersey and glizzly)

This project need some projects dependances, so do this commands tu run the dependances on the target :

    git clone https://bruno@lsis.univ-tln.fr/redmine/ebenseignement/librarymanager/librarymanagerjava.git
    cd librarymanagerjava
    mvn install
    cd ..
    git clone https://bruno@lsis.univ-tln.fr/redmine/ebenseignement/librarymanager/libraryjavagui/observablelibrarygit.git
    cd observablelibrarygit
    mvn install
    cd ..
    git clone https://bruno@lsis.univ-tln.fr/redmine/ebenseignement/librarymanager/ihmsimple.git
    cd ihmsimple
    mvn install
    cd ..

# examples with Curl client after running the java project :

Curl examples :

    // Get :
    // All authors
    curl -H "Accept: application/json"  http://localhost:9998/biblio/auteurs
    // Author with id 0
    curl -H "Accept: application/json"  http://localhost:9998/biblio/auteur/0
    // Author with id 1
    curl -H "Accept: application/json"  http://localhost:9998/biblio/auteur/1
    // Delete
    // Delete Author number 0
    curl -H "Accept: application/xml" -X DELETE  http://localhost:9998/biblio/auteur/0
    // PUT
    // Put an author with name Durand ans prenom Jean
    curl -H "Content-Type: application/json" -X PUT http://localhost:9998/biblio/auteur\?nom\=Durand\&prenom\=Jean
