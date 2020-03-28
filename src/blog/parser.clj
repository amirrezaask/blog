(ns blog.parser
  (:require [clj-org.org :refer [parse-org]] [hiccup.core :as h]))

(defn parse
  "first opens given file and parse it into hiccup tree."
  [file-path]
  (->
   file-path
   (slurp)
   (parse-org)
   (:content)
   (h/html)))

(defn posts
  "parses all posts in /posts and return all posts html representation plus the posts index."
  [posts-loc posts]
  (->>
   posts
   (map (fn [post-name] (format "%s/%s" posts-loc post-name)))
   (map parse)))
