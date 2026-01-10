package io.github.christianjann.gittasks.manager

import io.github.christianjann.gittasks.manager.ExtensionType.Markdown
import io.github.christianjann.gittasks.manager.ExtensionType.Text


enum class ExtensionType {
    Text,
    Markdown;
}

fun extensionType(extension: String): ExtensionType? = extensionTypeLib(extension)?.let {
    extensionTypeFromNumber(it)
}

private fun extensionTypeFromNumber(num: Int): ExtensionType? =
    when (num) {
        0 -> null
        1 -> Text
        2 -> Markdown
        else -> throw Exception("Invalid number for ExtensionType: ^$num")
    }

private external fun extensionTypeLib(extension: String): Int

external fun isExtensionSupported(extension: String): Boolean