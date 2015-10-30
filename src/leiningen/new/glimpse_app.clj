(ns leiningen.new.glimpse-app
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "glimpse-app"))

(defn glimpse-app
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh glimpse project.")
    (->files data
             ["dev/{{sanitized}}/dev.clj" (render "dev.clj" data)]

             ["resources/public/README.md" (render "README.md" data)]
             ["resources/views/_templates/default.html" (render "default.html" data)]
             ["resources/views/index.html" (render "index.html" data)]

             ["src/{{sanitized}}/routes.clj" (render "routes.clj" data)]
             ["src/{{sanitized}}/web.clj" (render "web.clj" data)]

             [".gitignore" (render "gitignore" data)]
             ["project.clj" (render "project.clj" data)]
             ["Procfile" (render "Procfile" data)]
             ["README.md" (render "README.md" data)])))
