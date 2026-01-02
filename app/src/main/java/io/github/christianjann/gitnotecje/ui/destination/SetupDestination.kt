package io.github.christianjann.gitnotecje.ui.destination

import android.os.Parcelable
import io.github.christianjann.gitnotecje.MyApp
import io.github.christianjann.gitnotecje.R
import io.github.christianjann.gitnotecje.ui.model.StorageConfiguration
import kotlinx.parcelize.Parcelize


sealed interface SetupDestination : Parcelable {

    @Parcelize
    data object Main : SetupDestination

    @Parcelize
    data class FileExplorer(
        val path: String?,
        val newRepoMethod: NewRepoMethod,
    ) : SetupDestination

    @Parcelize
    data class Remote(val storageConfig: StorageConfiguration) : SetupDestination

}

enum class NewRepoMethod {
    Create,
    Open,
    Clone;


    fun getExplorerTitle(useUrlForRootFolder: Boolean): String {
        val context = MyApp.appModule.context

        return when (this) {
            Create -> context.getString(R.string.create_repo_explorer)
            Open -> context.getString(R.string.open_repo_explorer)
            Clone -> if (useUrlForRootFolder) context.getString(R.string.clone_repo_explorer_use_url_for_root_folder) else context.getString(
                R.string.clone_repo_explorer
            )
        }
    }
}


