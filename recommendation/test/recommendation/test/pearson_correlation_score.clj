(ns recommendation.test.pearson_correlation_score
  (:use [recommendation.metrics.pearson_correlation_score] :reload)
  (:use [clojure.test]))


(def critics {"Lisa Rose"    {"Lady in the Water" 2.5 "Snakes on a Plane" 3.5 "Just My Luck" 3.0 "Superman Returns" 3.5 "You, Me and Dupree" 2.5 "The Night Listener" 3.0}
              "Gene Seymour" {"Lady in the Water" 3.0 "Snakes on a Plane" 3.5 "Just My Luck" 1.5 "Superman Returns" 5.0 "The Night Listener" 3.0 "You, Me and Dupree" 3.5}})

(deftest should-return-the-pearson-correlation-score
	  (let [critics {"Lisa Rose"    {"Lady in the Water" 2.5 "Snakes on a Plane" 3.5 "Just My Luck" 3.0 "Superman Returns" 3.5 "You, Me and Dupree" 2.5 "The Night Listener" 3.0}
                   "Gene Seymour" {"Lady in the Water" 3.0 "Snakes on a Plane" 3.5 "Just My Luck" 1.5 "Superman Returns" 5.0 "The Night Listener" 3.0 "You, Me and Dupree" 3.5}}
          distance (pearson-correlation-score "Lisa Rose" "Gene Seymour" critics)]
  (is (= 0.396 (Double/parseDouble (format "%.3f" distance))))))


(deftest should-return-the-square-for-a-person-critics-score
	(let [square (square-for-person "Lisa Rose" critics "Lady in the Water")]
		(is (= 6.25 square)))) 	

(deftest should-return-the-sum-of-squares-of-a-person-critics
	(let [similarities {"Lady in the Water" 1 "Snakes on a Plane" 1}
				sum-of-squares (sum-of-squares "Lisa Rose" critics similarities)]
				(is (= 18.5 sum-of-squares))))

(deftest shoud-return-the-sum-of-critics-score
	(let [sum-of (sum-of "Lisa Rose" critics)]
		(is (= 18 sum-of))))

(deftest should-return-the-sum-of-mulplication-between-two-collections-of-scores
	(let [similarities {"Lady in the Water" 1 "Snakes on a Plane" 1}
			  value (sum-of-multiplication-between critics "Lisa Rose" "Gene Seymour" similarities )]
			 (is (= 19.75 value))))