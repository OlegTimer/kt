// Leonardo Pisano (Leonardo Fibonacci) - 1202 Liber Abbaca
// How many couples of rabbits can you receive from one couple in 1 year?
// Every couple produces couple in 1 month, rabbits become fertile in 1 month, they don't die
// (rabbits in task are not suffering from close inbreeding) from D. Knuth by OlegTim
fun main(){
val end = 12
var fert = 2
var born = 0
var kid = 0

    for (i in 1..end) {
        fert = fert + kid
        kid = born
        born = fert
println("after $i month:\tfert: $fert\t born: $born\t kids: $kid")
    }

    val total = ((fert + born + kid)/2) -1
        println("Total couples without first couple: $total")
}