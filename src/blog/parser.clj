(ns blog.parser
  (:require [clj-org.org :refer [parse-org]]
            [hiccup.core :as h]
            [clojure.string :as s]))


(defn read-and-parse
  "first opens given file and parse it into hiccup tree."
  [file-path]
  {
   :name file-path
   :content   (->
               file-path
               (slurp)
               (parse-org)
               (:content)
               )})

(defn with-posts-index
  "generate posts index"
  [posts-loc posts]
  (let [names (map :name posts)]
    (conj posts {:name (format "%s/index.html" posts-loc) :content [:ul (map (fn [name] [:li name]) names)]} )))

(defn extract-name [name]
  (get (s/split (get (s/split name #"/") 1) #"\.") 0))


(defn posts
  "parses all posts in /posts and return all posts html representation plus the posts index."
  [public-loc posts-loc posts]
  (println posts)
  (->>
   posts
   (map (fn [post-name] (format "%s/%s" posts-loc post-name)))
   (map (fn [post] (read-and-parse post)))
   (with-posts-index posts-loc)
   (map (fn [post] {:name (:name post) :content (h/html (:content post))}))
   (map (fn [post] (assoc post :name (extract-name (:name post)))))
   (map (fn [post] (assoc post :name (format "%s/%s.html" public-loc (get (s/split (:name post) #"\.") 0)))))
   (map (fn [post] (spit (:name post) (:content post))))
))

;; (posts "public" "posts" ["post1.org" "post2.org"])
