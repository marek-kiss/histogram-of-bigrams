package sk.mkiss.interview.bigramhistogram

import java.util.*

object BigramHistogram {

    fun from(readable: Readable): Map<String, Int> {
        var previousWord: String? = null
        var histogram = mutableMapOf<String, Int>()

        Scanner(readable).use {
            while (it.hasNext()) {

                val word = it.next().toLowerCase().stripPunctuation()

                if (word.isEmpty()) {
                    continue
                }

                if (previousWord != null) {
                    histogram.addOccurrence(bigram = "$previousWord $word")
                }

                previousWord = word
            }
        }

        return histogram
    }

    private fun MutableMap<String, Int>.addOccurrence(bigram: String) {
        this.merge(bigram, 1, Integer::sum)
    }

}