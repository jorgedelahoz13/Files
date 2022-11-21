package com.files;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

public class WidgetPequeno extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context.getApplicationContext());
        ComponentName thisWidget = new ComponentName(context.getApplicationContext(), WidgetPequeno.class);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        if (appWidgetIds != null && appWidgetIds.length > 0) {
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        Intent intent = new Intent(context, WidgetPequenoRemoteViewsService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_pequeno);
        views.setRemoteAdapter(R.id.listView, intent);
        views.setInt(R.id.listView, "setVisibility", View.VISIBLE);

        Intent intent1 = new Intent(context, Archivos.class);
        intent1.putExtra("extraID", "fotos");
        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT  | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.fotos_click, pendingIntent1);
        views.setInt(R.id.fotos, "setColorFilter", Color.parseColor("#757575"));

        Intent intent2 = new Intent(context, Archivos.class);
        intent2.putExtra("extraID", "imagenes");
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 2, intent2, PendingIntent.FLAG_UPDATE_CURRENT  | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.imagenes_click, pendingIntent2);
        views.setInt(R.id.imagenes, "setColorFilter", Color.parseColor("#757575"));

        Intent intent3 = new Intent(context, Archivos.class);
        intent3.putExtra("extraID", "peliculas");
        PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 3, intent3, PendingIntent.FLAG_UPDATE_CURRENT  | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.peliculas_click, pendingIntent3);
        views.setInt(R.id.peliculas, "setColorFilter", Color.parseColor("#757575"));

        Intent intent4 = new Intent(context, Archivos.class);
        intent4.putExtra("extraID", "musica");
        PendingIntent pendingIntent4 = PendingIntent.getActivity(context, 4, intent4, PendingIntent.FLAG_UPDATE_CURRENT  | PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.musica_click, pendingIntent4);
        views.setInt(R.id.musica, "setColorFilter", Color.parseColor("#757575"));


        appWidgetManager.updateAppWidget(appWidgetId, views);


    }


}
