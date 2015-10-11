(ns riemann-harness.component.riemann
  (:require
    [riemann.client             :as riemann]
    [com.stuartsierra.component :as component]))

(defrecord RiemannClient
  [host]
  component/Lifecycle
  (start [this]
    (assoc this
           :conn (riemann/tcp-client {:host host})))
  (stop [this]
    (when-let [conn (:conn this)]
      (.close conn))
    (dissoc this :conn)))

(def new-riemann-client ->RiemannClient)
