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
;; File : project.clj
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
;; File use : This file is used to mark dependencies and plugins such as Gorilla-REPL
;; Language : Clojure
;; Requirements : To be run using Gorilla-REPL (if not using Gorilla-REPL comment the mentioned lines out)

(defproject evol_4 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot evol-4.core
  :target-path "target/%s"
  :plugins [[org.clojars.benfb/lein-gorilla "0.3.5"]]
  :profiles {:uberjar {:aot :all}})
