(ns gamesdb-frontend.components
  (:require [reagent.core :as reagent :refer [atom]]
            [gamesdb-frontend.store :refer [game-rows title-value status-value platform-value]]))

(defn game-item [{:keys [:title :status :platform]}]
  [:li {:class ["game-item"]}
   [:b title] " " [:i status] " " platform])

(defn game-list [games]
  [:ol {:class ["game-list"]}
   (for [game @games] ^{:key (:title game)} [game-item game])])

(defn text-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn labelled-text [label value]
  [:div {:class ["input-label"]} label [text-input value]])

(defn game-input []
  [:div
   [labelled-text "Title: " title-value]
   [labelled-text "Status: " status-value]
   [labelled-text "Platform: " platform-value]
   [:button {:on-click #(swap! game-rows conj {:title @title-value :status @status-value :platform @platform-value})} "Add"]])
