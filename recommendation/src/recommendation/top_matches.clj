(ns recommendation.top_matches)

(defrecord Rank [person value])


(defn get-similarities
	[data-of person1 person2 similarity-metric]
		(if (not (= person1 person2)) 
			(Rank. person2 (similarity-metric person1 person2 data-of))
			;{ :person person2 :value (similarity-metric person1 person2 data-of)} 
			{}))

(defn top-matches
	"Returns the best matches for person from the data dictionary. The number of results and the similarity metric should be informed."
	[data, person, number-of-results, similarity-metric]
		(let [scores (map (fn[other-person] (get-similarities data person other-person similarity-metric)) (keys data))
					filterd-scores (filter (fn[element] (not (empty? element))) scores)
					reverse-score (reverse (sort-by :value filterd-scores))]
					(take number-of-results reverse-score))
		)