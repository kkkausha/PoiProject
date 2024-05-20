package com.example.poiproject

import android.text.Spannable
import android.text.SpannableString
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.CarLocation
import androidx.car.app.model.Distance
import androidx.car.app.model.DistanceSpan
import androidx.car.app.model.ItemList
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.PlaceListMapTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import data.PlacesRepository
import androidx.car.app.model.Metadata
import androidx.car.app.model.Place
import androidx.car.app.model.PlaceMarker

class MainScreen(carContext: CarContext): Screen(carContext) {
    override fun onGetTemplate(): Template {
        val row = Row.Builder().setTitle("Hello World").build()
        var pane = Pane.Builder().addRow(row).build()
        val placesRepo = PlacesRepository()
        val itemListBuilder = ItemList.Builder().setNoItemsMessage("No palces found")
        placesRepo.getPlaces().forEach{
            itemListBuilder.addItem(
                Row.Builder().setTitle(it.location_name)
                    .addText(SpannableString(" ").apply { setSpan(
                        DistanceSpan.create(Distance.create(Math.random()*100, Distance.UNIT_KILOMETERS)),0,1,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    ) })
                    .setOnClickListener({
                        screenManager.push(SecondScreen(carContext, it.ID))
                    }).setMetadata(
                    Metadata.Builder().setPlace(
                        Place.Builder(CarLocation.create(it.latitude, it.longitude))
                            .setMarker(PlaceMarker.Builder().build())
                            .build()
                    ).build()
                )
                    .build()
            )
        }
        return PlaceListMapTemplate.Builder()
            .setTitle("Places of Interest")
            .setItemList(itemListBuilder.build())
            .build()
        return PaneTemplate.Builder(pane).build()
    }

}