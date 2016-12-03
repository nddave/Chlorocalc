# Chlorocalc

![alt tag](https://raw.githubusercontent.com/nddave/Chlorocalc/master/Chlorocalc.png)

Using genetic programming and artificial intelligence (regression method), this program creates a formula to model the correlation between light intensity and chlorophyll generation in a plant.

The idea is to take values of light intensity given to a plant (x variable) and chlorophyll generated (y variable) and to evolve a formula that models the two variables. Then use the same formula as an error function to evolve a set of values that correspond to light intensities. This list of light intensitites can then be used by green houses or farmers to automate an efficient plant growth cycle using changing light intensities.

The following plant is considered for the following conditions:

* Plant considered: Soybean plant
* Light intensities: Ranging from 90w/m² to 220w/m²
* Plant environment temperature: Room temperature maintained at 22°C

The paper referenced for this data collection is as cited below:

*J. Elizabeth M. Ballantine and B. J. Forde. "The Effect of Light Intensity and Temperature on Plant Growth and Chloroplast Ultrastructure in Soybean" American Journal of Botany 57.10 (1970): 1150-1159. November 1970. Web. 27 April 2016.*

# Getting started

Chlorocalc is quick and easy to setup. Assuming you have the following downloaded, installed and running.

* Clojure
* Leiningen
* Gorilla REPL

After this, download the Chlorocalc.zip file, change directory to the Chlorocalc folder and run it with the following command:
```
lein gorilla
```
This will start a local server, and will output the local host URL. Please use this URL to access the Gorilla REPL using your favourite web browser. Once in Gorilla REPL, click the top right button and chose 'Load a worksheet', then chose 'core.clj'. This will load the main code page. Once loaded press (shift + enter) in the code box to load all the defined functions. The result output will look as follows:

![alt_tag](https://github.com/nddave/Chlorocalc/blob/master/Load%20Output.png)

Once all the functions have been loaded, please enter the following command (by pressing shift + enter) to begin mutation and evolution process. The goal of this program is to use AI to generate a formula that models light intensity and chlorophyll data in a given plant.
```
(model-correlation 1000)
```
This will begin the mutation and evolution process for creating the formula that models the light intensity and chlorophyll data. The '1000' in the command is the initial population size. The larger the population size, the better the mutation, crossover and evolution process occurs. Once the evolution process starts, it will look like the following:

![alt_tag](https://raw.githubusercontent.com/nddave/Chlorocalc/master/Evolution.png)

Here, the AI starts by creating a random fomula and keeps tweaking it until an error-less formula is reached. These 'tweaks' happen through a systematic process of evolution and crossover defined in the functions in the code. Every generation is a new and improved formula with lesser error compared to the previous generation. At the end, the program will complete when it figures out an error free formula to model the correlation between light intensity and chlorophyll data. Here is a screenshot of when the program completed its execution at Generation 24.

![alt_tag](https://raw.githubusercontent.com/nddave/Chlorocalc/master/Generation%2024.png)

The end formula may be hard for a human eye to interpret, however, if ran as a Clojure code, it will result and output that precisely resonates and models the data provided by the research paper. Please check [Chlorocalc Running.pdf](https://github.com/nddave/Chlorocalc/blob/master/Chlorocalc%20Running.pdf) to find a screenshot of the running algorithm. You can find all code files [here](https://github.com/nddave/Chlorocalc/tree/master/src/evol_4).

# License information ![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)

This work is licensed under a [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/). 

Program is created by [Nirman Dave](http://www.nirmandave.com) as a form of assignment for *Evolutionary Computation CS284* course at *Hampshire College, Amherst MA* under *Professor Lee Spector*.

