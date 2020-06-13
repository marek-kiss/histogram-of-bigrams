package sk.mkiss.interview.bigramhistogram

import java.io.FileReader
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    if (args.isEmpty()) {
        println("End. No file name provided.")
        exitProcess(1)
    }

    FileReader(args[0]).use {
        val histogram = BigramHistogram.from(it)
        histogram.forEach { bigram, occurence -> println("\"$bigram\" $occurence")}
    }

}




