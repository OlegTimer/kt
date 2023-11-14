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
    val earthRadius = 6371.0
    val gravParam = 398600
    val liststr: MutableList<String> = ArrayList()
    val listd: MutableList<Double> = ArrayList()
    var err = 0
    var res = 0.0
    val r: Double
    val losses: Double

    val fileName = "_input.txt"
    val myfile = File(fileName)
    val fileExists = myfile.exists()
    if (!fileExists) {
        myfile.printWriter().use { out ->
            out.println("200")
            out.println("18.1")
            out.println("Get delta v (necessary speed), needed to reach round Earth orbit (km/s)")
            out.println("First line is height (km) of round orbit above Earth surface")
            out.println("Next line is % of losses from minimal characteristic delta v")
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


    if    (listd.size<2){
        err=1
        losses=0.0
        r=0.0
    }
    else{
        r=listd[0]
losses = listd[1]
res = kotlin.math.sqrt(2*gravParam/earthRadius) * kotlin.math.sqrt(1 -(earthRadius/(2*(r+earthRadius))))
    }

    val fileName2 = "_output.txt"
    val myfile2 = File(fileName2)
    myfile2.printWriter().use { out ->
        if (err==1){
            out.println("err")
        }
        else {
out.println("If height of round orbit above Earth surface is $r km")
out.println("Minimal characteristic delta v with NO losses (in km/s) is:")
out.println(res)
res+=res*losses/100
out.println("Assuming total amount of losses (grav, etc) is $losses % from minimal characteristic delta v,")
out.println("characteristic delta v WITH losses (in km/s) is:")
out.println(res)
out.println("Assuming Earth rotation speed is 0.465 km/s and rocket is launched east at it best course,")
out.println("the delta v is decreased and, depending on launch pad, final delta v is (km/s)")
out.println("for equator (Kuru):")
res-=0.465
out.println(res)
out.println("for Cape Canaveral:")
res+=0.056
out.println(res)
out.println("for Baykonur:")
res+=0.092
out.println(res)
        }
    }

}