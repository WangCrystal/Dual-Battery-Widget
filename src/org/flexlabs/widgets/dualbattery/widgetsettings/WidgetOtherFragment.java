package org.flexlabs.widgets.dualbattery.widgetsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import com.flurry.android.FlurryAgent;
import org.flexlabs.widgets.dualbattery.Constants;
import org.flexlabs.widgets.dualbattery.R;

import java.io.File;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ArtiomChi
 * Date: 18/06/11
 * Time: 15:30
 */
public class WidgetOtherFragment extends PreferenceFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FlurryAgent.onEvent(Constants.FLURRY_EVENT_WIDGET_OTHER);
        WidgetPropertiesActivity activity = (WidgetPropertiesActivity)getActivity();
        activity.localyticsSession.tagEvent(Constants.FLURRY_EVENT_WIDGET_OTHER);
        activity.googleTracker.trackPageView(Constants.GOOGLE_PAGE_WIDGET_OTHER);
        activity.mpMetrics.event(Constants.FLURRY_EVENT_WIDGET_OTHER, null);
        addPreferencesFromResource(R.xml.widget_properties_other);

        File crashReport = new File(getActivity().getFilesDir(), Constants.STACKTRACE_FILENAME);
        Preference pref = findPreference(WidgetPropertiesActivity.KEY_REPORT);
        if (!crashReport.exists()) {
            pref.setEnabled(false);
        } else {
            pref.setSummary(
                    getString(R.string.propTitle_SendCrashReport_summaryPrefix) + " " +
                    new Date(crashReport.lastModified()).toString());
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        return
            ((WidgetPropertiesActivity)getActivity()).onPreferenceClicked(preference.getKey()) ||
            super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
