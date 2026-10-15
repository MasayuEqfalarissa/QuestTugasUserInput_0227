package com.example.tugasuserinput_prak4

import android.widget.Button
import android.widget.Space
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun FormBiodata(modifier: Modifier = Modifier) {
    var textNamaDepan by remember { mutableStateOf("") }
    var textNamaBelakang by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    var textTglLahir by remember { mutableStateOf("") }
    var textExperience by remember { mutableStateOf("") }

    var isAndroidChecked by remember { mutableStateOf(false) }
    var isUiUxChecked by remember { mutableStateOf(false) }
    var isWebChecked by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showResultDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val msgSuccess = stringResource(id = R.string.snackbar_message)


    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                            textTglLahir = formatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(text = stringResource(R.string.btn_batal))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showResultDialog) {
        LaunchedEffect (Unit) {
            delay(5000)
            showResultDialog = false
        }

        val selectedSkills = mutableListOf<String>()
        if (isAndroidChecked) selectedSkills.add(stringResource(R.string.android))
        if (isUiUxChecked) selectedSkills.add(stringResource(R.string.UiUx))
        if (isWebChecked) selectedSkills.add(stringResource(R.string.web))
        val skillText = selectedSkills.joinToString(", ")

        AlertDialog(
            onDismissRequest = { showResultDialog = false },
            title = {
                Text(
                    text = "Data Biodata Anda",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(text = "Nama: $textNamaDepan $textNamaBelakang")
                    Text(text = "Email: $textEmail")
                    Text(text = "Tgl Lahir: $textTglLahir")
                    Text(text = "Experience: $textExperience")
                    Text(text = "Skill: $skillText")
                }
            },
            confirmButton = {
                TextButton(onClick = { showResultDialog = false }) {
                    Text(text = stringResource(R.string.btn_ok))
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            Image(
                painter = painterResource(R.drawable.nailong),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .align(Alignment.TopCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(200.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.corner_radius),
                        topEnd = dimensionResource(R.dimen.corner_radius),
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.background)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_large)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.title),
                            color = colorResource(R.color.title_color),
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                        Text(
                            text = stringResource(R.string.subtitle),
                            color = colorResource(R.color.subtitle_color),
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                        ) {
                            OutlinedTextField(
                                value = textNamaDepan,
                                onValueChange = { textNamaDepan = it },
                                label = { Text(text = stringResource(R.string.nama_depan)) },
                                singleLine = true,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = textNamaBelakang,
                                onValueChange = { textNamaBelakang = it },
                                label = { Text(text = stringResource(R.string.nama_belakang)) },
                                singleLine = true,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                        OutlinedTextField(
                            value = textEmail,
                            onValueChange = { textEmail = it },
                            label = { Text(text = stringResource(R.string.email)) },
                            singleLine = true,
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
                        ) {
                            // Kolom Tgl Lahir dengan Box agar bisa diklik memunculkan kalender tanpa error
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { showDatePicker = true }
                            ) {
                                OutlinedTextField(
                                    value = textTglLahir,
                                    onValueChange = {},
                                    readOnly = true,
                                    enabled = false, // Menonaktifkan interaksi teks bawaan agar fokus sepenuhnya ke klik dialog
                                    label = { Text(text = stringResource(R.string.tgl_lahir)) },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.icon_calendar),
                                            contentDescription = null
                                        )
                                    },
                                    singleLine = true,
                                    shape = MaterialTheme.shapes.medium,
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }

                            OutlinedTextField(
                                value = textExperience,
                                onValueChange = { textExperience = it },
                                label = { Text(text = stringResource(R.string.experience)) },
                                singleLine = true,
                                shape = MaterialTheme.shapes.medium,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                        Text(
                            text = stringResource(R.string.skill),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = isAndroidChecked,
                                    onCheckedChange = { isAndroidChecked = it }
                                )
                                Text(text = stringResource(R.string.android))
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = isUiUxChecked,
                                    onCheckedChange = { isUiUxChecked = it }
                                )
                                Text(text = stringResource(R.string.UiUx))
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = isWebChecked,
                                    onCheckedChange = { isWebChecked = it }
                                )
                                Text(text = stringResource(R.string.web))
                            }
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    showResultDialog = true
                                    delay(5000) // Tampil selama tepat 5 detik
                                    showResultDialog = false
                                }
                            },
                            shape = MaterialTheme.shapes.medium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.button_color)
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.proceed),
                                color = colorResource(id = R.color.button_text_color)
                            )
                        }
                    }
                }
            }
        }
    }
}

