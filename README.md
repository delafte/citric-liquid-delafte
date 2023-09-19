# 99.7% Citric Liquid

## About

`99.7% Citric Liquid` is a simplified clone of the renowned game, `100% Orange Juice`. Its main
purpose is to serve as an educational tool, teaching foundational programming concepts.

📢 **Note**: This project is purely educational and will not be used for any commercial purposes.

---

## T1 - 22/09/23

First, I started with the implementation of different tests. For that I used the testing framework
MUnit and designed the tests for the characteristics (variables and/or values) and methods that each class had to have. 
With that in mind, I started with the concept of the board and the players. For that, I decided to create a trait for the units and use the one of the panels.

Considering that all the panels have two functionalities in common, I implemented the abstract panel class, so that all the different classes
of panel could extend from it, and consequently, make the methods inherited.

We have 5 different panels, all with different effects, so each one has its own class in which I defined their special functionality
(except for the HomePanel and EncounterPanel, due to their complexity they are going to be developed later).

Subsequently, I designed the Board class. Keeping on mind that this was a collection of panels, in this phase the decision of which type
of organization they were going to have was made. For the moment, it consists of a simple square, like in the table game "Monopoly". 

Afterward, the implementation of the different types of units was made. I divided it into the WildUnits and the players.

Each type of Wild Unit has its own class, with their values defined. There wasn't a previous abstract class defined because 
in the future their special functionalities will be addressed, and I'll see if they have some in common or not.

The class for the players has the necessary parameters for save the information of each character and the method for playing
the dice, so that it can be used when the situation requires.

Finally, for the time being, the norm was implemented as an attribute in the PlayerCharacter Class. When we address its functionalities
then it will be created as a method or class or other option, depending on the complexity.

This project is licensed under the [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/).

---