(ns recommendation.test.top_matches
  (:use [recommendation.metrics.pearson_correlation_score] :reload)
  (:use [recommendation.top_matches] :reload)
  (:use [clojure.test]))

(def data {"Lisa Rose" {"Lady in the Water" 2.5 "Snakes on a Plane" 3.5 "Just My Luck" 3.0 "Superman Returns" 3.5 "You, Me and Dupree" 2.5 "The Night Listener" 3.0}
              "Gene Seymour" {"Lady in the Water" 3.0 "Snakes on a Plane" 3.5 "Just My Luck" 1.5 "Superman Returns" 5.0 "The Night Listener" 3.0 "You, Me and Dupree" 3.5}
              "Michael Phillips" {"Lady in the Water" 2.5 "Snakes on a Plane" 3.0 "Superman Returns" 3.5 "The Night Listener" 4.0}
              "Claudia Puig" {"Snakes on a Plane" 3.5 "Just My Luck" 3.0 "The Night Listener" 4.5 "Superman Returns" 4.0 "You, Me and Dupree" 2.5}
              "Mick LaSalle" {"Lady in the Water" 3.0 "Snakes on a Plane" 4.0 "Just My Luck" 2.0 "Superman Returns" 3.0 "The Night Listener" 3.0 "You, Me and Dupree" 2.0}
              "Jack Mattews" {"Lady in the Water" 3.0 "Snakes on a Plane" 4.0 "The Night Listener" 3.0 "Superman Returns" 5.0  "You, Me and Dupree" 3.5}
              "Tody" {"Snakes on a Plane" 4.5 "You, Me and Dupree" 1.0 "Superman Returns" 4.0}})

(defn get-rank-of 
  [person-name ranks]
  (first (filter (fn[rank] (= (:person rank) person-name)) ranks)))


(deftest should-be-possible-to-use-euclidean-distance
	(let [pearson-correlation (top-matches data "Tody" 3 pearson-correlation-score)]
     (is (= 3 (count pearson-correlation)))
     (let [lisa-rose    (get-rank-of "Lisa Rose" pearson-correlation)
           mick-lasale  (get-rank-of "Mick LaSalle" pearson-correlation)
           claudia-puig (get-rank-of "Claudia Puig" pearson-correlation)]
      (is (= 0.9912407071619299 (:value lisa-rose)))
      (is (= 0.9244734516419049 (:value mick-lasale)))
      (is (= 0.8934051474415647 (:value claudia-puig))))
	 	)
	)
