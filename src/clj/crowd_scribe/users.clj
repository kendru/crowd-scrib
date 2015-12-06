(ns crowd-scribe.users
  (:require [org.httpkit.server :refer [send!]]
            [crypto.password.bcrypt :as password]
            [crypto.random :as random]
            [crowd-scribe.handler :refer [dispatch serialize]]))

(def by-email (atom nil))
(def email-by-token (atom {}))

(defmethod dispatch :register-user
  [ch {:keys [data]}]
  (let [{:keys [email] :as user} data]
    (if (get by-email email)
      (send! ch (serialize [:add-error :duplicate-user-email email]))
      (do (swap! by-email assoc email
                 (update-in user [:password] password/encrypt))
          (dispatch ch {:type :login-user
                        :data {:email email
                               :password (:password user)}})))))

(defmethod dispatch :login-user
  [ch {:keys [data]}]
  (let [{:keys [email password]} data]
    (if-let [user (get @by-email email)]
      (if (password/check password (:password user))
        (let [token (random/base64 32)]
          (swap! email-by-token assoc token email)
          (send! ch (serialize [:set-auth-token token])))
        (send! ch (serialize [:add-error :invalid-credentials "Login failed"])))
      (send! ch (serialize [:add-error :invalid-credentials "Login failed"])))))

(defn verify-auth-token [email token]
  (= token (get @email-by-token token)))

(defn initialize! []
  (swap! by-email {}))
