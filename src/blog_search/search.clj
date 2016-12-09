(ns blog-search.search
  (:require [clojure.set :as sets :refer [union]]
            [blog-search.feed-parser :as fp :refer [parse-feed]]
            [clj-http.client :as http :refer [with-connection-pool get]]
            [cemerick.url :refer [url]]))

(defn request-api-pooled
  [api-urls]
  (http/with-connection-pool 
    {:timeout 5 :threads 4 :insecure? false :default-per-route 10})
    (map http/get api-urls))

(defn extract-links
  [resp]
  (->> resp
       :body
       fp/parse-feed
       :entries
       (map :link)
       (take 10)))

(defn extract-domains
  [link]
  (->> link
       url
       :host
       (re-find #"(?i)[\w\d-_]+\.[\w\d\-_]+$")))

(defn get-stats
  [words]
  (->> words
       set
       (map #(str "http://blogs.yandex.ru/search.rss?text=" %))
       request-api-pooled
       (map extract-links)
       flatten
       set
       (map extract-domains)
       (group-by identity)
       (reduce-kv 
         #(assoc %1 %2 (count %3)) 
         {})))

