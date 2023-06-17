package com.grpcx.youtubetomp3.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun PreviewEditText() {
    DefaultEditText() {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultEditText(
    modifier : Modifier =  Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly : Boolean = false,
    enabled : Boolean = true,
    initialValue : String = "",
    onValueChange: (String) -> Unit,
) {

    var currentValue by remember(initialValue) { mutableStateOf(initialValue) }

    OutlinedTextField(
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        readOnly = readOnly,
        enabled = enabled,
        value = currentValue,
        onValueChange = {
            currentValue = it
            onValueChange(currentValue)
        }
    )

}

@Composable
fun DestinationSelector(
    modifier : Modifier =  Modifier,
    selectedDestination : String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onFileSelectorClicked :() -> Unit = {},
    isEnabled : Boolean = true,
) {

    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {

        DefaultEditText(
            modifier = modifier,
            enabled = isEnabled,
            readOnly = false,
            initialValue = selectedDestination,
            leadingIcon = leadingIcon,
            onValueChange = {}
        )

        Box(modifier = Modifier
            .matchParentSize()
            .clickable(
                enabled = true,
                onClick = onFileSelectorClicked
            )
        )
    }
}

