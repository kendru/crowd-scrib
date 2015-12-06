(ns crowd-scribe.pages.sign-up
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe dispatch]]
            [crowd-scribe.forms :refer [bound-input]]))

(defn submit-form [form]
  (dispatch [:register-user @form]))

(defn toggle-lang [form id]
  (if (some #{id} (:languages @form))
    (swap! form update-in [:languages]
           (fn [langs] (remove #(= id %) langs)))
    (swap! form update-in [:languages] conj id)))

(defn lang-select [form]
  (let [languages (subscribe [:all-languages])]
    (fn []
      [:div.collection
       (doall
        (for [{:keys [id label]} @languages]
          ^{:key (str "lang-" id)}
          [:a {:class-name (str "collection-item" (when (some #{id} (:languages @form))
                                                    " active"))
                               :on-click #(toggle-lang form id)}
           label]))])))

(defn can-translate [form]
  [:p
   [:input {:type "checkbox"
            :id "can-translate"
            :checked (:can-translate? @form)
            :on-change #(swap! form update-in [:can-translate?] not)}]
   [:label {:for "can-translate"} "I can translate"]])

(defn page []
  (let [form (atom {:name {:first ""
                           :last ""}
                    :email ""
                    :password ""
                    :languages []
                    :can-translate? false})]
    (fn []
      [:div
       [:h2.header.orange-text "Sign Up"]
       [:div.row
        [:div.col.s6
         [:div.row
          [bound-input form [:name :first] "First Name"
           {:placeholder "John"} 6 "account_circle"]
          [bound-input form [:name :last] "Last Name"
           {:placeholder "Doe"} 6]]
         [:div.row
          [bound-input form [:email] "Email"
           {:placeholder "john.doe@example.com"} 12 "email"]]
         [:div.row
          [bound-input form [:password] "Password"
           {:type "password"} 12 "lock"]]]
        [:div.col.s6
         [:div.row
          [:div.input-field.col.s12
           [lang-select form]
           [:label {:class-name "active"
                    :for "languages"} "Proficient Languages"]]
          [:div.input-field.col.s12
           [can-translate form]]]]]
       [:div.row.center
        [:a.btn-large.light-blue {:on-click #(submit-form form)} "Submit"]]])))
