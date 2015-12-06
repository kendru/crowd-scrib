(ns user
  (:require [reloaded.repl :refer [system init start stop go reset]]
            [environ.core :refer [env]]
            [crowd-scribe.systems :refer [dev-system]]))

(reloaded.repl/set-init! dev-system)

