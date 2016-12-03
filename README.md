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

```clojure
nil
#'evol-4.core/target-data
#'evol-4.core/function-table
#'evol-4.core/pd
#'evol-4.core/absolute
#'evol-4.core/todo-function
#'evol-4.core/todo-terminal
#'evol-4.core/todo-code
#'evol-4.core/misprint
#'evol-4.core/todo-codePopulation
#'evol-4.core/at-index
#'evol-4.core/insert-at-index
#'evol-4.core/mutate
#'evol-4.core/crossover
#'evol-4.core/sort-by-misprint
#'evol-4.core/select-x
#'evol-4.core/goabc
#'evol-4.core/model-correlation
```

Once all the functions have been loaded, please enter the following command (by pressing shift + enter) to begin mutation and evolution process. The goal of this program is to use AI to generate a formula that models light intensity and chlorophyll data in a given plant.
```
(model-correlation 1000)
```
This will begin the mutation and evolution process for creating the formula that models the light intensity and chlorophyll data. The '1000' in the command is the initial population size. The larger the population size, the better the mutation, crossover and evolution process occurs. Once the evolution process starts, it will look like the following:

```clojure
Evolving a formula that models the correlation between the light intensity and chlorophyll
======================
Generation: 0
Best error: 7.8571428569999995
Best program: 3.015192449001505
     Median error: 2088.6612626802803
     Average program size: 2.467
======================
Generation: 1
Best error: 7.8571428569999995
Best program: 3.0275101856351476
     Median error: 36.366364916073195
     Average program size: 2.117882
======================
Generation: 2
Best error: 7.8571428569999995
Best program: 3.000339323884951
     Median error: 10.322616432095245
     Average program size: 0.91608393
======================
Generation: 3
Best error: 7.765998386121409
Best program: (+ 2.875884665193702 (pd 4.001292147861381 x))
     Median error: 7.915283046752327
     Average program size: 0.43256745
======================
Generation: 4
Best error: 7.765998386121409
Best program: (+ 2.875884665193702 (pd 4.001292147861381 x))
     Median error: 7.8571428569999995
     Average program size: 0.46653345
======================
Generation: 5
Best error: 7.765998386121409
Best program: (+ 3.015192449001505 (pd 4.001292147861381 x))
     Median error: 7.8571428569999995
     Average program size: 0.7052947
```

Here, the AI starts by creating a random fomula and keeps tweaking it until an error-less formula is reached. These 'tweaks' happen through a systematic process of evolution and crossover defined in the functions in the code. Every generation is a new and improved formula with lesser error compared to the previous generation. At the end, the program will complete when it figures out an error free formula to model the correlation between light intensity and chlorophyll data. Here is a output of when the program completed its execution at Generation 30.

