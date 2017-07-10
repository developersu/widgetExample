package widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.blogspot.developersu.we.widgetexample.R;

public class MyWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget_main);
            view.setTextViewText(R.id.widgetText, "Hello widget!");
            appWidgetManager.updateAppWidget(appWidgetId, view);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Toast.makeText(context, "Widget deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
        AppWidgetManager awm = AppWidgetManager.getInstance(context);
        ComponentName compName = new ComponentName(context, MyWidget.class);
        int[] widgetIds = awm.getAppWidgetIds(compName);
        for (int widgetId : widgetIds) {
            Intent intentBtnPwr = new Intent(context, MyWidgetConfig.class);
            intentBtnPwr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            PendingIntent pi = PendingIntent.getActivity(context, widgetId, intentBtnPwr, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widgetBtn, pi);
            awm.updateAppWidget(widgetId, remoteViews);
        }
    }

}
