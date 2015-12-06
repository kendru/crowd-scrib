(ns crowd-scribe.state
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-sub]]))

(def init-data
  {:current-page :home
   :user nil
   :auth-token nil
   :languages [{:id "en" :label "English"}
               {:id "es" :label "Spanish"}
               {:id "de" :label "German"}
               {:id "fr" :label "French"}]
   :errors []})

(register-sub
 :current-page
 (fn [db _]
   (reaction (:current-page @db))))

(register-sub
 :user
 (fn [db _]
   (reaction (:user @db))))

(register-sub
 :auth-token
 (fn [db _]
   (reaction (:auth-token @db))))

(register-sub
 :all-languages
 (fn [db _]
   (reaction (:languages @db))))

(register-sub
 :user-languages
 (fn [db _]
   (reaction
    (let [lang-ids (into #{} (get-in @db [:user :languages]))]
      (filter #(contains? lang-ids (:id %)) (:languages @db))))))

(register-sub
 :target-languages
 (fn [db _]
   (reaction
    (let [user-lang-ids (into #{} (get-in @db [:user :languages]))]
      (println "user langs" @db)
      (remove #(contains? user-lang-ids (:id %)) (:languages @db))))))


