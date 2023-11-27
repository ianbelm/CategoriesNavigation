package cat.institutmontilivi.categoriesnavigation.Navegacio

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import cat.institutmontilivi.categoriesnavigation.Dades.Muntanya
import cat.institutmontilivi.categoriesnavigation.R

enum class CategoriaNavegacio(
    val rutaPrevia: String,
    val icona: ImageVector,
    @StringRes val titol: Int
)
{
    CategoriaArbres("CategoriaArbres", Icons.Default.Notifications, R.string.catTree),
    CategoriaMuntanyes("CategoriaMuntanyes", Icons.Default.Home, R.string.catMount),
    CategoriaRius("CategoriaRius", Icons.Default.Share, R.string.catRius)
}
sealed class Destinacio(
    val rutaBase: String,
    val argumentsDeNavegacio: List<ArgumentDeNavegacio> = emptyList()
)
{
    val rutaGenerica = run{
        val clausArguments = argumentsDeNavegacio.map { "{${it.clau}}" }
        listOf(rutaBase)
            .plus(clausArguments)
            .joinToString("/")
    }
    val navArgs = argumentsDeNavegacio.map { it.toNavArgument() }
    object LlistaArbres: Destinacio(CategoriaNavegacio.CategoriaArbres.rutaPrevia + "/CategoriaArbres")
    object LlistaMuntanyes: Destinacio(CategoriaNavegacio.CategoriaMuntanyes.rutaPrevia + "/CategoriaMuntanyes")
    object LlistaRius: Destinacio(CategoriaNavegacio.CategoriaRius.rutaPrevia + "/CategoriaRius")

    object DetallArbre:Destinacio(CategoriaNavegacio.CategoriaArbres.rutaPrevia + "/DetallArbres", listOf(ArgumentDeNavegacio.ArbresContent))
    {
        fun CreaRutaAmbArguments(idArbre:Int) = "$rutaBase/$idArbre"
    }
    object DetallMuntanyes:Destinacio(CategoriaNavegacio.CategoriaMuntanyes.rutaPrevia + "/DetallMuntanyes", listOf(ArgumentDeNavegacio.MuntanyesContent))
    {
        fun CreaRutaAmbArguments(idMuntanya:Int) = "$rutaBase/$idMuntanya"
    }
    object DetallRius:Destinacio(CategoriaNavegacio.CategoriaRius.rutaPrevia + "/DetallRius", listOf(ArgumentDeNavegacio.RiusContent))
    {
        fun CreaRutaAmbArguments(idRius:Int) = "$rutaBase/$idRius"
    }
}
enum class ArgumentDeNavegacio (
    val clau: String,
    val tipus: NavType<*>
)
{
    ArbresContent("ArbresContent", NavType.IntType),
    MuntanyesContent("MuntanyesContent", NavType.IntType),
    RiusContent("RiusContent", NavType.IntType);
    fun toNavArgument(): NamedNavArgument{
        return navArgument(clau){type = tipus}
    }
}