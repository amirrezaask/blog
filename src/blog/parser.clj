(ns blog.parser
  (:require [clj-org.org :refer [parse-org]]))

(defn open-file
  "Basically opens a file with given file-path"
  [file-path]
  (slurp file-path))

(defn parse
  "first opens given file and parse it into hiccup tree."
  [file-path]
  (->
   file-path
   (open-file)
   (parse-org)))
