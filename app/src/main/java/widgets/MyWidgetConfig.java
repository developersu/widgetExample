package widgets;


import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

import com.blogspot.developersu.we.widgetexample.R;


public class MyWidgetConfig extends Activity {

    EditText EdText;
    AppWidgetManager awManager;
    Context thisContext;
    int awID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_main_config);

        EdText = (EditText)findViewById(R.id.configWidgetText);

        Intent intent = getIntent();
        Bundle bundleExtras = intent.getExtras();
        if (bundleExtras != null){
            awID = bundleExtras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }else
            finish();

        thisContext = this.getApplicationContext();
        awManager = AppWidgetManager.getInstance(thisContext);
        final RemoteViews awRV = new RemoteViews(thisContext.getPackageName(), R.layout.widget_main);

        findViewById(R.id.configWidgetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                awRV.setTextViewText(R.id.widgetText, ((EditText) findViewById(R.id.configWidgetText)).getText().toString());
                //*added
                Intent intentBtnPwr = new Intent(thisContext, MyWidgetConfig.class);
                intentBtnPwr.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
                PendingIntent pi = PendingIntent.getActivity(thisContext, awID, intentBtnPwr, PendingIntent.FLAG_UPDATE_CURRENT);
                awRV.setOnClickPendingIntent(R.id.widgetBtn, pi);
                // added end */
                awManager.updateAppWidget(awID, awRV);

                Intent resultIntent = new Intent();
                resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, awID);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }
}
