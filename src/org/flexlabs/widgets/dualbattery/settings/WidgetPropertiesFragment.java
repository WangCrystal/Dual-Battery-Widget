package org.flexlabs.widgets.dualbattery.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.flurry.android.FlurryAgent;
import org.flexlabs.widgets.dualbattery.service.BatteryMonitorService;
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
        appWidgetId = activity.appWidgetId;

        getPreferenceManager().setSharedPreferencesName(Constants.SETTINGS_PREFIX + appWidgetId);
        addPreferencesFromResource(R.xml.widget_properties_general);
        if (BatteryMonitorService.isDockSupported(getActivity())) {
            addPreferencesFromResource(R.xml.widget_properties_dock);
        }
    }
}