```clojure
======================
Generation: 30
Best error: 0.27690451158203055
Best program: (+ (+ (+ (+ (+ 2.875884665193702 (pd 3.000339323884951 (- 3.015192449001505 (* (+ 3.015192449001505 3.000339323884951) -4.181762000701954)))) (pd (+ 3.015192449001505 (pd (* x 1.2919237652450901) x)) x)) (pd (+ (+ (+ 2.875884665193702 (pd 3.000339323884951 x)) (pd (+ 2.900964782213359 3.015192449001505) x)) (+ (+ 2.900964782213359 (pd -0.7289892936958609 x)) (pd (- 4.001292147861381 (pd 1.2973174445924283 (pd 3.9408910119251033 (* (- x -0.8026046971279612) 0.11445689242808399)))) (pd 1.11473710383585 (+ (+ (+ (+ 2.875884665193702 (pd 3.015192449001505 x)) (pd (+ x 4.001292147861381) x)) (pd 0.29824581318035115 x)) (pd (+ 3.015192449001505 (pd x (* (* x -2.230139887857084) (pd x -2.8760242061290042)))) x)))))) (+ 2.875884665193702 (+ (+ 2.875884665193702 (pd 3.015192449001505 (pd x 4.916880596124386))) (+ 2.875884665193702 (pd (pd (pd (* 4.267387477619579 (+ x (+ 2.875884665193702 (pd (+ (+ 2.951474049669933 x) (pd 4.001292147861381 x)) x)))) x) x) x)))))) (pd 3.015192449001505 x)) (pd (+ 2.875884665193702 (pd x x)) x))
     Median error: 4.814868768989467
     Average program size: 131.62038
======================

Success: (+ (+ (+ (+ (+ 2.875884665193702 (pd 3.000339323884951 (- 3.015192449001505 (* (+ 3.015192449001505 3.000339323884951) -4.181762000701954)))) (pd (+ 3.015192449001505 (pd (* x 1.2919237652450901) x)) x)) (pd (+ (+ (+ 2.875884665193702 (pd 3.000339323884951 x)) (pd (+ 2.900964782213359 3.015192449001505) x)) (+ (+ 2.900964782213359 (pd -0.7289892936958609 x)) (pd (- 4.001292147861381 (pd 1.2973174445924283 (pd 3.9408910119251033 (* (- x -0.8026046971279612) 0.11445689242808399)))) (pd 1.11473710383585 (+ (+ (+ (+ 2.875884665193702 (pd 3.015192449001505 x)) (pd (+ x 4.001292147861381) x)) (pd 0.29824581318035115 x)) (pd (+ 3.015192449001505 (pd x (* (* x -2.230139887857084) (pd x -2.8760242061290042)))) x)))))) (+ 2.875884665193702 (+ (+ 2.875884665193702 (pd 3.015192449001505 (pd x 4.916880596124386))) (+ 2.875884665193702 (pd (pd (pd (* 4.267387477619579 (+ x (+ 2.875884665193702 (pd (+ (+ 2.951474049669933 x) (pd 4.001292147861381 x)) x)))) x) x) x)))))) (pd 3.015192449001505 x)) (pd (+ 2.875884665193702 (pd x x)) x))
(+ (+ (+ (+ (+ 2.875884665193702 (pd 3.000339323884951 (- 3.015192449001505 (* (+ 3.015192449001505 3.000339323884951) -4.181762000701954)))) (pd (+ 3.015192449001505 (pd (* x 1.2919237652450901) x)) x)) (pd (+ (+ (+ 2.875884665193702 (pd 3.000339323884951 x)) (pd (+ 2.900964782213359 3.015192449001505) x)) (+ (+ 2.900964782213359 (pd -0.7289892936958609 x)) (pd (- 4.001292147861381 (pd 1.2973174445924283 (pd 3.9408910119251033 (* (- x -0.8026046971279612) 0.11445689242808399)))) (pd 1.11473710383585 (+ (+ (+ (+ 2.875884665193702 (pd 3.015192449001505 x)) (pd (+ x 4.001292147861381) x)) (pd 0.29824581318035115 x)) (pd (+ 3.015192449001505 (pd x (* (* x -2.230139887857084) (pd x -2.8760242061290042)))) x)))))) (+ 2.875884665193702 (+ (+ 2.875884665193702 (pd 3.015192449001505 (pd x 4.916880596124386))) (+ 2.875884665193702 (pd (pd (pd (* 4.267387477619579 (+ x (+ 2.875884665193702 (pd (+ (+ 2.951474049669933 x) (pd 4.001292147861381 x)) x)))) x) x) x)))))) (pd 3.015192449001505 x)) (pd (+ 2.875884665193702 (pd x x)) x))
```

The end formula may be hard for a human eye to interpret, however, if ran as a Clojure code, it will result and output that precisely resonates and models the data provided by the research paper. Please check [Chlorocalc Running.pdf](https://github.com/nddave/Chlorocalc/blob/master/Chlorocalc%20Running.pdf) to find a screenshot of the running algorithm. You can find all code files [here](https://github.com/nddave/Chlorocalc/tree/master/src/evol_4).

# License information ![License: CC BY 4.0](https://img.shields.io/badge/License-CC%20BY%204.0-lightgrey.svg)

This work is licensed under a [Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/). 

Program is created by [Nirman Dave](http://www.nirmandave.com) as a form of assignment for *Evolutionary Computation CS284* course at *Hampshire College, Amherst MA* under *Professor Lee Spector*.

