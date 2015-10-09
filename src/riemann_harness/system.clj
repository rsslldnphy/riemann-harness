(ns riemann-harness.system
  (:require
    [com.stuartsierra.component :as component]))

(defn new-system
  []
  (component/system-map :riemann ))
