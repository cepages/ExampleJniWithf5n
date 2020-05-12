package com.example.examplejniwihf5n.aligner

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.Exception

sealed class Result<out T:Any?>
class Success<out T : Any?>(val data: T) : Result<T>()
class Error(val exception: Throwable, val message: String = exception.localizedMessage) : Result<Nothing>()
/**
 * The aligner will take the runID as identifier and a list of bases string for that id.
 */
class AlignerManager(val runID:String, val bases:List<String>) {

    /**
     * Runs the aligner minimap2 with the bases provided in the class.
     * This call runs synchronously
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun runAligner():Error? {

        FastQFileManager.generateFastqFileWithDefaultScoreCharacter(runID,bases)

        val fastaAbsolutePath = AlignerFileStorage.fastaAbsolutePath()
        val fastQAbsolutePath = AlignerFileStorage.fastQAbsolutePath(runID)
        val outputAlignerPath = AlignerFileStorage.outputAlignerAbsolutePath(runID)

        val commandToRun = CommandGenerator().runMinimap(fastaAbsolutePath,fastQAbsolutePath,outputAlignerPath)

        val output = NativeCommands.getNativeInstance().init(commandToRun,0)
        if (output != 0) {

            Log.e("AlignerManager","Error in the aligner")
            return Error(Exception("Error in the aligner"))
        }
        return null
    }
}