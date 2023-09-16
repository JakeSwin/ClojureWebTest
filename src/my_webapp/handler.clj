(ns my-webapp.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [my-webapp.views :as views]
            [my-webapp.components :as comps]
            [ring.adapter.jetty9 :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.refresh :refer [wrap-refresh]]))

(defroutes app-routes
  (GET "/" [] (views/home-page))
  (GET "/test" [] "Hello Testing")
  (POST "/clicked" [] "(comps/response)")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app 
  (wrap-refresh (wrap-defaults #'app-routes site-defaults)))

(defonce server (atom nil))

(defn start! []
  (when-not @server
    (reset! server (jetty/run-jetty
                    #'app
                    {:port 3000
                     :join? false}))))

(defn stop! []
  (when @server
    (jetty/stop-server @server)
    (reset! server nil)))

(comment
  (start!)
  (stop!))

(defn -main [& _]
  (start!))