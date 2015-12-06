(require '[figwheel-sidecar.repl-api :as ra]
         '[com.stuartsierra.component :as component])

;; TODO: Move this into a component in user.cljs

(def figwheel-config
  {:figwheel-options {}
   :build-ids ["dev"]
   :all-builds
   [{:id "dev"
     :figwheel true
     :source-paths ["src/cljs"]
     :compiler {:main "crowd-scribe.core"
                :asset-path "js"
                :output-to "resources/public/js/main.js"
                :output-dir "resources/public/js"
                :verbose true}}]})

(defrecord Figwheel []
  component/Lifecycle
  (start [config]
    (ra/start-figwheel! config)
    config)
  (stop [config]
    (ra/stop-figwheel!)
    config))

(def ui-system
  (atom
   (component/system-map
    :figwheel (map->Figwheel figwheel-config))))

(defn start-ui []
  (swap! ui-system component/start))

(defn stop-ui []
  (swap! ui-system component/stop))

(defn reload-ui []
  (stop-ui)
  (start-ui))

(defn repl-ui []
  (ra/cljs-repl))
