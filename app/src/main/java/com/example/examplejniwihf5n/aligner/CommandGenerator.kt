package com.example.examplejniwihf5n.aligner
class CommandGenerator {

    fun runMinimap(fastaFilePath:String, fastqFilePath:String, outputFilePath:String):String {

        return "minimap2 -x map-ont  $fastaFilePath $fastqFilePath -o $outputFilePath -a"
    }
}