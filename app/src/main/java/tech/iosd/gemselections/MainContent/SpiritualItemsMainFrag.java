package tech.iosd.gemselections.MainContent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.iosd.gemselections.Handicrafts.Handicrafts;
import tech.iosd.gemselections.Ittar.Ittar;
import tech.iosd.gemselections.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpiritualItemsMainFrag extends Fragment implements View.OnClickListener {


    public SpiritualItemsMainFrag() {
        // Required empty public constructor
    }

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        return inflater.inflate(R.layout.fragment_spiritual_items_main, container, false);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        Fragment fragment = null;

        switch (id){
            case R.id.khanna_handicraft:
                startActivity(
                        new Intent(getContext(), Handicrafts.class)
                );
                break;
            case R.id.khanna_permeries_banner_cv:
                startActivity(
                        new Intent(getContext(), Ittar.class)
                );
                break;
            case R.id.stone_idols_banner_cv:
                fragment = new MainStoneIdolFragment();

                break;
            case R.id.yantra_banner_cv:
                fragment = new MainYantraFragment();

                break;
            case R.id.saphatic_banner_cv:
                fragment = new MainSphatikFragment();

                break;
            case R.id.japa_banner_cv:
                fragment = new MainJapaMalaFragment();

                break;
            case R.id.kavach_banner_cv:
                fragment = new MainKavachFragment();

                break;
            case R.id.sarva_banner_cv:
                fragment = new SarvaManokaamnaPraptiYugal();

                break;

        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(tech.iosd.gemselections.R.id.container_main, fragment);
            fragmentTransaction.addToBackStack("Main");
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); // Will show transitioning as fragments change
            fragmentTransaction.commit();
        }



    }
}
