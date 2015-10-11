(ns user
  (:require
    [com.stuartsierra.component   :as component]
    [clojure.tools.namespace.repl :as repl]
    [environ.core                 :refer [env]]
    [user.system                  :refer :all]
    [riemann-harness.system       :as system]
    [riemann.client               :as riemann-client]))

(defn init []
  (reset! system (system/new-system)))

(defn start []
  (swap! system component/start))

(defn stop []
  (swap! system component/stop))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (repl/refresh :after 'user/go))

(comment
  (go)
  (stop)
  (reset))
