;; This is my canvas. This is my art.
;;
;; Assignment information
;; ---
;; Class : Evolutionary Computing
;; Assignment : Make a final assignment that uses evolutionary computation to its best potential.
;; Student : Nirman Dave
;;
;; Program information
;; ---
;; Name : Evol_4
;; File : core.clj
;; Version : 1.0
;;
;; Description : The idea is to take values of light intensity given to a plant (x variable) and chlorophyll generated (y variable) and to
;;               evolve a formula that models the two variables. Then use the same formula as an error function to evolve a set of values
;;               that correspond to light intensities. This list of light intensitites can then be used by green houses or farmers to automate
;;               an efficient plant growth cycle using changing light intensities.
;;
;;               The following plant is considered for the following conditions:
;;               Plant considered: Soybean plant
;;               Light intensities: Ranging from 90w/(m^2) to 220w/(m^2)
;;               Plant environment temperature: Room temperature maintained at 22*C
;;
;;               The paper referenced for this data collection is as cited below:
;;               J. Elizabeth M. Ballantine and B. J. Forde. "The Effect of Light Intensity and Temperature on Plant Growth and Chloroplast
;;               Ultrastructure in Soybean" American Journal of Botany 57.10 (1970): 1150-1159. November 1970. Web. 27 April 2016.
;;
;; File use : This file is used to take the target data from the research paper, and evolve a formula that models the correlation.
;; Language : Clojure
;; Requirements : To be run using Gorilla-REPL (if not using Gorilla-REPL comment the mentioned lines out)

;; Defining namespace and other requirements
(ns evol-4.core
  (:require [gorilla-plot.core :as plot])
  (:require [clojure.zip :as zip]))

;; Target data aquired from the Ballantine and Forde's research paper (1970, p.1157)
;; The data is represented in the fashion : [light intensity(w/(m^2)) chlorophyll growth(mg/g)]
(def target-data
  (list [90 4.0]
        [100 3.842857143]
        [110	3.685714286]
        [120	3.528571429]
        [130	3.371428571]
        [140	3.214285714]
        [150	3.057142857]
        [160	2.9]
        [170	2.742857143]
        [180	2.585714286]
        [190	2.428571429]
        [200	2.271428571]
        [210	2.114285714]
        [220	1.8]))

;; Lists the potential functions that the AI can use during evolution
(def function-table (zipmap '(+ - * pd)
                            '(2 2 2 2)))

(defn pd
  "Also known as protected division. Avoids dividing by zero."
  [num denom]
  (if (zero? denom)
    0
    (/ num denom)))

(defn absolute
  "A funtion that returns the absolute value of a number n."
  [number]
  (max number (- number)))

(defn todo-function
  "A function that returns a randomly selected function from the function-table."
  []
  (rand-nth (keys function-table)))

(defn todo-terminal
  "Returns a random number from the todo-code."
  []
  (rand-nth (list 'x (- (rand 10) 5))))

(defn todo-code
  "Returns a randomized code that will be evaled and mutated."
  [number]
  (if (or (zero? number)
          (zero? (rand-int 2)))
    (todo-terminal)
    (let [f (todo-function)]
      (cons f (repeatedly (get function-table f)
                          #(todo-code (dec number)))))))

(defn misprint
  "Basically just an error function. Looks how far the computer generated
  result is from the actual result."
  [correlation]
  (let [value-function (eval (list 'fn '[x] correlation))]
    (reduce + (map (fn [[x y]]
                     (absolute
                       (- (value-function x) y)))
                   target-data))))

(defn todo-codePopulation
  "Returns the size of the todo-code."
  [c]
  (if (seq? c)
    (count (flatten c))
    1))

(defn at-index
  "Defines the index to tweak."
  [tree point-index]
  (let [index (mod (absolute point-index) (todo-codePopulation tree))
        zipper (zip/seq-zip tree)]
    (loop [z zipper i index]
      (if (zero? i)
        (zip/node z)
        (if (seq? (zip/node z))
          (recur (zip/next (zip/next z)) (dec i))
          (recur (zip/next z) (dec i)))))))

(defn insert-at-index
  "Inserts an item at a given index."
  [tree point-index new-subtree]
  (let [index (mod (absolute point-index) (todo-codePopulation tree))
        zipper (zip/seq-zip tree)]
    (loop [z zipper i index]
      (if (zero? i)
        (zip/root (zip/replace z new-subtree))
        (if (seq? (zip/node z))
          (recur (zip/next (zip/next z)) (dec i))
          (recur (zip/next z) (dec i)))))))


(defn mutate
  "First mutation algorithm that inserts a code at point i."
  [i]
  (insert-at-index i
                   (rand-int (todo-codePopulation i))
                   (todo-code 2)))

(defn crossover
  "Crossover takes two codes and creates a new one that is the combination of both."
  [i j]
  (insert-at-index i
                   (rand-int (todo-codePopulation i))
                   (at-index j (rand-int (todo-codePopulation j)))))

(defn sort-by-misprint
  "Sorts the collection of items by misprint level."
  [population]
  (vec (map second
            (sort (fn [[err1 ind1] [err2 ind2]] (< err1 err2))
                  (map #(vector (misprint %) %) population)))))

(defn select-x
  "Selects a particular code from the population size and returns that."
  [population tournament-size]
  (let [size (count population)]
    (nth population
         (apply min (repeatedly tournament-size #(rand-int size))))))

(defn goabc
  "Takes an input t and returns it as is. This is to avoid converting
  a computer generated code into a string."
  [t]
  t)

(defn model-correlation
  "Uses evolutionary computing to model the correlation between light intensity and chlorophyll growth in the
  Soybean plant, by creating a formula that returns value y for value x."
  [popsize]
  (println "======================")
  (println "")
  (println "Evolving a formula that models the correlation between the light intensity and chlorophyll")
  (loop [generation 0
         population (sort-by-misprint (repeatedly popsize #(todo-code 2)))]
    (let [best (first population)
          best-error (misprint best)]
      (println "======================")
      (println "Generation:" generation)
      (println "Best error:" best-error)
      (println "Best program:" best)
      (println "     Median error:" (misprint (nth population
                                                   (int (/ popsize 2)))))
      (println "     Average program size:"
               (float (/ (reduce + (map count (map flatten population)))
                         (count population))))
      (if (< best-error 0.47)        ; Over a period of different trials it was observed that 0.46 was the best error reachable. Which is acceptable amount of uncertainty when talking about light intensity values that range in hundreds.
        (do (println "Success:" best)
          (goabc best))
        (recur
          (inc generation)
          (sort-by-misprint
            (concat
              (repeatedly (* 0.1875 popsize) #(mutate (select-x population 7)))
              (repeatedly (* 0.75 popsize) #(crossover (select-x population 7)
                                                       (select-x population 7)))
              (repeatedly (* 0.0625 popsize) #(select-x population 7)))))))))
