package neat_ui.com;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingPreference extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new MainPreferenceFragment()).commit();
    }

    public static class MainPreferenceFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preference_setting);

            // gallery EditText change listener
//            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_gallery_name)));

            EditTextPreference numberPreference = findPreference(getString(R.string.speed));

            if (numberPreference != null) {
                numberPreference.setOnBindEditTextListener(
                        new EditTextPreference.OnBindEditTextListener() {
                            @Override
                            public void onBindEditText(@NonNull EditText editText) {
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                            }
                        });
            }

            Preference battery = findPreference(getString(R.string.battery));
            battery.setSummary(Integer.toString(Robot.getInstance().getBattery()));

            // feedback preference click listener
//            Preference myPref = findPreference(getString(R.string.speed));
//            myPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//                public boolean onPreferenceClick(Preference preference) {
//                    return true;
//                }
//            });
        }
    }
}
