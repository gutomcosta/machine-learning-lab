(ns recommendation.metrics.euclidean_distance)


(def critics {"Lisa Rose" {"Lady in the Water" 2.5 "Snakes on a Plane" 3.5 "Just My Luck" 3.0 "Superman Returns" 3.5 "You, Me and Dupree" 2.5 "The Night Listener" 3.0}
              "Gene Seymour" {"Lady in the Water" 3.0 "Snakes on a Plane" 3.5 "Just My Luck" 1.5 "Superman Returns" 5.0 "The Night Listener" 3.0 "You, Me and Dupree" 3.5}} )


(defn check-exists-in-collections-with
  [collection other-collection exists-func not-exists-func]
  (into {} 
    (for [[key value] collection]
      (if (contains? other-collection key)
        (exists-func key value)
        (not-exists-func key value)))))



(defn common-items
  [collection other-collection]
  (let [exists-func     (fn [key value] [key 1])
        not-exists-func (fn [key value] [key nil])]
  (check-exists-in-collections-with
    collection other-collection exists-func not-exists-func)))  


(defn get-common-items-between-persons
  [person1 person2 critics-of]
  (let [person1-critics (get critics-of person1)
        person2-critics (get critics-of person2)
        common-items (common-items person1-critics person2-critics)]
    (select-keys common-items (for [[k v]
                                common-items :when (not (= v nil))] k))))

(defn calculate-squared-of-difference
  [value other-value]
  (let [difference (- value other-value)]
    (* difference difference)))

(defn calculate-difference-between-collection-values
  [collection other-collection]
   (for [[key value] collection]
    (if (contains? other-collection key)
      (calculate-squared-of-difference value (get other-collection key))
      nil)))

(defn sum-of-squares
  [collection other-collection]
  (let [squared-difference (filter (fn[num] (not (nil? num))) (calculate-difference-between-collection-values 
                              collection other-collection))]
    (reduce + squared-difference)))

(defn euclidean-distance 
  [person1 person2 critics-of]
  (let [similarities (get-common-items-between-persons person1 person2 critics-of)
        squares (sum-of-squares (get critics-of person1) (get critics-of person2))
        square-roots-with-1 (+ 1 (Math/sqrt squares))]
        (/ 1 square-roots-with-1)))


