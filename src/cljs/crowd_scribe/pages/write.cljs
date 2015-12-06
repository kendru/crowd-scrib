(ns crowd-scribe.pages.write
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :refer [subscribe]]
            [crowd-scribe.forms :refer [bound-input]]))

(defn page []
  (let [form (atom {:recipient ""
                    :body ""
                    :from-language ""
                    :to-language ""})
        user-langs (subscribe [:user-languages])
        target-langs (subscribe [:target-languages])]
    (fn []
      [:div
       [:h2.header.orange-text [:i.material-icons.large "input"] " Write"]
       [:div.row
        [bound-input form [:recipient] "Recipient"
         {:placeholder "recipient@example.com"} 6 "email"]
        [:div.input-field.col.s3
         [:select.browser-default
          {:id "from-language"
           :on-change
           (fn [e]
             (swap! form update-in [:from-language] (constantly (.. e -target -value))))}
          (doall
           (for [lang @user-langs]
             [:option {:value (:id lang)} (:label lang)]))]
         [:label.active {:for "from-language"} "From"]]
        [:div.input-field.col.s3
         [:select.browser-default
          {:id "to-language"
           :on-change
           (fn [e]
             (swap! form update-in [:to-language] (constantly (.. e -target -value))))}
          (doall
           (for [lang @target-langs]
             [:option {:value (:id lang)} (:label lang)]))]
         [:label.active {:for "to-language"} "To"]]]
       [:div.row
        [:div.input-field.col.s12
         [:textarea.materialize-textarea
          {:id "body"
           :on-change
           (fn [e]
             (swap! form update-in [:body] (constantly (.. e -target -value))))}
          (:body @form)]
         [:label.active {:for "body"} "Message"]]]
       [:div.row
        [:div.col.s12]]])))
