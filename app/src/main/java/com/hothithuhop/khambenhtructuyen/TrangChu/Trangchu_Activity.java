package com.hothithuhop.khambenhtructuyen.TrangChu;

import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.hothithuhop.khambenhtructuyen.Datkham_Activity;
import com.hothithuhop.khambenhtructuyen.Dienthongtinbenhnhan_Activity;
import com.hothithuhop.khambenhtructuyen.Fragment_dangnhapdangky.DangNhapDangKi_Activity;
import com.hothithuhop.khambenhtructuyen.Lichsukham.HistoryBill;
import com.hothithuhop.khambenhtructuyen.Nhanphieukhambenh_Activity;
import com.hothithuhop.khambenhtructuyen.R;
import com.hothithuhop.khambenhtructuyen.model.Notification;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import com.hothithuhop.khambenhtructuyen.CacVanDeThuongGap.cacvandethuonggap_Activity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Trangchu_Activity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView tv_call1, tv_call2;

    Toolbar toolbar;


    private Button btn_datlichkhamngay;
    private TextView tv_qlhoso, tv_cacvande, tv_qlphieukham;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        anhxa();
        //code drawer navigation
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionToolBar();
        GetHD(Integer.parseInt(readID()));

        //sự kiện click item trong drawer navifation
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.number:
                        startActivity(new Intent(Trangchu_Activity.this, Nhanphieukhambenh_Activity.class));
                        break;
                    case R.id.info:
                        startActivity(new Intent(Trangchu_Activity.this, Dienthongtinbenhnhan_Activity.class));
                        break;
                    case R.id.logout:
                        startActivity(new Intent(Trangchu_Activity.this, DangNhapDangKi_Activity.class));
                        finish();
                        break;
                    case R.id.history:
                        startActivity(new Intent(Trangchu_Activity.this, HistoryBill.class));
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START); //đóng drawer navigation khi click item
                return true;
            }
        });

        SliderView sliderView = findViewById(R.id.img_view);
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.anhkham);
        images.add(R.drawable.dangky);
        images.add(R.drawable.dangnhap);
        imageSlideAdapter myAdapter = new imageSlideAdapter(images);
        sliderView.setSliderAdapter(myAdapter);
        sliderView.setAutoCycle(true);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);

        btn_datlichkhamngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trangchu_Activity.this, Datkham_Activity.class);
                startActivity(intent);
            }
        });
        tv_qlhoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trangchu_Activity.this, Dienthongtinbenhnhan_Activity.class);
                startActivity(intent);
            }
        });
        tv_cacvande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trangchu_Activity.this, cacvandethuonggap_Activity.class);
                startActivity(intent);
            }
        });
        tv_qlphieukham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trangchu_Activity.this, Nhanphieukhambenh_Activity.class);
                startActivity(intent);
            }
        });

        tv_call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:19007179"));
                startActivity(intent);
            }
        });
        tv_call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:19002267"));
                startActivity(intent);
            }
        });
    }

    //khai báo drawer navigation
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_qlhoso = (TextView) findViewById(R.id.tv_qlhoso);
        tv_qlphieukham = (TextView) findViewById(R.id.tv_qlphieukham);
        tv_cacvande = (TextView) findViewById(R.id.tv_cacvande);
        btn_datlichkhamngay = (Button) findViewById(R.id.btn_datlichkhamngay);
        tv_call1 = (TextView) findViewById(R.id.tv_call1);
        tv_call2 = (TextView) findViewById(R.id.tv_call2);
    }
    private void GetHD(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetHoadon(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseUser, this::handleError)
        );
    }

    private void handleError(Throwable throwable) {
        Toast.makeText(this, "ko lấy dc", Toast.LENGTH_SHORT).show();
    }

    private void handleResponseUser(Notification infors) {
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        SharedPreferences.Editor editor = pr.edit();
        try {
            if (infors.getStatus() == 1){
                editor.putString("HoaDon",infors.getId()+"");
                editor.commit();
            } else {
                editor.putString("HoaDon","0");
                editor.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readID() {
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        String id = pr.getString("idUser", "");
        return id;
    }
}