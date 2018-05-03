package iliada.dovizapp;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iliada.dovizapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Uygulama_hakkinda_fragment extends Fragment {


    public Uygulama_hakkinda_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uygulama_hakkinda, container, false);


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Uygulama Hakkında");
        setTextviewTypeface(view);

    }

    private void setTextviewTypeface(View view)
    {
        ViewGroup group = (ViewGroup)view.findViewById(R.id.help_content_relative);
        Typeface typefaceOpenSans_Semibold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/open_sans_semi_bold.otf");
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View v = group.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView)v).setTypeface(typefaceOpenSans_Semibold);
            }
        }
    }
}
