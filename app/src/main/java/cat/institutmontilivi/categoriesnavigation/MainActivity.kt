package cat.institutmontilivi.categoriesnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cat.institutmontilivi.categoriesnavigation.Navegacio.CategoriaNavegacio
import cat.institutmontilivi.categoriesnavigation.Navegacio.GrafDeNavegacio
import cat.institutmontilivi.categoriesnavigation.ui.theme.CategoriesNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CategoriesNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Aplicacio()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Aplicacio(content: @Composable () -> Unit = {Text("")})
{
    PantallaPrincipal {

        val controladorDeNavegacio = rememberNavController()
        val navBackStackEntry by controladorDeNavegacio.currentBackStackEntryAsState()
        val rutaActual = navBackStackEntry?.destination?.route?:""
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.navegaci_niuada)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        titleContentColor = MaterialTheme.colorScheme.onSecondary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    navigationIcon = { Image(
                        painterResource(id = R.drawable.ic_launcher_background), contentDescription = "",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.height(32.dp))
                    })
            },
            bottomBar = {
                NavigationBar {
                    CategoriaNavegacio.values().forEach {
                        NavigationBarItem(label = {Text(stringResource(id = it.titol))},
                            selected = rutaActual.contains(it.rutaPrevia),
                            onClick = { controladorDeNavegacio.navigate(it.rutaPrevia)
                            {popUpTo(controladorDeNavegacio.graph.findStartDestination().id){
                                saveState = true
                            }
                                launchSingleTop = true
                                restoreState = true
                            } },
                            icon = { Icon(imageVector = it.icona, contentDescription = stringResource(
                                id = it.titol)
                            ) })
                    }
                }
            }
        ) {
            Box(modifier = Modifier.padding(paddingValues = it)){
                GrafDeNavegacio(controladorDeNavegacio)
            }
        }
    }
}

@Composable
fun PantallaPrincipal(content: @Composable ()->Unit)
{
    CategoriesNavigationTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
            )
        {
            content()
        }
    }
}