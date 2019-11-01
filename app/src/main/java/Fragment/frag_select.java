package Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.kr.itforone.shiningtour.R;


public class frag_select extends Fragment {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    ImageView bru_activity,bru_extra,bru_trafic,bru_study,bru_foods,bru_hotel,bru_place,bru_shoping;;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.content_main,container,false);

        bru_activity = (ImageView)view.findViewById(R.id.bru_activity);
        bru_shoping = (ImageView)view.findViewById(R.id.bru_shoping);
        bru_place = (ImageView)view.findViewById(R.id.bru_place);
        bru_foods = (ImageView)view.findViewById(R.id.bru_foods);
        bru_hotel = (ImageView)view.findViewById(R.id.bru_hotel);
        bru_extra = (ImageView)view.findViewById(R.id.bru_extra);
        bru_trafic = (ImageView)view.findViewById(R.id.bru_trafic);
        bru_study = (ImageView)view.findViewById(R.id.bru_study);
        bru_extra = (ImageView)view.findViewById(R.id.bru_extra);
//        this.bru_activity = (ImageView)view.findViewById(R.id.bru_activity);
//        this.bru_activity = (ImageView)view.findViewById(R.id.bru_activity);

        SharedPreferences pref = getActivity().getSharedPreferences("pref_lang", getActivity().MODE_PRIVATE);
        String flag_lang = pref.getString("lang", "");

        if(!flag_lang.equals("")) {
            if (flag_lang.equals("kor")) {
                Glide.with(this).load(R.drawable.main_icon01).into(bru_activity);
                Glide.with(this).load(R.drawable.main_icon02).into(bru_foods);
                Glide.with(this).load(R.drawable.main_icon03).into(bru_shoping);
                Glide.with(this).load(R.drawable.main_icon04).into(bru_hotel);
                Glide.with(this).load(R.drawable.main_icon05).into(bru_place);
                Glide.with(this).load(R.drawable.main_icon07).into(bru_trafic);
                Glide.with(this).load(R.drawable.main_icon08).into(bru_study);
                Glide.with(this).load(R.drawable.main_icon06).into(bru_extra);
            } else {
                Glide.with(this).load(R.drawable.main_icon01_eng).into(bru_activity);
                Glide.with(this).load(R.drawable.main_icon02_eng).into(bru_foods);
                Glide.with(this).load(R.drawable.main_icon03_eng).into(bru_shoping);
                Glide.with(this).load(R.drawable.main_icon04_eng).into(bru_hotel);
                Glide.with(this).load(R.drawable.main_icon05_eng).into(bru_place);
                Glide.with(this).load(R.drawable.main_icon07_eng).into(bru_trafic);
                Glide.with(this).load(R.drawable.main_icon08_eng).into(bru_study);
                Glide.with(this).load(R.drawable.main_icon06_eng).into(bru_extra);
            }
        }
        return view;
    }
}




