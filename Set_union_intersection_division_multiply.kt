//To run jar with no console on Win7 Java8 by OlegTim
//for IntelliJ IDEA files are in main project dir
//Create Kotlin project
// File - Project structure - Artifacts
//Create new artifact jar. Choose main class (of code) and create
// build - build artifacts - build jar
//jar will be in out\artifacts
//you can rename to Main.kt
import java.io.BufferedReader
import java.io.InputStream
import java.io.File

fun main() {
    var err = 0
    var mode = -1
    val liststr: MutableSet<String> = mutableSetOf()
    val liststr2: MutableSet<String> = mutableSetOf()
    val liststrf: MutableList<String> = ArrayList()


    var fileName = "_options.txt"
    var file = File(fileName)
    var fileExists = file.exists()
    if (!fileExists) {
        file.printWriter().use { out ->
            out.println("0")
            out.println("Set union, intersection, division, multiply")
            out.println("^ in the first lines of _options.txt (this file) choose an action: ^")
            out.println("0 - union, 1 - intersection (crossing), 2 division (difference)")
            out.println("3 - direct multiply (Cartesian)")
            out.println("Enter nums or letters (line-separated) in _input.txt , _input2.txt")
            out.println("Enter val(s) in the line(s) of _options.txt , _input.txt , _input2.txt")
            out.println("in the program's dir and run *.jar Result is in _output.txt")
            out.println("")
            out.println("For example, by default in _options.txt first line is 0. Action is union.")
            out.println("Set1 is {0.0} and Set2 is {a}. Result will contain both {0.0 , a}")
        }
    }

    fileName = "_input.txt"
     file = File(fileName)
     fileExists = file.exists()
    if (!fileExists) {
        file.printWriter().use { out ->
            out.println("0.0")
        }
    }

    fileName = "_input2.txt"
    file = File(fileName)
    fileExists = file.exists()
    if (!fileExists) {
        file.printWriter().use { out ->
            out.println("a")
        }
    }

    val inputStream: InputStream = File("_input.txt").inputStream()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { liststr.add(it)} }
    val inputStream2: InputStream = File("_input2.txt").inputStream()
    inputStream2.bufferedReader().useLines { lines -> lines.forEach { liststr2.add(it)} }
    val bufferedReader: BufferedReader = File("_options.txt").bufferedReader()
    val inputString = bufferedReader.readLine()
    try {
        val num = inputString.toIntOrNull()
        if (num != null) {
         mode = num
        }
    }catch (_: Exception) { }


     if (mode==0 || mode==1 || mode==2 || mode==3){}else{err=1}
     if (liststr.size<1 || liststr2.size<1){err=1}


    if (err==0){//if no err start
if (mode==0){//union
    val m: MutableSet<String> = mutableSetOf()
    liststr.forEach {m.add(it)}
    liststr2.forEach {m.add(it)}
    m.forEach {liststrf.add(it)}
}

 if (mode==1){//cross
liststr.forEach {
    if (liststr2.contains(it)){ liststrf.add(it) }
}
}

if (mode==2){//divide
    liststr.forEach {
        if (liststr2.contains(it)){ } else{liststrf.add(it) }
    }
}

if (mode==3){ //multiply
    var s= ""
    liststr.forEach {
s=it
        liststr2.forEach {
            liststrf.add(s+"\t"+it)
        }
    }
}
    }//if no err end


    fileName = "_output.txt"
    file = File(fileName)
    file.printWriter().use { out ->
        if (err==1){
            out.println("err")
        }
        else {

            if (liststrf.size<1) {
                out.println("empty")
            }
            else{
                liststrf.forEach {
                    out.println("$it")
                }
            }

        }

    }
}