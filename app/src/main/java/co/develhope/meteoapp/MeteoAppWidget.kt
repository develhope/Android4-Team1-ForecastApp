package co.develhope.meteoapp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import co.develhope.meteoapp.sharedpreferences.SharedImplementation
import com.google.gson.Gson

class MeteoAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)

        }

    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.

    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val sharedImplementation = SharedImplementation(context, Gson())
    val views = RemoteViews(context.packageName, R.layout.meteo_app_widget)
    views.setTextViewText(R.id.temperatureTextView, sharedImplementation.getTemperature())
    views.setTextViewText(R.id.cityTextView, sharedImplementation.getSelectedCity()?.name.orEmpty())
    views.setImageViewResource(R.id.sunIcon, sharedImplementation.getIconSun())
    appWidgetManager.updateAppWidget(appWidgetId, views)


}