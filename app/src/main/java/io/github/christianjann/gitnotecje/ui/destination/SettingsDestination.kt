package io.github.christianjann.gitnotecje.ui.destination

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface SettingsDestination : Parcelable {

    @Parcelize
    data object Main : SettingsDestination

    @Parcelize
    data object Logs : SettingsDestination

    @Parcelize
    data object FolderFilters : SettingsDestination

}