(ns my-webapp.components
  (:require [hiccup.core :as h]))

(defn response
  []
  (str (h/html [:div.foo [:h2 "my text"]])))\
