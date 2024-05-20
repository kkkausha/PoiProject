package data.model

import android.content.Intent
import androidx.core.net.toUri

data class Place(
    var ID: Int, var location_name: String, var latitude: Double,
    var longitude: Double, var Description: String, var Distance: Double,
    var Hours: Double, var ticket_price: Double
)

fun Place.toIntent(action : String): Intent{
    return Intent(action).apply {
        data = "geo:${latitude},${longitude}".toUri()  }
}
