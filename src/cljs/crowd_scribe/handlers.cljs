(ns crowd-scribe.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [cognitect.transit :as transit]
            [crowd-scribe.state :as state]))

(declare fwd-to-server)

(defn handle-error! [error-key]
  (println "TODO: implement error handling for" error-key))

(register-handler
 :initialize
 (fn [_ _] state/init-data))

(register-handler
 :set-page
 (fn [db [_ page]]
   (assoc db :current-page page)))

(register-handler
 :set-auth-token
 (fn [db [_ token]]
   (assoc db :auth-token token)))

(register-handler
 :add-error
 (fn [db [_ error-key error-msg]]
   (handle-error! error-key)
   (update-in db [:errors] conj error-msg)))


(def writer (transit/writer :json))
(def reader (transit/reader :json))
(def ws (atom nil))

(defn parse [msg]
  (transit/read reader (.-data msg)))

(defn send-to-server [db type data]
   (.send @ws
         (transit/write writer
                        {:type type
                         :token (:auth-token db)
                         :data data})))

(register-handler
 :register-user
 (fn [db [type data]]
   (send-to-server db type data)
   (if (= :sign-up (:current-page db))
     (assoc db :current-page :dashboard)
     db)))


;; Initialize
(reset! ws (js/WebSocket. "ws://localhost:3000/ws"))
(aset @ws "onmessage"
  (fn [data] (dispatch (parse data))))
