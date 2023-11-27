package cat.institutmontilivi.categoriesnavigation.Pantalles

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cat.institutmontilivi.categoriesnavigation.Dades.Rius
import cat.institutmontilivi.categoriesnavigation.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PDetallRius(id: Int){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ){
        val riu = Rius.dades.find{it.id == id}
        Log.d("IANBELMONTE","ID CORRECTA $id")

        if (riu != null) {
            Text(
                stringResource(R.string.nom) + riu.nom, modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(riu.foto)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.riu),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .weight(4F)
            )
            Text(text = stringResource(R.string.id_riu) + riu.id.toString(), modifier = Modifier.align(
                Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(7.dp))

            Text(text = stringResource(R.string.caudal) + riu.caudal.toString(), modifier = Modifier.align(
                Alignment.CenterHorizontally))
            Text(text = stringResource(R.string.longitud) + riu.longitud.toString(), modifier = Modifier.align(
                Alignment.CenterHorizontally))
        }
    }
}