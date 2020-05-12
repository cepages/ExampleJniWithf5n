package com.example.examplejniwihf5n.aligner
import splitties.init.appCtx
import java.io.File

class DirectoryManager {

    companion object {

        private val filesDirectory = appCtx.filesDir
        val RAW_FILES_DIRECTORY = "rawFiles"
        val FASTQ_FILES_DIRECTORY = "fastQFiles"

        fun rawBasesFilesFileDirectory(runID: String): File {

            val rawDirectoryFile = File(filesDirectory, RAW_FILES_DIRECTORY)
            return File(rawDirectoryFile, runID)
        }

        @Throws
        fun createFolderForRawBases(runID: String):String {

            val directory = rawBasesFilesFileDirectory(runID)
            return createDirectoryForFolderFile(directory).absolutePath
        }

        fun fastQGlobalFileDirectory():File {

            return File(filesDirectory, FASTQ_FILES_DIRECTORY)
        }

        fun fastQFileDirectory(runID: String):File {

            val fastQDirectory = fastQGlobalFileDirectory()
            return File(fastQDirectory,runID)
        }

        fun createDirectoryForFastQFiles(runID: String):File {

            val directory = fastQFileDirectory(runID)
            return createDirectoryForFolderFile(directory)
        }

        @Throws
        private fun createDirectoryForFolderFile(directory:File):File {

            if (!directory.exists()) {
                directory.mkdirs()
            }
            return directory
        }
    }
}