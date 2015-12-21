package com.vvv.merge

fun main(args: Array<String>){
    if (args.size != 4){
        print("Required 4 arguments: <source-file>, <version1-file>, <version2-file>, <output-file>")
        return
    }
    val sourcePath = args[0]
    val version1Path = args[1]
    val version2Path = args[2]
    val outputPath = args[3]

    val sourceFile = java.io.File(sourcePath)
    val version1File = java.io.File(version1Path)
    val version2File = java.io.File(version2Path)

    if (!sourceFile.exists()) {
        print("Source File not found: $sourcePath")
        return
    }

    if (!version1File.exists()){
        print("Version 1 File not found: $version1Path")
        return
    }

    if (!version2File.exists()){
        print("Version 2 File not found: $version2Path")
        return
    }

    val source = sourceFile.readLines().toTypedArray()
    val version1 = version1File.readLines().toTypedArray()
    var version2 = version2File.readLines().toTypedArray()

    val merger = Merger(source, version1, version2)
    try {
        merger.Merge(outputPath)
    } catch (e : Exception){
        print(e.toString())
    }
}
