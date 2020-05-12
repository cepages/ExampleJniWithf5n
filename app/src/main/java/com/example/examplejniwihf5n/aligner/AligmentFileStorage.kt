package com.example.examplejniwihf5n.aligner

import android.os.Build
import androidx.annotation.RequiresApi
import splitties.init.appCtx
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class AlignerFileStorage {

    companion object {

        private val PREFIX_FILE_NAME = "fastQ_"
        private val EXTENSION = ".fq"
        private val OUTPUT_ALIGNER = "output.sam"
        private val FASTA_FILE_NAME_AND_EXTENSION = "lambda.fasta"

        private fun fastQFileName(runID: String):String {

            return  PREFIX_FILE_NAME + runID + EXTENSION
        }

        /**
         * Creates a folder where to store the fastQFiles for the runID provided and returns the path with filename included
         */
        fun generateFastQFilePath(runID: String):File {

            val directoryFastQFiles = DirectoryManager.createDirectoryForFastQFiles(runID)
            return File(directoryFastQFiles, fastQFileName(runID))
        }

        /**
         * Returns the absolute path for the folder and file where the output for the aligner should be placed.
         */
        fun outputAlignerAbsolutePath(runID: String): String {

            val fastQDirectory = DirectoryManager.fastQFileDirectory(runID)
            return File(fastQDirectory, OUTPUT_ALIGNER).absolutePath
        }

        /**
         * Returns the absolute path for the folder and file where the fastQ file should be placed.
         */
        fun fastQAbsolutePath(runID: String): String {

            val fastQDirectory = DirectoryManager.fastQFileDirectory(runID)
            val fastqFile = fastQFileName(runID)
            return File(fastQDirectory,fastqFile).path
        }

        /**
         * Returns the absolute path for the fasta file.
         */
        fun fastaAbsolutePath(): String {

            val fastaFileInStorage = File(DirectoryManager.fastQGlobalFileDirectory(),FASTA_FILE_NAME_AND_EXTENSION)
            //We get the location of the fasta file. This fasta file should have been copied the first time this method has been called.
            if (fastaFileInStorage.exists()) {
                // If the file exist we return the file
                return fastaFileInStorage.path
            }else{

                // If not we copy it to the global fastQ folder and return the absolute path.
                val fileInputStream =  appCtx.assets.open(FASTA_FILE_NAME_AND_EXTENSION)
                Files.copy(fileInputStream,fastaFileInStorage.toPath(), StandardCopyOption.REPLACE_EXISTING)
                return fastaFileInStorage.absolutePath
            }
        }
    }
}