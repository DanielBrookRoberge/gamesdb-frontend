(ns gamesdb-frontend.store
  (:require [reagent.core :as reagent :refer [atom]]))

(def game-rows
  (atom
   [{:title "The Legend of Zelda" :status "campaign" :platform "NES Classic"}
    {:title "Super Metroid" :status "finished" :platform "SNES Classic"}]))

(def title-value (atom ""))

(def status-value (atom ""))

(def platform-value (atom ""))
