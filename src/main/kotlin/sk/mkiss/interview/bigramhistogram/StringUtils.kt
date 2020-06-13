package sk.mkiss.interview.bigramhistogram

private val PUNCTUATION_REGEX = "\\p{Punct}".toRegex()

fun String.stripPunctuation() = this.replace(PUNCTUATION_REGEX, "")