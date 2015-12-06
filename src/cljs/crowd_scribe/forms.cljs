(ns crowd-scribe.forms)

(defn bound-input
  ([form path label] (bound-input form path label {} nil nil))
  ([form path label params] (bound-input form path label params nil nil))
  ([form path label params columns] (bound-input form path label params columns nil))
  ([form path label params columns icon]
   (let [value (get-in @form path)]
     [:div {:class-name (str "input-field col s" (or columns 6))}
      (when icon
        [:i.material-icons.prefix icon])
      [:input (merge {:class-name "validate"
                      :type "text"
                      :value value
                      :on-change
                      (fn [e]
                        (swap! form update-in path (constantly (.. e -target -value))))}
                     params)]
      [:label.active {:for (:id params)} label]])))
