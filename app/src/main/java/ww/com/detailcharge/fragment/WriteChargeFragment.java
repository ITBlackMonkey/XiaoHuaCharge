package ww.com.detailcharge.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ww.com.detailcharge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteChargeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_write_charge, container, false);
    }

}
