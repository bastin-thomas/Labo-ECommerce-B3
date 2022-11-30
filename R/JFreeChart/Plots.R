###############################################################################
#                                                                             #
#   Bastin Thomas                                                             #
#   2301                                                                      #
#   JFreeChart: Demo R                                                             #
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
etude_agro <- read.csv(file = "etude-agro-mais.csv", sep = ";", na.strings = "NA", header = TRUE, row.names = "Individu", stringsAsFactors = TRUE )


etude_agro$Hauteur[is.na(etude_agro$Hauteur)] <- mean(etude_agro$Hauteur, na.rm = TRUE)
etude_agro$Masse[is.na(etude_agro$Masse)] <- mean(etude_agro$Masse, na.rm = TRUE)
etude_agro$Nb.grains[is.na(etude_agro$Nb.grains)] <- mean(etude_agro$Nb.grains, na.rm = TRUE)
etude_agro$Masse.grains[is.na(etude_agro$Masse.grains)] <- mean(etude_agro$Masse.grains, na.rm = TRUE)

etude_agro$Enracinement <- relevel(etude_agro$Enracinement, "Tres.fort")
etude_agro$Enracinement <- relevel(etude_agro$Enracinement, "Fort")
etude_agro$Enracinement <- relevel(etude_agro$Enracinement, "Moyen")
etude_agro$Enracinement <- relevel(etude_agro$Enracinement, "Faible")

etude_agro$Censure.droite <- NULL
etude_agro$Nb.jours.attaque <-NULL
etude_agro <- etude_agro[-c(1), ]

summary(etude_agro)
str(etude_agro)
attach(etude_agro)


x11()
zones <- matrix(c(1,2,3,4), ncol = 2)
layout(zones)
layout.show(max(zones))

# Relation Masse et Enracinement
boxplot(Masse~Enracinement)


Nord  <- nrow(subset(etude_agro, subset = Parcelle == 'Nord', select = Parcelle))
Sud   <- nrow(subset(etude_agro, subset = Parcelle == 'Sud', select = Parcelle))
Est   <- nrow(subset(etude_agro, subset = Parcelle == 'Est', select = Parcelle))
Ouest <- nrow(subset(etude_agro, subset = Parcelle == 'Ouest', select = Parcelle))
pie(c(Nord, Sud, Est, Ouest), labels = c("Nord", "Sud", "Est", "Ouest"), init.angle = 90, clockwise = TRUE)


plot(Masse.grains, Nb.grains)


