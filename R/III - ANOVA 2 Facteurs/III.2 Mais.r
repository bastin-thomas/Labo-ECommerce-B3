###############################################################################
#                                                                             #
#   Bastin Thomas                                                             #
#   2301                                                                      #
#   III.2 Mais                                                                #
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

etude_agro$Censure.droite <- NULL
etude_agro$Nb.jours.attaque <-NULL
etude_agro <- etude_agro[-c(1), ]

summary(etude_agro)
str(etude_agro)

# Sur base du dataset du maïs évoqué ci-dessus, étudier l'éventuelle dépendnace de la masse
# des grains par rapport à la couleur et l'enracinement. 

masse <- etude_agro$Masse
couleur <- etude_agro$Couleur
enracinement <- etude_agro$Enracinement


with(etude_agro, interaction.plot(couleur, enracinement, masse))
with(etude_agro, interaction.plot(enracinement, couleur, masse))



model4 <- lm(masse ~ couleur + enracinement)
model4$coefficients
anova(model4)

model2 <- lm(masse ~ enracinement * couleur)
model2$coefficients
anova(model2)

# La masse ne s'explique clairement pas en fonction de l'enracinement, ni la couleur



