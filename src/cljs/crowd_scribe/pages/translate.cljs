(ns crowd-scribe.pages.translate
  (:require [reagent.core :as reagent :refer [atom]]))

(defn page []
  (fn []
    [:div
     [:h1.header.orange-text [:i.material-icons.large "translate"] " Translate"]]))
