package com.files;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetMedianoRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetMedianoRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
