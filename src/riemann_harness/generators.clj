(ns riemann-harness.generators
  (:require
    [clojure.edn     :as edn]
    [clojure.java.io :as io]))


(defn replay-events-generator
  [history-file]
  (let [history (map edn/read-string (-> history-file io/resource io/reader line-seq))
        reel    (atom (cycle history))]
    (fn []
      (let [[current-event next-event] @reel
            interval                    (if (pos? (- (:time next-event) (:time current-event)))
                                          (* 1000 (- (:time next-event) (:time current-event))) ;; riemann times are in seconds, so multiply by 1000
                                          1000)]
        (swap! reel rest)
        [(dissoc current-event :time) interval]))))

(def my-host-a--my-service
  (constantly
    [{:host "my-host-a"
      :service "my-service"
      :metric 1
      :state "ok"}
     1000]))

(def my-host-b--my-service
  (fn []
    [{:host "my-host-b"
      :service "my-service"
      :metric (rand-int 100)
      :state (rand-nth ["ok" "warning" "critical"])}
     (+ 500 (rand-int 500))]))

(def my-host-c--my-service
  (replay-events-generator "my-host-c--my-service.edn"))

(def generators
  [my-host-a--my-service
   my-host-b--my-service
   my-host-c--my-service])
