(ns blog.core
  (:gen-class)
  (:require [blog.parser :as parser]))

(defn -main [& args]
  (println args)
  (println (parser/posts "public" "posts" args)))
