(defproject riemann-harness "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.nrepl "0.2.11"]
                 [riemann "0.2.10"]
                 [com.stuartsierra/component "0.3.0"]
                 [environ "1.0.1"]]
  :main ^:skip-aot riemann-harness.core
  :repl-opts {:init-ns user}
  :target-path "target/%s"
  :plugins [[lein-environ "1.0.1"]]
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["src" "dev"]}})
