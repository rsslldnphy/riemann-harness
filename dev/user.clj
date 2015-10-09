(ns user
  (:require
    [environ.core               :refer [env]]
    [riemann.bin                :as riemann]
    [clojure.tools.nrepl.server :as nrepl]))

(defonce started?
  (atom false))

(defn reload!
  []
  (if @started?
    (riemann/reload!)
    (do (riemann/-main "start" "riemann.config")
        (reset! started? true))))

(comment
  (reload!))
