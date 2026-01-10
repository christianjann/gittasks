package io.github.christianjann.gittasks.ui.destination

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface Destination : Parcelable {

    @Parcelize
    data class Setup(val setupDestination: SetupDestination) : Destination

    @Parcelize
    data class App(val appDestination: AppDestination) : Destination

}