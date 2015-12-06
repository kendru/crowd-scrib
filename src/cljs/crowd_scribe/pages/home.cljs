(ns crowd-scribe.pages.home
  (:require [reagent.core :as reagent :refer [atom]]))

(defn page []
  (fn []
    [:div
     [:h1.header.center.orange-text "Croudsourced translations"]
     [:div.row.center
      [:h5.header.col.s12.light "Easily send letters to speakers of other languages, or lend a hand by translating"]]
     [:div.row.center
      [:a.btn-large.waves-effect.waves-light.light-blue {:href "#/sign-up"} "Sign me up!"]]]))
