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

ACP <- PCA(etude_agro, scale.unit = TRUE, quali.sup = c(5:10,12), quanti.sup = c(11))

ACP$eig

barplot(ACP$eig[,2], names=paste("d",1:nrow(ACP$eig)))

ACP$var$cos2

ACP$var$contrib

plot(ACP, cex=0.65, invisible="quali", select="cos2 10", unselect=0.5)

plot(ACP, cex=0.65, habillage = 1, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 2, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 3, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 4, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 5, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 6, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 7, col.hab = c("red", "blue", "yellow", "green"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 8, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 9, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 10, col.hab = c("red", "blue", "yellow", "green"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 11, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")
plot(ACP, cex=0.65, habillage = 12, col.hab = c("red", "blue"), invisible="quali", select="cos2 0.97")

dim <- dimdesc(ACP)

dim$Dim.1$quanti
dim$Dim.2$quanti

dim$Dim.1$quali
dim$Dim.2$quali

ACP <- PCA(etude_agro, scale.unit = TRUE, quali.sup = c(5:10,12), quanti.sup = c(11), axes = c(1,3))

pairs(etude_agro[, 1:4])

cor(etude_agro[, 1:4])
#On retrouve la forte corrélation entre les variables quantitative



#Toutes les variables sont corrélées de même manière avec F1 et F2

# F1(x): Difficilement Explicable au vus du jeu de donnée

# F2(y): Difficilement Explicable au vus du jeu de donnée
