(defproject crowd-scribe "0.1.0-SNAPSHOT"
  :description "Crowd-sourced translation app"
  :url "https://github.com/kendru/crowd-scribe"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [org.clojure/clojurescript "1.7.170"]
                 [figwheel-sidecar "0.5.0-SNAPSHOT" :scope "test"]]
  :main ^:skip-aot crowd-scribe.core
  :source-paths ["src/clj"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
