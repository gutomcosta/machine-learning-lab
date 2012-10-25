(ns recommendation.pearson_correlation_score
	(:use [critics.common_elements])
	(:require [ clojure.contrib.math :as math]))


(defn square 
	[value]
	(* value value))

(defn square-for-person
	[person, critics, key]
	(square (get-in critics [person key])))

(defn sum-of
	[person critics-of]
	(let [values (map (fn[key] (get-in  critics-of [person key])) (keys (get critics-of person)))]
		(reduce + values)))

(defn sum-of-squares
	[person critics-of similarities]
		(let [squared (map (fn[key] (square-for-person person critics-of key)) (keys similarities))]
		(reduce + squared))) 

(defn multiplication-score
	[critics-of person1 person2 key]
	(* (get-in critics-of [person1 key]) (get-in critics-of [person2 key])))

(defn sum-of-multiplication-between
	[critics-of person1 person2 similarities]
		(let [multiplication (map (fn[key] (multiplication-score critics-of person1 person2 key)) (keys similarities))]
			(reduce + multiplication)))


(defn pearson-correlation-score
	[person1 person2 critics-of]
	(let [similarities (common-items (get critics-of person1) (get critics-of person2))
		    similarities_count (count similarities)
        sum-of-person1 (sum-of person1 critics-of)
        sum-of-person2 (sum-of person2 critics-of)
        sum-of-squares-person1 (sum-of-squares person1 critics-of similarities)
        sum-of-squares-person2 (sum-of-squares person2 critics-of similarities)
        sum-of-multiplication (sum-of-multiplication-between critics-of person1 person2 similarities)
        value (- sum-of-multiplication (/ (* sum-of-person1 sum-of-person2) similarities_count))
        density1 (math/sqrt (- sum-of-squares-person1 (/  (math/expt sum-of-person1 2) similarities_count)))
        density2 (math/sqrt (- sum-of-squares-person2 (/  (math/expt sum-of-person2 2) similarities_count)))
        density (* density1 density2)]
		(if (== density 0) 0 (/ value density))))
