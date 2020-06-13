package sk.mkiss.interview.bigramhistogram

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringReader

internal class BigramHistogramTest {

    @Test
    fun `should return empty histogram if input is empty`() {
        assertThat(BigramHistogram.from(StringReader(""))).isEmpty()
    }

    @Test
    fun `should create 0 bigrams if input contains only one word`() {
        assertThat(BigramHistogram.from(StringReader(" word "))).isEmpty()
    }

    @Test
    fun `should create bigrams ignoring case`() {
        assertThat(BigramHistogram.from(StringReader("first Second First sECOND")))
            .containsOnlyKeys("first second", "second first")
    }

    @Test
    fun `should create bigrams ignoring punctuation`() {
        assertThat(BigramHistogram.from(StringReader("first, second. [third] ?!? fourth;")))
            .containsOnlyKeys("first second", "second third", "third fourth")
    }

    @Test
    fun `should create bigrams across lines`() {
        assertThat(
            BigramHistogram.from(
                StringReader(
                    """first second
                       third
                       
                       fourth"""
                )
            )
        )
            .containsOnlyKeys("first second", "second third", "third fourth")
    }

    @Test
    fun `should build histogram of bigrams`() {
        assertThat(BigramHistogram.from(StringReader("The quick brown fox and the quick blue hare.")))
            .isEqualTo(
                mapOf(
                    "the quick" to 2,
                    "quick brown" to 1,
                    "brown fox" to 1,
                    "fox and" to 1,
                    "and the" to 1,
                    "quick blue" to 1,
                    "blue hare" to 1
                )
            )
    }
}