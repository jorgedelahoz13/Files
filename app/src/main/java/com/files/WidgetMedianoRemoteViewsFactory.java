package com.files;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class WidgetMedianoRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    String[] longitud;

    private Context context = null;
    private int appWidgetId;

    public WidgetMedianoRemoteViewsFactory(Context context, Intent intent) {
        this.context=context;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        String a1 = "";
        longitud = new String[]{a1};
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return(longitud.length);
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews item = new RemoteViews(context.getPackageName(), R.layout.item_lista);

        int currentNightMode = context.getApplicationContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                item.setInt(R.id.fondo, "setBackgroundColor", Color.parseColor("#FFFFFF"));
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                item.setInt(R.id.fondo, "setBackgroundColor", Color.parseColor("#191C19"));
                break;
        }

        return(item);
    }

    @Override
    public RemoteViews getLoadingView() {
        return(null);
    }

    @Override
    public int getViewTypeCount() {
        return(1);
    }

    @Override
    public long getItemId(int position) {
        return(position);
    }

    @Override
    public boolean hasStableIds() {
        return(true);
    }

    @Override
    public void onDataSetChanged() {
        String a1 = "";
        longitud = new String[]{a1};
    }

}
