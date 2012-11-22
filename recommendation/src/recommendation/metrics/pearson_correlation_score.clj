(ns recommendation.metrics.pearson_correlation_score
	(:require [ clojure.contrib.math :as math]))


(defn square 
	[value]
	(* value value))

(defn square-for-person
	[person, critics, key]
 	(square (get-in critics [person key])))

(defn sum-of
	[person similarities critics]
  (reduce (fn[value key] (+ value 
      ((critics person) key))) 0 similarities)
  )

(defn sum-of-squares
  [person critics-of similarities]
  (reduce (fn[x y] (+ x (math/expt ((critics-of person) y) 2))) 0 similarities))

(defn multiplication-score
	[critics-of person1 person2 key]
	(* (get-in critics-of [person1 key]) (get-in critics-of [person2 key])))


(defn sum-of-multiplication-between
  [critics-of person1 person2 similarities]
  (reduce (fn[x y] (+ x (* ((critics-of person1) y) ((critics-of person2) y)))) 0 similarities))

  
(defn pearson-correlation-score
  [person1 person2 critics-of]
  (let [similarities (filter (get critics-of person1) (keys (get critics-of person2)))]
    (if (= 0 (count similarities))
      0
      (let [sum-of-person1 (sum-of person1 similarities critics-of)
            sum-of-person2 (sum-of person2 similarities critics-of)
            sum-of-squares-person1 (sum-of-squares person1 critics-of similarities)
            sum-of-squares-person2 (sum-of-squares person2 critics-of similarities)
            sum-of-multiplication (sum-of-multiplication-between critics-of person1 person2 similarities)
            numerator (- sum-of-multiplication (/ (* sum-of-person1 sum-of-person2) (count similarities)))
            denominator (math/sqrt 
              (*
                (- sum-of-squares-person1 (/ (math/expt sum-of-person1 2) (count similarities)))
                (- sum-of-squares-person2 (/ (math/expt sum-of-person2 2) (count similarities)))))]
            (if (== denominator 0) 
              0 
              (double (/ numerator denominator)) )))))
   
   

