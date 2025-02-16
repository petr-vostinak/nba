package cz.vostinak.presentation.screens.team.utils

import cz.vostinak.presentation.R

object TeamLogoUtils {

    fun getLogoByAbbreviation(abbreviation: String?): Int? {
        return when (abbreviation) {
            "ATL" -> R.drawable.atl
            "BOS" -> R.drawable.bos
            "BKN" -> R.drawable.bkn
            "CHA" -> R.drawable.cha
            "CHI" -> R.drawable.chi
            "CLE" -> R.drawable.cle
            "DAL" -> R.drawable.dal
            "DEN" -> R.drawable.den
            "DET" -> R.drawable.det
            "GSW" -> R.drawable.gsw
            "HOU" -> R.drawable.hou
            "IND" -> R.drawable.ind
            "LAC" -> R.drawable.lac
            "LAL" -> R.drawable.lal
            "MEM" -> R.drawable.mem
            "MIA" -> R.drawable.mia
            "MIL" -> R.drawable.mil
            "MIN" -> R.drawable.min
            "NOP" -> R.drawable.nop
            "NYK" -> R.drawable.nyk
            "OKC" -> R.drawable.okc
            "ORL" -> R.drawable.orl
            "PHI" -> R.drawable.phi
            "PHX" -> R.drawable.phx
            "POR" -> R.drawable.por
            "SAC" -> R.drawable.sac
            "SAS" -> R.drawable.sas
            "TOR" -> R.drawable.tor
            "UTA" -> R.drawable.uta
            "WAS" -> R.drawable.was
            else -> null
        }
    }

}