(ns critics.common_elements)

(defn check-exists-in-collections-with
  [collection other-collection exists-func not-exists-func]
  (into {} 
    (for [[key value] collection]
      (if (contains? other-collection key)
        (exists-func key value)
        ))))



(defn common-items
  [collection other-collection]
  (let [exists-func     (fn [key value] [key 1])
        not-exists-func (fn [key value] [key nil])]
  (check-exists-in-collections-with
    collection other-collection exists-func not-exists-func)))  
