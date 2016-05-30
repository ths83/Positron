import os
import shutil

directory = "Android/Positron/app/libs"


# Si le folder libs n'existe pas dans le projet android, on le crée
if not os.path.exists(directory):
    os.makedirs(directory)

# Si le jar existe dans le projet android on le supprime
try:
	os.remove("Android/Positron/app/libs/entities-1.0-SNAPSHOT.jar")
	print("le jar dans l'android est supprimé")
except:
	print("ya pa de jar dans ton android tête de naz")
	pass

# Si le jar existe dans le target côté java, on le copie dans les libs du projet android
try:
	shutil.copyfile('entities/target/entities-1.0-SNAPSHOT.jar', 'Android/Positron/app/libs/entities-1.0-SNAPSHOT.jar')
	print("la copie du nouveau jar généré (si t'as pas oublié de mvn package à partir de entities comme un naab) a été effectué")
except:
	print("la copie du jar côté java vers l'android a foiré")
