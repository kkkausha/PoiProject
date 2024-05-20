package com.example.poiproject

import android.graphics.Color
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarColor
import androidx.car.app.model.CarIcon
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.drawable.IconCompat
import data.PlacesRepository
import data.model.toIntent

class SecondScreen(carContext: CarContext, private var id : Int): Screen(carContext) {
    private var isFavorite = false
    override fun onGetTemplate(): Template {
        var place = PlacesRepository().getPlace(id)?: return MessageTemplate.Builder("Place not found")
            .setHeaderAction(Action.BACK).build()

        var row = Row.Builder().setTitle("co-ordinates").addText("${place.latitude}, ${place.longitude}").build()
        var row1 = Row.Builder().setTitle("Description").addText(place.Description).build()
        val navigateAction = Action.Builder().setTitle("Navigate")
            .setOnClickListener{carContext.startCarApp(place.toIntent(CarContext.ACTION_NAVIGATE))}.build()
       val actionStrip = ActionStrip.Builder()
           .addAction(
               Action.Builder()
                   .setIcon(
                       CarIcon.Builder(
                           IconCompat.createWithResource(carContext,
                               R.drawable.baseline_favorite_24
                           )
                       ).setTint(
                           if (isFavorite) CarColor.RED else CarColor.createCustom(
                               Color.LTGRAY,
                               Color.DKGRAY
                           )
                       ).build()
                   ).setOnClickListener{
                        isFavorite =!isFavorite
                       invalidate()
                   }
                   .build()
           ).build()
        var pane = Pane.Builder().addAction(navigateAction).addRow(row).addRow(row1).build()

        return PaneTemplate.Builder(pane).setTitle(place.location_name).setHeaderAction(Action.BACK)
            .setActionStrip(actionStrip).build()
    }
}