###############################################################################
#                                                                             #
#   Bastin Thomas                                                             #
#   2301                                                                      #
#   I.1. Le Mais                                                              #
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
setwd("E:\\HEPL\\R\\Labo-R-B3\\ACP\\Files")

library(FactoMineR)
#FIN HEADER

#1. Importation des données:
etude_agro <- read.csv(file = "etude-agro-mais.csv", sep = ";", na.strings = "NA", header = TRUE, row.names = "Individu", stringsAsFactors = TRUE )


etude_agro$Hauteur[is.na(etude_agro$Hauteur)] <- mean(etude_agro$Hauteur, na.rm = TRUE)
etude_agro$Masse[is.na(etude_agro$Masse)] <- mean(etude_agro$Masse, na.rm = TRUE)
etude_agro$Nb.grains[is.na(etude_agro$Nb.grains)] <- mean(etude_agro$Nb.grains, na.rm = TRUE)
etude_agro$Masse.grains[is.na(etude_agro$Masse.grains)] <- mean(etude_agro$Masse.grains, na.rm = TRUE)

etude_agro$Censure.droite <- NULL
etude_agro$Nb.jours.attaque <-NULL
etude_agro <- etude_agro[-c(1), ]

summary(etude_agro)
str(etude_agro)


x11()
ACP <- PCA(etude_agro, scale.unit = TRUE, quali.sup = c(5:10,12), quanti.sup = c(11))



#G1 La Masse Total du Plant et la Hauteur du Plant sont fortement corélée entre elles.

#G2 La masse par grain est fortement corrélé aux nombres de grains par plante.

#G1 n'est pas corrélé avec G2 (angle 90°)

#Toutes les variables sont corrélées de même manière avec F1 et F2

# F1(x): 

# F2(y): 
