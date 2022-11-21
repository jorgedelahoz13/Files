package com.files;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetPequenoRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetPequenoRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
