(ns hyperlith.impl.params
  (:require [hyperlith.impl.codec :as codec]))

;; TODO: might want to parse datastar query param (json) to edn
;; TODO: might want to keyword query params

(defn parse-query-string [query-string]
  (try
    (codec/form-decode query-string)
    (catch Throwable _)))

(defn wrap-query-params
  [handler]
  (fn [req]
    (-> (if-let [query-string (:query-string req)]
          (assoc req :query-params (parse-query-string query-string))
          req)
      handler)))


