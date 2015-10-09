(ns riemann-harness.core
  (:require
    [riemann.bin                :as riemann]
    [clojure.tools.nrepl.server :as nrepl])
  (:gen-class))

(defn -main
  "Start nrepl and riemann servers"
  [& args]
  ; (println "Starting Riemann server")
  ; (riemann/-main "start" "riemann.config")
  (println "Starting NREPL server on port 4001")
  (nrepl/start-server :port 4001))

(comment
  (riemann/reload!)
  (riemann/-main))
