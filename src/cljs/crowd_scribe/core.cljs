(ns crowd-scribe.core
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [dispatch-sync]]
            [crowd-scribe.layout :refer [app-shell]]
            [crowd-scribe.state :as state]
            [crowd-scribe.routes :refer [init-routes]]
            [crowd-scribe.handlers]
            [crowd-scribe.state]))

(when js/goog.DEBUG
  (enable-console-print!))

(defn render-root []
  (reagent/render-component [app-shell]
    (.getElementById js/document "app")))

(do (init-routes)
    (render-root))

(defn ^:export init []
  (dispatch-sync [:initialize]))

