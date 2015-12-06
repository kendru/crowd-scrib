(ns crowd-scribe.layout
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe]]
            [crowd-scribe.pages.home :as home]
            [crowd-scribe.pages.sign-up :as sign-up]
            [crowd-scribe.pages.dashboard :as dashboard]
            [crowd-scribe.pages.write :as write]
            [crowd-scribe.pages.translate :as translate]
            [crowd-scribe.data-static :as static]))

(defn not-found []
  (fn [] [:h2 "Page not found"]))

(defmulti page identity)
(defmethod page :default [] [not-found])
(defmethod page :home [] [home/page])
(defmethod page :sign-up [] [sign-up/page])
(defmethod page :dashboard [] [dashboard/page])
(defmethod page :write [] [write/page])
(defmethod page :translate [] [translate/page])

(defn logo []
  (fn []
    [:a.logo {:href "#/"
              :class-name "brand-logo"}
     "CrowdScribe"]))

(defn nav-link [{:keys [href label]}]
  [:li
   [:a {:href href} label]])

(defn nav-links []
  (let [auth-token (subscribe [:auth-token])]
    (fn []
      [:ul.right
       (doall
        (for [link (if @auth-token
                     static/logged-in-links
                     static/logged-out-links)]
          ^{:key link} [nav-link link]))])))

(defn header []
  [:header.app-header
   [:nav.light-blue {:role "navigation"}
    [:div.nav-wrapper.container
     [logo]
     [nav-links]]]])

(defn app-shell []
  (let [current-page (subscribe [:current-page])]
    (fn []
      [:div.app-shell
       [header]
       [:div.app-contwnt.container
        (page @current-page)]])))
