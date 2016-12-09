(defproject blog-search "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot blog-search.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.0.0"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [compojure "1.5.1"]
                 [com.cemerick/url "0.1.1"]
                 [cheshire "5.6.3"]
                 [org.jdom/jdom "2.0.2"]
		 [net.java.dev.rome/rome "1.0.0"]])
