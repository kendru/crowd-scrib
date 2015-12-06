(defproject crowd-scribe "0.1.0-SNAPSHOT"
  :description "Crowd-sourced translation app"
  :url "https://github.com/kendru/crowd-scribe"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.3.1"]
                 [org.danielsz/system "0.2.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [figwheel-sidecar "0.5.0-SNAPSHOT" :scope "test"]
                 [reagent "0.5.1"]
                 [re-frame "0.5.0"]
                 [secretary "1.2.3"]
                 [http-kit "2.1.18"]
                 [environ "1.0.0"]
                 [com.cognitect/transit-clj "0.8.285"]
                 [com.cognitect/transit-cljs "0.8.232"]
                 [crypto-password "0.1.3"]
                 [crypto-random "1.2.0"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [org.postgresql/postgresql "9.4-1206-jdbc41"]]
  :plugins [[lein-cljsbuild "1.1.1"]]
  :main ^:skip-aot crowd-scribe.core
  :source-paths ["src/clj"]
  :target-path "target/%s"
  :profiles {:dev {:plugins [[lein-environ "1.0.0"]]
                   :source-paths ["dev"]
                   :env {:http-port "3000"}}
             :uberjar {:aot :all}}
  :cljsbuild {
    :builds [{
        :source-paths ["src/cljs"]
        :compiler {
          :main "crowd-scribe.core"
          :asset-path "js"
          :output-to "resrouces/public/js/main.js"
          :output-dir "resources/public/js"
          :optimizations :whitespace
          :pretty-print true}}]})
