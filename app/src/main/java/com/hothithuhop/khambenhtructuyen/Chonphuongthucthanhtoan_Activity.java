package com.hothithuhop.khambenhtructuyen;

import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.dismissProgressDialog;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.showProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.hothithuhop.khambenhtructuyen.model.DatKham;
import com.hothithuhop.khambenhtructuyen.model.Notification;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chonphuongthucthanhtoan_Activity extends AppCompatActivity {
    private Button btn_thanhtoan;
    private ImageButton img_back_chonphuongthucthanhtoan;
    private TextView tv_bacsi, tv_ngaykham, tv_giokham, tv_phong, tv_bhyt, tv_tienkham, tv_phitienich, tv_tongtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonphuongthucthanhtoan);
        anhxa();


        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
                showProgressDialog(Chonphuongthucthanhtoan_Activity.this,"Đang đăng ký...");
                DatKham datKham = new DatKham(Integer.parseInt(pr.getString("idUser", "")),
                        Integer.parseInt(pr.getString("idBS", "")), pr.getString("ngay", ""),
                        pr.getString("BHYT", ""), pr.getString("phong", ""), Integer.parseInt(tv_tongtien.getText().toString()),
                        pr.getString("gio", ""));
                DatKhamAPI(datKham);
            }
        });
        img_back_chonphuongthucthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chonphuongthucthanhtoan_Activity.this, Datkham_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        btn_thanhtoan = (Button) findViewById(R.id.btn_thanhtoan);
        img_back_chonphuongthucthanhtoan = (ImageButton) findViewById(R.id.img_back_chonphuongthucthanhtoan);
        tv_bacsi = (TextView) findViewById(R.id.tv_bacsi);
        tv_ngaykham = (TextView) findViewById(R.id.tv_ngaykham);
        tv_giokham = (TextView) findViewById(R.id.tv_giokham);
        tv_phong = (TextView) findViewById(R.id.tv_phong);
        tv_bhyt = (TextView) findViewById(R.id.tv_bhyt);
        tv_tienkham = (TextView) findViewById(R.id.tv_tienkham);
        tv_tongtien = (TextView) findViewById(R.id.tv_tongtien);
        tv_phitienich = (TextView) findViewById(R.id.tv_phitienich);
        getData();
    }
    private void getData() {
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        tv_bacsi.setText(pr.getString("BS", ""));
        tv_ngaykham.setText(pr.getString("ngay", ""));
        tv_giokham.setText(pr.getString("gio", ""));
        tv_phong.setText(pr.getString("phong", ""));
        tv_bhyt.setText(pr.getString("BHYT", ""));
        tv_tienkham.setText(pr.getString("tienKham", ""));
        if (tv_phong.getText().toString().equals("phòng thường"))
            tv_phitienich.setText("20000"); else tv_phitienich.setText("50000");
            int k = Integer.parseInt(tv_phitienich.getText().toString()) + Integer.parseInt(pr.getString("tienKham", ""));
        tv_tongtien.setText(k+"");
    }
    private void DatKhamAPI(DatKham datKham) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.BookAPI(datKham)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseDK, this::handleError)
        );
    }
    private void handleResponseDK(Notification noti) {
        dismissProgressDialog();
        int idHD = noti.getId();
        try {
            if (noti.getStatus() == 1) {
                savaHD(idHD+"");
                startActivity(new Intent(Chonphuongthucthanhtoan_Activity.this, Nhanphieukhambenh_Activity.class));
            }
            else
            {
                Toast.makeText(this, noti.getNotification(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
        Toast.makeText(this, "Lỗi " + throwable.toString(), Toast.LENGTH_SHORT).show();
    }
    private void savaHD (String id){
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        SharedPreferences.Editor editor = pr.edit();
        editor.putString("HoaDon",id);
        editor.commit();
    }
}