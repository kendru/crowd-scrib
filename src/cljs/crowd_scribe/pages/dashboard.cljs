(ns crowd-scribe.pages.dashboard
  (:require [reagent.core :as reagent :refer [atom]]))

(defn page []
  (fn []
    [:div
     [:h1.header.orange-text "Dashboard"]
     [:a.btn-large.waves-effect.waves-light.light-blue {:href "#/write"}
      [:i.material-icons.left "input"] "Write a letter"]
     [:a.btn-large.waves-effect.waves-light.light-blue {:href "#/translate"}
      [:i.material-icons.left "translate"] "Translate"]]))
