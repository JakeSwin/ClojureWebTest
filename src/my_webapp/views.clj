(ns my-webapp.views
  (:require [hiccup.page :as page]
            [ring.util.anti-forgery :as util]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Hello " title)]
   (page/include-css "/css/styles.css")
   (page/include-js "https://unpkg.com/htmx.org@1.9.5")])

(defn home-page
  []
  (page/html5
   (gen-page-head "Home")
   [:h1 "Home"]
   [:p "Testing webapp"]
   [:button {:hx-post "/clicked" :hx-swap "outerHTML"} "Click Me"]))