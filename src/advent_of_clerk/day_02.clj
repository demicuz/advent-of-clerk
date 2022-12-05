;; # 🎄 Advent of Clerk: Day 2
(ns advent-of-clerk.day-02
  (:require
    [nextjournal.clerk :as clerk]
    [clojure.string :as str]))

(def input (slurp "resources/day_02.txt"))

(def abbr-player {\X :rock \Y :paper \Z :scissors})

(def abbr-opponent {\A :rock \B :paper \C :scissors})

(def shape-bonus {:rock 1 :paper 2 :scissors 3})

(defn parse-line-1 [line]
  [(abbr-opponent (first line))
   (abbr-player (last line))])

(def parsed-input-1
  (->> input
       str/split-lines
       (map parse-line-1)
       ))

(defn win? [move]
  (or (= move [:rock :paper]) (= move [:paper :scissors]) (= move [:scissors :rock])))

(defn draw? [move]
  (= (first move) (second move)))

(defn get-round-bonus [move]
  (cond
   (win? move) 6
   (draw? move) 3
   :else 0))

(defn get-round-score [move]
  (+ (get-round-bonus move) (shape-bonus (second move))))

(->> parsed-input-1
     (map get-round-score)
     (apply +)
     )

; **Part 2**
(def shapes [:rock :paper :scissors])

(defn get-move [opponent-shape result]
  (let [opp-index (.indexOf shapes opponent-shape)]
    (cond
     (= result \X) (shapes (mod (+ opp-index 2) 3))
     (= result \Y) (shapes opp-index)
     (= result \Z) (shapes (mod (+ opp-index 1) 3)))))

(defn parse-line-2 [line]
  (let [[opponent result] [(first line) (last line)]
        opponent-shape (abbr-opponent opponent)]
    [opponent-shape (get-move opponent-shape result)]))

(def parsed-input-2
  (->> input
       str/split-lines
       (map parse-line-2)
       ))

(->> parsed-input-2
     (map get-round-score)
     (apply +))
