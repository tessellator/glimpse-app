(defproject {{name}} "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring-server "0.4.0"]
                 [compojure "1.4.0"]
                 [glimpse "0.1.0"]]

    :aliases {"prototype" ["with-profile" "dev" "run" "-m" "{{name}}.dev" "prototype"]
              "production" ["with-profile" "dev" "run"]}

  :main ^:skip-aot {{name}}.web
  :uberjar-name "{{name}}-standalone.jar"

  :profiles {:uberjar {:aot :all}
             :production {:ring {:open-browser? false
                                 :stacktraces? false
                                 :auto-reload? false}}
             :dev {:dependencies [[ring/ring-devel "1.4.0"]]
                   :source-paths ["dev"]}}

  :repl-options {:init-ns {{name}}.dev
                 :init (glimpse/set-mode! :prototype)})
