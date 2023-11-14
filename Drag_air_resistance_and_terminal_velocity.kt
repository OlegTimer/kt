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
    var mass = 0.0
    var square = 0.0
    var dragCoeff = 0.0
    var airDensity = 0.0
    var velocity = 0.0
    var drag = 0.0
    var gforce = 0.0
    var terminalVelocity = 0.0

    val fileName = "_input.txt"
    val myfile = File(fileName)
    val fileExists = myfile.exists()
    if (!fileExists) {
        myfile.printWriter().use { out ->
            out.println("0.0")
            out.println("0.0")
            out.println("0.0")
            out.println("0.0")
            out.println("0.0")
            out.println("_ Calculation of drag (air_resistance) and terminal velocity _")
            out.println("Please, in the first line enter the front_projection in m^2")
            out.println("In the next line enter air density in kg*m^3 (1.23 sea level)")
            out.println("In the next line enter speed in m/s")
            out.println("In the next line enter mass in kg")
            out.println("In the last line enter the drag factor:")
            out.println("<Disk, surface to stream: 1.17 >\n")
            out.println("<Half-sphere, base to stream: 1.17 >\n")
            out.println("<Cube, side to stream: 1.05 >\n")
            out.println("<Cylinder, base to stream: 0.82 >\n")
            out.println("<Cube, edge to stream: 0.8 >\n")
            out.println("<Cone, sharp to stream: 0.5 >\n")
            out.println("<Sphere: 0.47 >\n")
            out.println("<Half-sphere, spherical to stream: 0.38 >\n")
            out.println("<Drop, fat side to stream: 0.04 >\n")
            out.println("^ Enter val(s) in the line(s) of _input.txt in the program's dir and run *.jar")
        }
    }

    val inputStream: InputStream = File("_input.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { liststr.add(it)} }


    liststr.forEach{
        try {
            val num = it.toDoubleOrNull()
            if (num != null) {
                listd.add(num)
            }
        }catch (_: Exception) { }
    }


    if    (listd.size<5){
        err=1
    }
    else{
         dragCoeff = listd[4]
         square = listd[0]
         airDensity = listd[1]
         velocity = listd[2]
         mass = listd[3]
         drag = 0.5 * velocity * velocity * square * airDensity * dragCoeff
         gforce = mass*9.81
         terminalVelocity = kotlin.math.sqrt(gforce / (0.5 * dragCoeff * airDensity * square))
    }

    val fileName2 = "_output.txt"
    val myfile2 = File(fileName2)
    myfile2.printWriter().use { out ->
        if (err==1){
            out.println("err")
        }
        else {
            out.println("With front_projection\t$square\tm^2")
            out.println("air density\t$airDensity\tkg*m^3")
            out.println("speed\t$velocity\tm/s")
            out.println("mass\t$mass\tkg")
            out.println("and drag factor\t$dragCoeff")
            out.println("Drag (air_resistance) is\t$drag\tH")
            out.println("Gravitational force is\t$gforce\tH")
            out.println("Terminal velocity is\t$terminalVelocity\tm/s")
        }
    }

}