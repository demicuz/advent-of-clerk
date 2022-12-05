;; # 🎄 Advent of Clerk: Day 4
(ns advent-of-clerk.day-04
  (:require
    [nextjournal.clerk :as clerk]
    [clojure.string :as str]))

(defn parse-int [s]
  (Integer/parseInt s))

(def input (slurp "resources/day_04.txt"))

(defn process-line [line]
  (->> line
       (re-matches #"(\d+)-(\d+),(\d+)-(\d+)")
       (drop 1)
       (map parse-int)))

(def data
  (->> input
       str/split-lines
       (map process-line)))

(defn includes? [[x1 x2 y1 y2]]
  (or
   (and (<= x1 y1) (>= x2 y2))
   (and (>= x1 y1) (<= x2 y2))))

(->> data
     (filter includes?)
     count)

(defn overlaps? [[x1 x2 y1 y2]]
  (or
   (and (<= x1 y2) (>= x2 y1))
   (and (<= y1 x2) (>= y2 x1))))

(->> data
     (filter overlaps?)
     count)
