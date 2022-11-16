###############################################################################
#                                                                             #
#   Bastin Thomas                                                             #
#   2301                                                                      #
#   III.1. Bière et Petits Maux                                               #
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
Biere <- read.csv(file = "bieres_petits_maux.csv", sep = ";", na.strings = "NA", header = TRUE, stringsAsFactors = TRUE, encoding="UTF-8")
summary(Biere)
str(Biere)


# L'Administration de la Santé Publique de Bidendumie a recensé le nombre de patients
# atteints de l'une des 4 maladies bénignes les plus fréquentes et ayant consommé l'une des 3
# bières locales les plus répandues. Elle a mesuré un coefficient biochimique représentatif sur 6
# patients (si possible) choisis aléatoirement et a obtenu (chaque ligne correspond à une maladie) : 



# Est-il possible d'interpréter de tels résultats ? Ils se trouvent dans le fichier
# bieres_petits_maux.csv. 

biere <- Biere$biere
maux <- Biere$maux
CBio <- Biere$CBio007.69


with(Biere, interaction.plot(biere, maux, CBio))
# Les droites sont en globalité pas parrallèle -> Facteur d'interraction probable.

with(Biere, interaction.plot(maux, biere, CBio))
# Les droites sont peut interprétable (ni parrallèle ni en interraction...)



# Model maux*biere Avec Interraction
model2 <- lm(CBio ~ maux * biere)
model2$coefficients
anova(model2)

# On remarque que selon le test d'anova, biere pValue: 0.05326 -> Il devient compliqué de choisir entre un rejet ou non, idéalement il faudrait plus de donnée
#                                        maux  pValue: <0.05 -> h0 rejeter (Valeur Significative) donc maux a un impacte sur Cbio
#                                   maux:biere pValue: <0.05 -> h0 rejeter(Valeur Significative) donc les maux en interraction avec les bières on un impacte sur le CBio
# Valeur non exploitable



# Model maux+biere Sans Interraction
model4 <- lm(CBio ~ biere + maux)
model4$coefficients
anova(model4)
# On est obliger d'accepter h0 pour biere, la bière n'influe pas significativement sur le CBio.
# Signifique les maux influes significativement sur le CBio


# Malgré tout de manière général ses valeurs sont relativement peut fiable. Les pvalues a 0.05 n'aidant pas.