(ns riemann-harness.system
  (:require
    [riemann-harness.component.riemann   :refer [new-riemann-client]]
    [riemann-harness.component.send-loop :refer [new-send-loop]]
    [com.stuartsierra.component          :as component]))

(defn new-system
  []
  (component/system-map
    :riemann   (-> (new-riemann-client "riemann"))
    :send-loop (-> (new-send-loop)
                   (component/using [:riemann]))))
