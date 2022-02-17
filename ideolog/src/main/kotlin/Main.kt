import java.io.File

const val limit = 10

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Error! Pass log_path to arguments")
        return
    }
    val logPath = args[0]
    val rows = File(logPath).readLines()
    val stat = mutableMapOf<String, Int>()

    val threadOrdNum = if (args.size > 1) args[1].toInt() else 5

    rows.forEach {
        val rowWords = it.split("\\s+".toRegex())
        val threadName = rowWords[threadOrdNum]
        stat[threadName] = stat.getOrDefault(threadName, 0).plus(1)
    }

    val result = stat.toList().sortedBy { -it.second }.take(limit)
    result.forEach { println("${it.first} ${it.second}") }
}