(ns blog.core
  (:gen-class)
  (:require [clj-org.org :refer [parse-org]]))

(defn open-file [file-path]
  (slurp file-path))

(defn parse [file-path]
  (let [contents (open-file file-path)]
    (parse-org contents)))

(defn main [& args]
  (println "Args => " args))
