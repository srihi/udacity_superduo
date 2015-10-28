package barqsoft.footballscores;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import barqsoft.footballscores.service.myFetchService;

public class FootballWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int i=0; i<appWidgetIds.length; i++)
        {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.widget_frame_layout, pendingIntent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                views.setRemoteAdapter(R.id.widget_list, new Intent(context, WidgetRemoteViewsService.class));
            }
            else{
                views.setRemoteAdapter(0,R.id.widget_list, new Intent(context, WidgetRemoteViewsService.class));
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                views.setEmptyView(R.id.widget_list, R.id.widget_empty);
            }
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(myFetchService.INTENT_DATA_UPDATED)) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(
                    new ComponentName(context, getClass()));
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list);
        }
    }

}
