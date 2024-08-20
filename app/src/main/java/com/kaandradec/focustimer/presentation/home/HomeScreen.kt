package com.kaandradec.focustimer.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kaandradec.focustimer.R
import com.kaandradec.focustimer.domain.model.TimerTypeEnum
import com.kaandradec.focustimer.presentation.components.AutoResizedText
import com.kaandradec.focustimer.presentation.components.BorderedIcon
import com.kaandradec.focustimer.presentation.components.CircleDot
import com.kaandradec.focustimer.presentation.components.CustomButton
import com.kaandradec.focustimer.presentation.components.InformationItem
import com.kaandradec.focustimer.presentation.components.TimerTypeItem
import com.kaandradec.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
    // 3 Formas de manejar estados en compose
    // val mutableState = remember { mutableStateOf(0)}
    // val (state, setState) = remember { mutableStateOf(0)}
    // var valor by remember { mutableStateOf(0)}

    val timeState by remember { mutableStateOf(viewModel.timerValueState) }
    val timerTypeState by remember { mutableStateOf(viewModel.timerTypeState) }
    val roundsState by remember { mutableStateOf(viewModel.roundsState) }
    val todayTimeState by remember { mutableStateOf(viewModel.todayTimeState) }

    val lifecycleOwner = LocalLifecycleOwner.current

    // LaunchedEffect: Se ejecuta una sola vez cuando el composable es lanzado (STARTED)
    LaunchedEffect(viewModel) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getTimerSessionByDate()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(FocusTimerTheme.dimens.paddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier.size(FocusTimerTheme.dimens.iconSizeNormal),
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        AutoResizedText(
            text = "Focus Timer",
            // Con copy podemos modificar el estilo previo
            textStyle = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        Row {
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary)
        }
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        TimerSession(
            timer = viewModel.millisToMinutes(timeState.value),
            onIncreaseTap = {
                viewModel.onIncreaseTime()
            },
            onDecreaseTap = {
                viewModel.onDecreaseTime()
            }
        )
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        TimerTypeSession(
            type = timerTypeState.value,
            onTap = { type ->
                viewModel.onUpdateType(type)
            }
        )
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomButton(
                text = "Start",
                textColor = MaterialTheme.colorScheme.surface,
                buttonColors = MaterialTheme.colorScheme.primary,
                onTap = {
                    viewModel.onStartTimer()
                }
            )
            CustomButton(
                text = "Reset",
                textColor = MaterialTheme.colorScheme.primary,
                buttonColors = MaterialTheme.colorScheme.surface,
                onTap = {
                    viewModel.onCancelTimer(reset = true)
                }
            )
        }
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        InformationSession(
            modifier = Modifier.weight(1f),
            round = roundsState.value.toString(),
            time = viewModel.millisToHours(todayTimeState.value)
        )
    }
}

@Composable
fun TimerSession(
    modifier: Modifier = Modifier,
    timer: String,
    onIncreaseTap: () -> Unit = {},
    onDecreaseTap: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f), // Ocupa el 50% del espacio disponible
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BorderedIcon(icon = R.drawable.ic_minus, onTap = onDecreaseTap)
//            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
        AutoResizedText(
            text = timer,
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .align(Alignment.CenterVertically),
            textStyle = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )
        Column(
            modifier = Modifier.weight(1f), // Ocupa el 50% del espacio disponible
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BorderedIcon(icon = R.drawable.ic_plus, onTap = onIncreaseTap)
//            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
    }
}

@Composable
fun TimerTypeSession(
    modifier: Modifier = Modifier,
    type: TimerTypeEnum,
    onTap: (TimerTypeEnum) -> Unit = {}
) {
    val gridCount = 3
    val itemsSpacing = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal)
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(FocusTimerTheme.dimens.spacerLarge),
        columns = GridCells.Fixed(gridCount),
        horizontalArrangement = itemsSpacing,
        verticalArrangement = itemsSpacing,
    ) {
        items(
            TimerTypeEnum.values(),
            key = { it.title }
        ) {
            TimerTypeItem(
                text = it.title,
                textColor = if (type == it)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.secondary,
                onTap = { onTap(it) }
            )
        }
    }
}

@Composable
fun InformationSession(
    modifier: Modifier = Modifier,
    round: String,
    time: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = modifier
                .align(Alignment.BottomCenter),
        ) {
            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = round,
                label = "rounds"
            )
            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            InformationItem(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = time,
                label = "time"
            )
        }
    }
}

@Preview(name = "HomeScreenPreview", showBackground = true)
@Composable
fun HomeScreenPreview() {
    FocusTimerTheme {
        HomeScreen()
    }
}