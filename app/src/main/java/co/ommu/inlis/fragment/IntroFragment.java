package co.ommu.inlis.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import co.ommu.inlis.IntroActivity;
import co.ommu.inlis.R;
import co.ommu.inlis.WelcomeDrawerActivity;

public class IntroFragment extends Fragment {

    int position;

    public IntroFragment(int pos) {
        this.position = pos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intro_page, container, false);
        LinearLayout linOne = (LinearLayout) view.findViewById(R.id.linOne);
        LinearLayout linTwo = (LinearLayout) view.findViewById(R.id.linTwo);
        CheckBox cbNotif = (CheckBox) view.findViewById(R.id.cb_agree);


        if (position == 2) {
            linOne.setVisibility(View.GONE);
            linTwo.setVisibility(View.VISIBLE);

            cbNotif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        IntroActivity.statusCheck = true;
                    } else {
                        IntroActivity.statusCheck = false;
                    }
                }
            });


        } else {
            linOne.setVisibility(View.VISIBLE);
            linTwo.setVisibility(View.GONE);
        }

        RelativeLayout btnStart = (RelativeLayout) view.findViewById(R.id.btnMulai);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!IntroActivity.statusCheck) {
                    Toast.makeText(getActivity(), "Belum di check", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(new Intent(getActivity(), WelcomeDrawerActivity.class));
                    getActivity().finish();
                }

                SharedPreferences.Editor editor = IntroActivity.preferences.edit();
                editor.putInt("intro", 1);
                editor.commit();


            }
        });
        return view;

    }
}

