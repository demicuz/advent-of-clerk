;; # 🎄 Advent of Clerk: Day 3
(ns advent-of-clerk.day-03
  (:require
    [nextjournal.clerk :as clerk]
    [clojure.string :as str]
    [clojure.set :as set]))

(def input (slurp "resources/day_03.txt"))

(def alphabet "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

(def scores (->> (range)
                 (drop 1)
                 (interleave alphabet)
                 (apply hash-map)))

(defn process-line [line]
  (let [half-len (/ (count line) 2)]
    (->> line
         (split-at half-len)
         (map set)
         (apply set/intersection)
         (first)
         (scores))))

(->> input
     str/split-lines
     (map process-line)
     (reduce +)
     )

(defn process-three-lines [lines]
  (->> lines
       (map set)
       (apply set/intersection)
       (first)
       (scores)))

(->> input
     str/split-lines
     (partition 3)
     (map process-three-lines)
     (reduce +))
