(defproject riemann-harness "0.1.0-SNAPSHOT"
  :description "Harness for building and testing riemann configs"
  :url "http://github.com/rsslldnphy/riemann-harness"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.clojure/tools.namespace "0.3.0-alpha1"]
                 [miscellany "0.0.4"]
                 [riemann-clojure-client "0.4.1"]
                 [com.taoensso/timbre "4.1.1"]
                 [com.stuartsierra/component "0.3.0"]
                 [environ "1.0.1"]]
  :main ^:skip-aot riemann-harness.core
  :repl-opts {:init-ns user}
  :target-path "target/%s"
  :plugins [[lein-environ "1.0.1"]]
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["src" "dev"]}})
