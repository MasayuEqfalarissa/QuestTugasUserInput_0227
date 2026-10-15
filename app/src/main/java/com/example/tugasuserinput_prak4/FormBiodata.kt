package com.example.tugasuserinput_prak4

import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

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

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val msgSuccess = stringResource(id = R.string.snackbar_message)

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
                modifier = Modifier
                    .fillMaxSize()
                    .height(dimensionResource(R.dimen.spacing_banner))
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
                                onValueChange = { textNamaDepan = it },
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
                    }
                }
            }
        }
    }
}
