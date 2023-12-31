package cat.institutmontilivi.categoriesnavigation.Navegacio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import cat.institutmontilivi.categoriesnavigation.Pantalles.PDetallArbres
import cat.institutmontilivi.categoriesnavigation.Pantalles.PDetallMuntanyes
import cat.institutmontilivi.categoriesnavigation.Pantalles.PDetallRius
import cat.institutmontilivi.categoriesnavigation.Pantalles.PLlistaArbres
import cat.institutmontilivi.categoriesnavigation.Pantalles.PLlistaMuntanyes
import cat.institutmontilivi.categoriesnavigation.Pantalles.PLlistaRius

@Composable
fun GrafDeNavegacio(controladorDeNavegacio: NavHostController = rememberNavController())
{
    NavHost(navController = controladorDeNavegacio,
        startDestination = CategoriaNavegacio.CategoriaArbres.rutaPrevia)
    {
        navigation(
            startDestination = Destinacio.LlistaArbres.rutaBase,
            route = CategoriaNavegacio.CategoriaArbres.rutaPrevia
        )
        {
            composable(route = Destinacio.LlistaArbres.rutaGenerica)
            {
                PLlistaArbres(titol = "Llista de arbres",
                    onContentClick = {id:Int -> controladorDeNavegacio.navigate(Destinacio.DetallArbre.CreaRutaAmbArguments(idArbre = id))})
            }
            composable(
                route = Destinacio.DetallArbre.rutaGenerica,
                arguments = Destinacio.DetallArbre.navArgs)
            {
                val n = it.arguments?.getInt(ArgumentDeNavegacio.ArbresContent.clau)
                requireNotNull(n)
                PDetallArbres(id=n)
            }
        }
        navigation(startDestination = Destinacio.LlistaMuntanyes.rutaBase,
            route = CategoriaNavegacio.CategoriaMuntanyes.rutaPrevia)
        {
            composable(route = Destinacio.LlistaMuntanyes.rutaGenerica)
            {
                PLlistaMuntanyes(titol = "Llista de muntanyes",
                    onContentClick = {id:Int -> controladorDeNavegacio.navigate(Destinacio.DetallMuntanyes.CreaRutaAmbArguments(idMuntanya = id))})
            }
            composable(
                route = Destinacio.DetallMuntanyes.rutaGenerica,
                arguments = Destinacio.DetallMuntanyes.navArgs)
            {
                val n = it.arguments?.getInt(ArgumentDeNavegacio.MuntanyesContent.clau)
                requireNotNull(n)
                PDetallMuntanyes(id=n)
            }
        }
        navigation(startDestination = Destinacio.LlistaRius.rutaBase,
            route = CategoriaNavegacio.CategoriaRius.rutaPrevia)
        {
            composable(route = Destinacio.LlistaRius.rutaGenerica)
            {
                PLlistaRius(titol = "Llista de Rius",
                    onContentClick = {id:Int -> controladorDeNavegacio.navigate(Destinacio.DetallRius.CreaRutaAmbArguments(idRius = id))})
            }
            composable(
                route = Destinacio.DetallRius.rutaGenerica,
                arguments = Destinacio.DetallRius.navArgs)
            {
                val n = it.arguments?.getInt(ArgumentDeNavegacio.RiusContent.clau)
                requireNotNull(n)
                PDetallRius(id=n)
            }
        }
    }
}