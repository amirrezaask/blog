(ns blog.core
  (:gen-class)
  (:require [blog.parser :as parser]))

(defn -main [& args]
  (println (parser/parse (nth args 0))))
