package com.files;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetGrandeRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetGrandeRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
