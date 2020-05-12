package com.example.examplejniwihf5n.aligner
import de.mpicbg.scicomp.bioinfo.FastqRecord
import de.mpicbg.scicomp.kutils.saveAs

/**
 * This class acts as a wrapper for the de.mpicbg.scicomp in order to create fastQ files and save it into disk.
 */
class FastQFileManager {

    companion object {

        private val DEFAULT_SCORE_CHARACTER = "!"
        /**
         * Generates a FastQ file for the runID and the bases in the class synchronously. The FastQ file will be in the fastQ folder and will be a compress file.
         */
        fun generateFastqFileWithDefaultScoreCharacter(identifier:String, bases:List<String>) {

            val fastQRecordList = bases.map { bases ->

                val score = DEFAULT_SCORE_CHARACTER.repeat(bases.length)
                FastqRecord("@$identifier",bases,score)
            }

            val fastQFilePath = AlignerFileStorage.generateFastQFilePath(identifier)
            fastQRecordList.saveAs(fastQFilePath, transform = { it.toEntryString() })
        }
    }
}