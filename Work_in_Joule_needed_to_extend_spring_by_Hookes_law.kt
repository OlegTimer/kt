//To run jar with no console on Win7 Java8 by OlegTim
//reads vals from lines of _input.txt the program's dir and writes to _output.txt
//for IntelliJ IDEA files are in main project dir
//Create Kotlin project
// File - Project structure - Artifacts
//Create new artifact jar. Choose main class (of code) and create
// build - build artifacts - build jar
//jar will be in out\artifacts
//you can rename to Main.kt
import java.io.InputStream
import java.io.File
fun main() {
    val liststr: MutableList<String> = ArrayList()
    val listd: MutableList<Double> = ArrayList()
    var err = 0
    var res = 0.0

    val fileName = "_input.txt"
    val myfile = File(fileName)
    val fileExists = myfile.exists()
    if (!fileExists) {
        myfile.printWriter().use { out ->
 out.println("0.0")
 out.println("0.0")
 out.println("Calculation of work (J Joule) needed to extend spring by Hooke's law")
 out.println("In the first line enter the force (N newton), needed to extend spring by 1 cm centimetre")
 out.println("In the second line enter distance (cm) to extend spring")
 out.println("^ Enter val(s) in the line(s) of _input.txt in the program's dir and run *.jar")
        }
    }

    val inputStream: InputStream = File("_input.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { liststr.add(it)} }


    liststr.forEach{
        try {
            var num = it.toDoubleOrNull()
            if (num != null) {
                listd.add(num)
            }
        }catch (e: Exception) { }
    }


 if    (listd.size<2){
     err=1
 }
else{
     val force = listd[0]
     val dist = listd[1]/100
     res = (force/0.01)*dist*dist/2
}

    val fileName2 = "_output.txt"
    val myfile2 = File(fileName2)
    myfile2.printWriter().use { out ->
        if (err==1){
            out.println("err")
        }
        else {
            out.println("If force, needed to extend spring by 1 cm centimetre is: "+listd[0]+" N")
            out.println("and distance to extend spring is: "+listd[1]+" cm")
            out.print("The work needed to extend spring by Hooke's law is: ")
            out.print(res)
            out.println(" J")
            out.println("or "+res/9.80665+" kgf·m")
        }

    }
}