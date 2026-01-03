package io.github.christianjann.gitnotecje.ui.screen.app

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.NavAction
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavTransitionScope
import dev.olshevski.navigation.reimagined.NavTransitionSpec
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.pop
import dev.olshevski.navigation.reimagined.rememberNavController
import io.github.christianjann.gitnotecje.ui.destination.AppDestination
import io.github.christianjann.gitnotecje.ui.destination.EditParams
import io.github.christianjann.gitnotecje.ui.destination.SettingsDestination
import io.github.christianjann.gitnotecje.ui.screen.app.edit.EditScreen
import io.github.christianjann.gitnotecje.ui.screen.app.grid.GridScreen
import io.github.christianjann.gitnotecje.ui.screen.settings.SettingsNav
import io.github.christianjann.gitnotecje.ui.utils.crossFade
import io.github.christianjann.gitnotecje.ui.utils.slide


private const val TAG = "AppScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    appDestination: AppDestination,
    onCloseRepo: () -> Unit,
) {

    val navController =
        rememberNavController(startDestination = appDestination)

    NavBackHandler(navController)

    SharedTransitionLayout {
        AnimatedNavHost(
            controller = navController,
            transitionSpec = AppNavTransitionSpec
        ) {
            when (it) {

                is AppDestination.Grid -> {
                    GridScreen(
                        onSettingsClick = {
                            navController.navigate(
                                AppDestination.Settings(
                                    SettingsDestination.Main
                                )
                            )
                        },
                        onEditClick = { note, editType ->
                            navController.navigate(AppDestination.Edit(EditParams.Idle(note, editType)))
                        },
                        sts = this@SharedTransitionLayout
                    )
                }

                is AppDestination.Edit -> EditScreen(
                    editParams = it.params,
                    onFinished = {
                        navController.pop()

                        if (it.params is EditParams.Saved) {
                            navController.navigate(AppDestination.Grid)
                        }
                    },
                    sharedTransitionScope = this@SharedTransitionLayout
                )

                is AppDestination.Settings -> SettingsNav(
                    onBackClick = { navController.pop() },
                    destination = it.settingsDestination,
                    onCloseRepo = onCloseRepo
                )
            }
        }
    }
}

private object AppNavTransitionSpec : NavTransitionSpec<AppDestination> {

    override fun NavTransitionScope.getContentTransform(
        action: NavAction,
        from: AppDestination,
        to: AppDestination
    ): ContentTransform {

        return when (from) {
            is AppDestination.Edit -> crossFade()
            AppDestination.Grid -> {
                if (to is AppDestination.Settings) {
                    slide()
                } else if (to is AppDestination.Edit) {
                    // Use shared element transition for note editing
                    ContentTransform(
                        targetContentEnter = androidx.compose.animation.fadeIn(),
                        initialContentExit = androidx.compose.animation.fadeOut(),
                        targetContentZIndex = 1f
                    )
                } else {
                    crossFade()
                }
            }

            is AppDestination.Settings -> slide(backWard = true)
        }
    }
}

