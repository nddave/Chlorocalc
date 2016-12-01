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
;; File : chartMaker.clj
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
;; File use : This file is used to generate a set of light intensities using the forumla evolved in core.clj
;; Language : Clojure
;; Requirements : To be run using Gorilla-REPL (if not using Gorilla-REPL comment the mentioned lines out)

;; Defining namespace and other requirements
(ns evol-4.chartMaker
  (:require [gorilla-plot.core :as plot])
  (:require [clojure.zip :as zip])
  [:use evol-4.core])

;; Formula is the output of the successful child from the evolution done in core.clj
(def formula
  (model-correlation 1000))

(defn use-formula
  "Takes in a value x and passes it through the evolved formula, to return the output."
  [number]
  (let [form formula
        function (eval (list 'fn '[x] form))]
    (function number)))

;; Target chlorophyll data. The program needs to generate a set of light intensitites that can result in the following chlorophyll data.
(def chloro-need
  [4 3.842857143 3.685714286 3.528571429 3.371428571 3.214285714 3.057142857 2.9 2.742857143 2.585714286 2.428571429 2.271428571 2.114285714 1.8])

(defn random-intensity
  "Generates a bunch of random values of light intensities, and prepares them for evolution!
  The light intensities range from 90w/(m^2) to 220w/(m^2) as that's what the research paper suggests.
  We are taking 14 values since each of them should correspond to chlorophyll generated. Therefore:
  random-intensity[i] = Light Intensity
  chloro-need[i] = chlorophyll generated under light intensity documented at position i of the random-intensity array.
  The same goes for random-intensity[i+1]:chloro-need[i+1], random-intensity[i+2]:chloro-need[i+2],... and so on."
  []
  (vec (repeatedly 14 #(inc (+ (rand-int (- 221 90)) 90)))))

(defn err-item
  "Uses the formula evolves in core.clj to check an error for each light intensity, and how far is it from generating the efficient chlorophyll data we need?"
  [x intensity-list]
  (Math/abs (float (- (use-formula (nth chloro-need x)) (nth intensity-list x)))))

(defn err-total
  "Checks the errors for all light intensities using err-item and returns an average error."
  [intensity-list]
  (/ (reduce + (for [i (range 14)] (err-item i intensity-list)))
     (count intensity-list)))

;; === Mutation Algorithms ===

(defn tweak-a
  "A mutate function that picks a random element and mutates it by adding or subtracting 1"
  [intensity-list]
  (let [tweak-point (rand-int (dec (count intensity-list)))]
    (concat (take tweak-point intensity-list)
            [((rand-nth [inc dec]) (nth intensity-list tweak-point))]
            (drop (inc tweak-point) intensity-list))))

(defn tweak-b
  "Averages and replaces the key that is most near to average with a randon new key"
  [intensity-list]
  (let [dist-from-avg (for [x intensity-list] (- (/ (reduce + intensity-list) (count intensity-list)) x))
        min-dist-from-avg (apply min dist-from-avg)]
    (assoc intensity-list (.indexOf dist-from-avg min-dist-from-avg) (+ (rand-int (- 221 90)) 90))))

;; A list consisting of all mutation algorithms. During evolution, the program will pick one at random each time it needs to mutate.
(def all-tweaks ['tweak-a 'tweak-b])

;; ======

(defn pick
  "Returns best of randomly generated 10 sets of frequencies"
  [collection]
  (let [collection-size (count collection)] ; indexes collection elements
    (nth collection
         (apply min (repeatedly 10 #(rand-int collection-size))))))

(defn tweak-algo
  "Returns a randomly chosen tweak algorithm"
  []
  (get all-tweaks (rand-int (count all-tweaks))))

(defn evolve-lightIntensity-chart
  "Facilitates conditions necessary for evolution.
  collection-size: A collection of sets of light intensities
  tweak-algorithm: Name of the mutation algorithm to be used"
  [collection-size]
  (println "Evolving your light intensities...")
  (loop [generation 0           ; number of generations at snapshot k
         least-err []           ; point of least error
         collection (sort-by err-total (repeatedly collection-size random-intensity))] ; collection of sets of frequencies sorted by err-total
    (if (> generation 100)      ; max generated = 100 (if goal not achieved in 100 trials, the evolution fails)
      (do (println "Failed")
        (plot/list-plot least-err))           ; comment this line if not running on Gorilla-REPL
      (let [best (first collection)           ; when sorted by err-total, first in collection is the closest to target
            err-of-best (err-total best)
            tweak-to (tweak-algo)]            ; err-total of the best set in the collection
        (println "======")
        (println "Set : " generation)
        (println "Misprints : " err-of-best)
        (println "Light intensities : " best)
        (println "Tweak algo : " tweak-to)
        (if (< (float err-of-best) 0.00001)  ; the evolution is asymptotic, hence a error of 0.000001 is considered
          (do (println "Success : " best)
            (plot/list-plot least-err))      ; comment this line if not running on Gorilla-REPL
          (recur
            (inc generation)                 ; increment the number of generations
            (conj least-err err-of-best)     ; join least-error and error-of-best to be used for plotting data
            (sort-by err-total               ; create a new collection with mutated sets of light intensitites
                     (repeatedly collection-size #(eval (read-string (str "(" (str tweak-to) " " (pick collection) ")")))))))))))

(evolve-lightIntensity-chart 1000)
