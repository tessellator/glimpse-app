(ns {{name}}.dev
  (:require [ring.server.standalone :refer [serve]]
            [glimpse.views :as glimpse]
            [{{name}}.routes :refer [{{name}}-routes]]
            [{{name}}.web :refer [application]]))

(defonce server (atom nil))

(defn start-server
  ([] (start-server true))
  ([open-browser?]
   (if-not (nil? @server)
     (println (str "Could not start server; already started.  "
                   "Maybe you want to (restart-server)?"))
     (reset! server (serve application {:join? false
                                        :open-browser? open-browser?
                                        :auto-refresh? true})))
   nil))

(defn stop-server []
  (let [svr @server]
    (when-not (nil? svr)
      (.stop svr)
      (reset! server nil)))
  nil)

(defn restart-server []
  (when-not (nil? @server)
    (stop-server))
  (require '{{name}}.dev :reload-all)
  (start-server))

(defn -main [& [mode]]
  (when-not (nil? mode)
    (glimpse/set-mode! (keyword mode)))
  (start-server))
