(ns {{name}}.routes
    (:require [compojure.core :refer :all]
              [glimpse.views :as glimpse]))

(defroutes {{name}}-routes
  (GET "/" request (-> (glimpse/view (:uri request))
                       (glimpse/render))))
