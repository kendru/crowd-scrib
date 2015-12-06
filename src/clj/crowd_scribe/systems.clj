(ns crowd-scribe.systems
  (:require [com.stuartsierra.component :as component]
            [system.components
             [http-kit :refer [new-web-server]]
             [postgres :refer [new-postgres-database]]]
            [environ.core :refer [env]]
            [crowd-scribe.handler :refer [ws-handler]]
            [crowd-scribe.users]))

(defn get-db-spec []
  {:classname "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname ""
   :user (get env :db-user "crowd_scribe")
   :password (get env :db-password "s3cr3t")
   :host (get env :db-host "127.0.0.1")})

(defn dev-system []
  (component/system-map
;   :postgres-db (new-postgres-database (get-db-spec))
   :web-server (new-web-server (Integer/parseInt (get env :http-port)) ws-handler)))

(defn prod-system []
  :postgres-db (new-postgres-database (get-db-spec))
  :web-server (new-web-server (Integer/parseInt (get env :http-port)) ws-handler))
