package tech.iosd.gemselections.AstrologyFragments.Western;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tech.iosd.gemselections.Adapters.WesternHoroscopeHouseAdapter;
import tech.iosd.gemselections.Adapters.WesternSolarReturnPlanetAdapter;
import tech.iosd.gemselections.R;
import tech.iosd.gemselections.Retrofit.AstrologyApiClient;
import tech.iosd.gemselections.Retrofit.AstrologyApiInterface;
import tech.iosd.gemselections.Retrofit.RequestModels.WesternAstrologySimpleRequest;
import tech.iosd.gemselections.Retrofit.ResponseModels.House;
import tech.iosd.gemselections.Retrofit.ResponseModels.SolarReturnHouseResponse;
import tech.iosd.gemselections.Retrofit.ResponseModels.SolarReturnPlanetsResponse;
import tech.iosd.gemselections.Utils.Constants;

/**
 * Created by anubhavmalik on 26/03/18.
 */

public class SolarReturnHouseFragment extends Fragment {
    TextView ascendantTextView, midheavenTextView, vertexTextView;
    RecyclerView solarReturnPlanetRecyclerView;
    WesternHoroscopeHouseAdapter westernSolarReturnPlanetAdapter;
    Retrofit retrofit;
    ArrayList<House> solarReturnPlanetsResponses = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.western_solar_house_return_frag, container, false);

        AstrologyApiClient astrologyApiClient = new AstrologyApiClient();
        retrofit = astrologyApiClient.getRetrofit();

        ascendantTextView = view.findViewById(R.id.solar_house_return_ascendant);
        vertexTextView = view.findViewById(R.id.solar_house_return_vertex);
        midheavenTextView = view.findViewById(R.id.solar_house_return_midheaven);
        solarReturnPlanetRecyclerView = view.findViewById(R.id.western_solar_return_house_recycler_view);

        AstrologyApiInterface astrologyApiInterface = retrofit.create(AstrologyApiInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading ... ");
        progressDialog.show();

        Bundle bundle = this.getArguments();
        bundle = new Bundle();
        if (bundle != null) {

            WesternAstrologySimpleRequest westernAstrologySimpleRequest = new WesternAstrologySimpleRequest(20, 2, 1992, 12, 12, Constants.PRIMARY_LAT, Constants.PRIMARY_LNG, Constants.TIMEZONE);
//                = new WesternAstrologySimpleRequest(bundle.getInt(Constants.PRIMARY_DAY,1)
//                ,bundle.getInt(Constants.PRIMARY_MONTH,1)
//                ,bundle.getInt(Constants.PRIMARY_YEAR,2018)
//                ,bundle.getInt(Constants.PRIMARY_HOUR,1)
//                ,bundle.getInt(Constants.PRIMARY_MIN,1)
//                ,Constants.PRIMARY_LAT
//                ,Constants.PRIMARY_LNG
//                ,Constants.TIMEZONE);


            retrofit2.Call<SolarReturnHouseResponse> call = astrologyApiInterface
                    .getSolarReturnHouse(AstrologyApiInterface.HEADER_TOKEN, westernAstrologySimpleRequest);

            call.enqueue(new Callback<SolarReturnHouseResponse>() {
                @Override
                public void onResponse(retrofit2.Call<SolarReturnHouseResponse> call, Response<SolarReturnHouseResponse> response) {
                    Log.d("TAGGER", "RESPONSE CODE : " + response.code());
                    Log.d("TAGGER", "RESPONSE BODY : " + response.body());
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        Log.d("TAGGER", "RESPONSE SUCCESS");
                        if(response.body() != null){
                            if(String.valueOf(response.body().getAscendant()).equals("0"))
                                ascendantTextView.append(response.body().getAscendant()+"");
                            if(String.valueOf(response.body().getMidheaven()).equals("0"))
                                midheavenTextView.append(response.body().getMidheaven()+"");
                            if (String.valueOf(response.body().getVertex()).equals("0"))
                                vertexTextView.append(response.body().getVertex()+"");
                            solarReturnPlanetsResponses.addAll(response.body().getHouses());
                            westernSolarReturnPlanetAdapter = new WesternHoroscopeHouseAdapter(getContext(),solarReturnPlanetsResponses);
                            solarReturnPlanetRecyclerView.setAdapter(westernSolarReturnPlanetAdapter);
                            solarReturnPlanetRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<SolarReturnHouseResponse> call, Throwable t) {

                    Log.d("TAGGER", "RESPONSE FAILURE");
                    Log.d("TAGGER", t.getMessage());
                    Snackbar.make(solarReturnPlanetRecyclerView, "PLEASE RETRY", Snackbar.LENGTH_INDEFINITE);

                    progressDialog.dismiss();
                }
            });


        } else {
            progressDialog.dismiss();
            Snackbar.make(solarReturnPlanetRecyclerView, "PLEASE RETRY", Snackbar.LENGTH_INDEFINITE);
        }

        return view;

    }
}
