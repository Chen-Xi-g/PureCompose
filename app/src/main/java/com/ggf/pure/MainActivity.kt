package com.ggf.pure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ggf.pure.ui.theme.PureComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PureComposeTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                NavigationComponent(
                    uiState = uiState,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}

@Composable
fun NavigationComponent(
    uiState: MainUiState,
    onAction: (MainAction) -> Unit = { }
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Gallery",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Drawer"
                        )
                    }
                }
                uiState.drawerMenu.forEachIndexed{ index, menu ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = menu.icon,
                                contentDescription = menu.title
                            )
                        },
                        label = { Text(text = menu.title, style = MaterialTheme.typography.bodyMedium) },
                        selected = menu.isSelected,
                        onClick = {
                            onAction(MainAction.SelectMenuItem(index))
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        MainContent(
            menuItem = uiState.drawerMenu.find { it.isSelected },
            onNavigationIconClick = {
                scope.launch{
                    if (drawerState.isClosed) {
                        drawerState.open()
                    } else {
                        drawerState.close()
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    menuItem: DrawerMenuItem?,
    onNavigationIconClick: () -> Unit = { }
){
    if (menuItem == null) return
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "PureCompose",
                            style = MaterialTheme.typography.titleLarge
                        )
                        if (menuItem.title.isNotEmpty()){
                            Text(
                                text = menuItem.title,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigationIconClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = "Gallery"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        HomeScreen(
            menuId = menuItem.id,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}