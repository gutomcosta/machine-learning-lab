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

(deftest should-be-possible-to-use-euclidean-distance
	(let [pearson-correlation (top-matches data "Tody" 3 pearson-correlation-score)]
     (println pearson-correlation)
	 	)
	)
