package org.flexlabs.widgets.dualbattery.widgetsettings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.flurry.android.FlurryAgent;
import org.flexlabs.widgets.dualbattery.BatteryLevel;
import org.flexlabs.widgets.dualbattery.Constants;
import org.flexlabs.widgets.dualbattery.R;

/**
 * Created by IntelliJ IDEA.
 * User: ArtiomChi
 * Date: 17/06/11
 * Time: 18:44
 */
public class WidgetPropertiesFragment extends PreferenceFragment {
    private int appWidgetId;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FlurryAgent.onEvent(Constants.FLURRY_EVENT_WIDGET_SETTINGS);
        WidgetPropertiesActivity activity = (WidgetPropertiesActivity)getActivity();
        activity.localyticsSession.tagEvent(Constants.FLURRY_EVENT_WIDGET_SETTINGS);
        activity.googleTracker.trackPageView(Constants.GOOGLE_PAGE_WIDGET_SETTINGS);
        activity.mpMetrics.event(Constants.FLURRY_EVENT_WIDGET_SETTINGS, null);
        appWidgetId = activity.appWidgetId;

        getPreferenceManager().setSharedPreferencesName(Constants.SETTINGS_PREFIX + appWidgetId);
        addPreferencesFromResource(R.xml.widget_properties_general);
        if (BatteryLevel.getCurrent().is_dockFriendly()) {
            addPreferencesFromResource(R.xml.widget_properties_dock);
        }
    }
}
