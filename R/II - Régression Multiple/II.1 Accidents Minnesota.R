###############################################################################
#                                                                             #
#   Bastin Thomas                                                             #
#   2301                                                                      #
#   II.1. Accidents Routes Minnesota                                          #
#                                                                             #
###############################################################################

#HEADER
# Vider la memoire
rm(list=ls()) 

# creer une fonction printf  
printf <- function(...) cat (sprintf(...)) 

# creer une fonction pour effacer la console
cls <- function() printf("\014")          
cls()

# Set WorkingDirectory
setwd("E:\\HEPL\\R\\Labo-ECommerce-B3\\R\\Files")

library(FactoMineR)
#FIN HEADER

#1. Importation des données:
Accident <- read.csv(file = "accidents2.csv", sep = ",", dec = ".", na.strings = "NA", header = TRUE, stringsAsFactors = TRUE, row.names = "X")
summary(Accident)
str(Accident)

plot(Accident)

# Cherche l'éventuelle relation entre ce taux d'accidents et nombre de signaux
# routiers par mile associé à la largeur de la bande d'urgence latérale
plot(Accident[, c(1,5,7)])

# Estimation Poncutelles Beta.i:
Model <- lm(formula = Accident$TauxAccident ~ Accident$SignauxParMiles + Accident$LargeurVoiesUrgence)

summary(Model)
# Le summary effectue des tests de students pour s'assuré que les Beta.i != 0 (H0 Beta.i == 0).
# Est ce que nos estimations sont fiables et que les régresseurs interviennent dans le modèle mathématique.
# Dans notre cas, les pvalues sont plus petites que 0 donc, on rejette H0. Donc les beta.i ne sont pas similaire.
# Tout le coef de toutes les variables est significatif, il ne faut pas utiliser de sous-modèle

#p-value: <0.05, rejet h0(yi = β0 + εi), donc il existe une corrélation mais qui est faiblement expliquée.

# Multiple R-Squared (Régression): 0.4627

#Il existe une corélation cependant cette dernière reste Faible




# Dans un second temps, on demande d'ajouter d'autres variables explicatives dans le modèle testé, 
# comme par exemple le nombre d'entrées par mile d'autoroute.
plot(Accident[, c(1,5,7,9)])

Model2 <- lm(formula = Accident$TauxAccident ~ Accident$SignauxParMiles + Accident$LargeurVoiesUrgence + Accident$EntréesParMiles)
summary(Model2)

# Selon le summary on voit directement que la LargeurVoiesUrgence a une pvalue plus grande que 0.05, donc il faut accepter pour Beta.2 h0
# Donc Beta.2 = 0 il faut retiré la LargeurVoiesUrgence et créer un sous modèle ne contenant plus LargeurVoiesUrgences.

SubModel2 <- lm(formula = Accident$TauxAccident ~ Accident$SignauxParMiles + Accident$EntréesParMiles)
summary(SubModel2)

# Avec 0.6295 de coef de correl, on commence a s'approché d'une bonne corrélation 
# mais il reste encore du chemin a parcourir avant de parlé de quelques choses de concret
# On peut continuer a ajouter des variables mais malheureusement ses dernière n'ajoutent pas grand choses au modèle. et le rende plus complexe. Il faut donc s'arreter la

Model3 <- lm(formula = Accident$TauxAccident ~ Accident$SignauxParMiles + Accident$EntréesParMiles)
summary(Model3)

# Signaux Par Miles et Entrées par miles sont donc les variables explicant le plus le taux d'accident
# Les autres ont ne pourra pas faire de modèle linéaire basé dessus.









