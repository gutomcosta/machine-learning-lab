(ns recommendation.top_matches)


(defn top-matches
	"Returns the best matches for person from the data dictionary. The number of results and the similarity metric should be informed."
	[data, person, number-of-results, similarity-metric]
		(let [scores (map (fn[other-person] (similarity-metric person other-person data)) (keys data))
					reverse-score (reverse (sort scores))]
					(take number-of-results reverse-score))
		)