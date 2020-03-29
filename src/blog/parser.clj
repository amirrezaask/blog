(ns blog.parser
  (:require [clj-org.org :refer [parse-org]]
            [hiccup.core :as h]))

(defn parse
  "first opens given file and parse it into hiccup tree."
  [file-path]
  {
   :name file-path
   :content   (->
               file-path
               (slurp)
               (parse-org)
               (:content)
               (h/html))})

(defn with-posts-index
  "generate posts index"
  [posts]
  (let [index []]
    (map (fn [{:keys [name content]}]
         (conj index name) {:name name :content content}) posts)
    (conj posts index)
  ))

(defn posts
  "parses all posts in /posts and return all posts html representation plus the posts index."
  [posts-loc posts]
  (->>
   posts
   (map (fn [post-name] (format "%s/%s" posts-loc post-name)))
   (map parse)
   (with-posts-index)))

(posts "posts" ["post1.org" "post2.org"])
