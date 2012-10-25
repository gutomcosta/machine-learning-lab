(ns recommendation.test.euclidean_distance
  (:use [recommendation.metrics.euclidean_distance] :reload)
  (:use [clojure.test]))

(deftest should_be_possible_to_get_a_critic_value ;; FIXME: write
  (is (= 2.5 (get-in critics ["Lisa Rose" "Lady in the Water"]))))



(deftest should-return-the-euclidean-distance-score
  (let [critics {"Lisa Rose" {"Lady in the Water" 2.5 "Snakes on a Plane" 3.5 "Just My Luck" 3.0 "Superman Returns" 3.5 "You, Me and Dupree" 2.5 "The Night Listener" 3.0}
                "Gene Seymour" {"Lady in the Water" 3.0 "Snakes on a Plane" 3.5 "Just My Luck" 1.5 "Superman Returns" 5.0 "The Night Listener" 3.0 "You, Me and Dupree" 3.5}}
        distance (euclidean-distance "Lisa Rose" "Gene Seymour" critics)]
  (is (= 0.294 (Double/parseDouble (format "%.3f" distance))))))

(deftest should-return-a-map-with-value-one-when-keys-exists-in-another-map
  (let [critics {"luiz" {"superman" 10 "spiderman" 3}
                 "jose" {"superman" 8 "spiderman" 8}}
        expected-items {"superman" 1 "spiderman" 1}
        common-items (get-common-items-between-persons "luiz" "jose" critics)]
    (is (= expected-items common-items))))

(deftest should-return-the-sum-of-squares
 (let [critics {"luiz" {"superman" 10 "spiderman" 3}
                 "jose" {"superman" 8 "spiderman" 8}}
       common-items {"superman" 1 "sperderman" 1}
       luiz (get critics "luiz")
       jose (get critics "jose")
       expected-sum-of-squares 29]
    (is (= expected-sum-of-squares (sum-of-squares luiz jose)))))
  
