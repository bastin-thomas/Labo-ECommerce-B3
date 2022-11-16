###############################################################################
#                                                                             #
#   Bastin Thomas                                                             #
#   2301                                                                      #
#   I.2. Aéroport                                                             #
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
Aeroport <- read.csv(file = "poste-budget-aeroport.csv", sep = ";", na.strings = "NA", header = TRUE, stringsAsFactors = TRUE, row.names = "Année" )
summary(Aeroport)
str(Aeroport)

ACP <- PCA(Aeroport, scale.unit = TRUE, quali.sup = c(10))

# Graphe des individus:
# On remarque directement une petite corélation entre l'orientation politique de l'année et les postes budgétaire de l'aéroport (Gauche/Droite).
# On remarque en approfondissant: Les dettes courts terme semble être l'explication de f1 avec l'orientation politique Le chiffre d'affaire semble également être corrélé a F1
plot(ACP, cex=0.65, habillage = 5, col.hab = c("red", "blue"), invisible="quali")




# Les dettes long/Moyen termes semble expliqué F2 et la température (mais la vision pourrais être biaisé pour la température !!!)


# Les dim 1 et 2 expliquent de façons suffisante les variables (49.67+25.75 = 75.42%)
# On peut remarqué qu'après la crise de 2008 c'est la gauche qui était au pouvoir principalement, Centre Gauche après le krash boursier de 2002 et avant CD et DR

# Graphe des variables:
# Les subventions sont fortement inversément corrélé aux immobilisations
# Les créances court termes et les intérèts n'ont pas d'impacte sur les immobilisations
# Le Chiffre d'affaire est fortement corrélé aux subventions
# Les dettes court terme sont relativement corrélé aux CA et au Subventions
# Les capitaux propres sont inversément proportionné au dette court terme.


# Conclusion pour F1 et F2:
# Le chiffre d'affaire augmente avec un gouv de gauche (Si plus d'argent pour tous, plus de personne prenne l'avion)
# Les subventions augmente avec un gouv de gauche (La gauche soutiens plus les entreprises (surtout si publique ou proche de l'état))
# Les dettes court terme augmente avec un gouv de gauche

# La température est difficilement interprétable au vus de la longueur de la flèche
# Les dettes Long Terme augmente plus le gouvernement est centriste

# Les créances court terme, les capitaux propres, et les intérêts augmente avec un gouvernement de droite (plus d'investissement, que se soit en près, ou en capitaux propres)
# Les immobilisations(Investissement dans du "matériel") augments avec un gouvernement de Centre Droite


ACP$eig

barplot(ACP$eig[,2], names=paste("d",1:nrow(ACP$eig)))

ACP$var$cos2

# La température ne joue aucunnement sur la dim1 et peut sur la dim2

ACP$var$contrib

plot(ACP, cex=0.65, habillage = 1, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 2, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 3, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 4, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 5, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 6, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 7, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 8, col.hab = c("red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 9, col.hab = c("red", "blue"), invisible="quali")

plot(ACP, cex=0.65, habillage = 10, col.hab = c("red", "blue", "red", "blue"), invisible="quali")
plot(ACP, cex=0.65, habillage = 10, col.hab = c("green", "green", "yellow", "yellow"), invisible="quali")

dim <- dimdesc(ACP)
dim$Dim.1$quanti
# On remarque directement l'explication avec les immobilisation, capitaux propres, CA, Subv et Dettes court terme 
dim$Dim.2$quanti
# On remarque l'explication avec les dettes long et moyens terme 

ACP2 <- PCA(Aeroport, scale.unit = TRUE, quali.sup = c(10), axes = c(1,3)) # On remarque que la température moyenne annuel joue fortement sur dim3

pairs(Aeroport[, 1:9])

cor(Aeroport[, 1:9])



# F1(x): Les dettes courts terme semble être l'explication de f1 avec l'orientation politique et Le chiffre d'affaire

# F2(y): dettes long/Moyen termes et la temp moyenne
