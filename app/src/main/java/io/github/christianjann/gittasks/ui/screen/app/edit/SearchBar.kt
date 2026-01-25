package io.github.christianjann.gittasks.ui.screen.app.edit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.github.christianjann.gittasks.R

/**
 * Search bar component for searching within a note.
 * Shows search input, match count (e.g., "3/15"), and next/previous navigation.
 */
@Composable
fun NoteSearchBar(
    isVisible: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    currentMatchIndex: Int,
    totalMatches: Int,
    onNextMatch: () -> Unit,
    onPreviousMatch: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    
    // Request focus when search bar becomes visible
    LaunchedEffect(isVisible) {
        if (isVisible) {
            focusRequester.requestFocus()
        }
    }
    
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Search input field
            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                ),
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onNextMatch() }),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_in_note),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                        )
                    }
                    innerTextField()
                }
            )
            
            // Match count indicator (e.g., "3/15")
            if (searchQuery.isNotEmpty()) {
                Text(
                    text = if (totalMatches > 0) {
                        "${currentMatchIndex + 1}/$totalMatches"
                    } else {
                        "0/0"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
            
            // Previous match button
            IconButton(
                onClick = onPreviousMatch,
                enabled = totalMatches > 0,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.previous_match),
                    modifier = Modifier.size(20.dp)
                )
            }
            
            // Next match button
            IconButton(
                onClick = onNextMatch,
                enabled = totalMatches > 0,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = stringResource(R.string.next_match),
                    modifier = Modifier.size(20.dp)
                )
            }
            
            // Close button
            IconButton(
                onClick = onClose,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_search),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * Find all match positions of a search query in text (case-insensitive).
 * Returns a list of start indices where matches occur.
 */
fun findMatchPositions(text: String, query: String): List<Int> {
    if (query.isEmpty()) return emptyList()
    
    val positions = mutableListOf<Int>()
    val lowerText = text.lowercase()
    val lowerQuery = query.lowercase()
    
    var startIndex = 0
    while (true) {
        val index = lowerText.indexOf(lowerQuery, startIndex)
        if (index < 0) break
        positions.add(index)
        startIndex = index + 1
    }
    
    return positions
}
