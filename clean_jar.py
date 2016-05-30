import os
import shutil
try:
	os.remove("Android/Positron/app/libs/entities-1.0-SNAPSHOT.jar")
	print("le jar dans l'android est supprimé")
except:
	print("ya pa de jar dans ton android tête de naz")
	pass

try:
	shutil.copyfile('entities/target/entities-1.0-SNAPSHOT.jar', 'Android/Positron/app/libs/entities-1.0-SNAPSHOT.jar')
	print("la copie du nouveau jar généré (si t'as pas oublié de mvn package à partir de entities comme un naab) a été effectué")
except:
	print("la copie du jar côté java vers l'android a foiré")
