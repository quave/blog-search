(ns blog-search.core
  (:use compojure.core)
  (:require [blog-search.search :as search :refer [get-stats]]
	    [ring.adapter.jetty :refer [run-jetty]]
	    [ring.middleware.params :refer [wrap-params]]
            [cheshire.core :as json :refer [generate-string]]
	    [compojure.handler :refer [site]]
            [compojure.route :refer [not-found]]))

(defn handler
  [rq] 
  (let [kw (seq (rq "query"))]
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string (get-stats kw) {:pretty true})}))

(defroutes app-routes
  (GET "/search" {params :query-params} (handler params))
  (not-found "<h1>Page not found</h1>"))

(defn -main 
  []
  (run-jetty (-> app-routes site wrap-params) 
    {:port 7770}))
