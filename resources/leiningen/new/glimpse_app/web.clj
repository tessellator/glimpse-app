(ns {{name}}.web
  (:require [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.server.standalone :refer [serve]]
            [compojure.core :refer [defroutes routes ANY]]
            [compojure.route :as route]
            [glimpse.views :as glimpse]
            [{{name}}.routes :refer [{{name}}-routes]])
  (:gen-class))

(defroutes app-routes
  (route/resources "/")
  (ANY "*" [:as {uri :uri}]
       (when-let [resp (glimpse/not-found uri)]
         (glimpse/render resp)))
  (route/not-found "Not Found"))

(def application (wrap-defaults (routes {{name}}-routes app-routes)
                                site-defaults))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") 8080))]
   (serve application {:port port})))
