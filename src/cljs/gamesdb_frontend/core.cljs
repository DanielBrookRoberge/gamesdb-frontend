(ns gamesdb-frontend.core
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [gamesdb-frontend.store :refer [game-rows]]
              [gamesdb-frontend.components :refer [game-list game-input]]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h1 "Game List"]
   [game-list game-rows]
   [game-input]])

(defn about-page []
  [:div [:h2 "About gamesdb-frontend"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn get-library! []
  (go (let [response (<! (http/get "http://localhost:3000/games"))]
        (reset! game-rows (:body response)))))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root)
  (get-library!))
