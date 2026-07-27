package com.example.soundnest.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.soundnest.ui.theme.LightBlack

@Composable
fun AppAlertDialogBox(
    title: String,
    message: String,
    btnText: String,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = title) },
        text = { Text(text = message) },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray.copy(alpha = 0.7f),
                )
            ) { Text(text = "Cancel") }
        },
        confirmButton = {
            TextButton(
                onClick = { onDelete() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.5f)
                )
            ) { Text(text = btnText) }
        },
        containerColor = LightBlack
    )

}