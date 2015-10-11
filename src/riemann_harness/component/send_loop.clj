(ns riemann-harness.component.send-loop
  (:require
    [riemann-harness.generators :as g]
    [com.stuartsierra.component :as component]
    [clojure.core.async         :as async :refer [alts! chan close! go-loop timeout <! <!!]]
    [riemann.client             :as riemann]
    [taoensso.timbre            :as log]))

(defn start-send-loop
  [riemann generator]
  (let [shutdown (chan)]
    [shutdown
     (go-loop []
       (let [[event interval] (generator)
             _                (riemann/send-event (:conn riemann) event)
             [v c]            (alts! [shutdown (timeout interval)])]
         (when-not (= c shutdown) (recur))))]))

(defrecord SendLoop [riemann]
  component/Lifecycle
  (start [this]
    (assoc this :send-loops
           (doall (map #(start-send-loop riemann %) g/generators))))
  (stop [this]
    (doseq [[shutdown send-loop] (:send-loops this)]
      (async/close! shutdown)
      (<!! send-loop))
    (dissoc this :send-loops)))

(defn new-send-loop
  []
  (map->SendLoop {}))
