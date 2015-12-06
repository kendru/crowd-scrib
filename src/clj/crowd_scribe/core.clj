(ns crowd-scribe.core
  (:require [com.stuartsierra.component :as component]
            [crowd-scribe.systems :refer [prod-system]])
  (:gen-class))

(defn -main
  [& args]
  (alter-var-root #'prod-system component/start))
