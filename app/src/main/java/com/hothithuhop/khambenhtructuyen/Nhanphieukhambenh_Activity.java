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

import com.hothithuhop.khambenhtructuyen.TrangChu.Trangchu_Activity;
import com.hothithuhop.khambenhtructuyen.model.Bacsi;
import com.hothithuhop.khambenhtructuyen.model.Chuyenkhoa;
import com.hothithuhop.khambenhtructuyen.model.Hoadon;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Nhanphieukhambenh_Activity extends AppCompatActivity {
    private Button btn_hoantat_pkb;
    private TextView tv_tenphongkham_pkb, tv_sophieu_ttbn, tv_tenbenhnhan_pkb, tv_ngayvaca_pkb, tv_giokham_pkb, tv_bs, tv_tienkham_pkb, tv_bhyt_pkb;
    int idHD=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanphieukhambenh);
        anhxa();
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        idHD = Integer.parseInt(pr.getString("HoaDon", ""));
        tv_sophieu_ttbn.setText(idHD+"");
        showProgressDialog(this, "Đang xác nhận hóa đơn...");
        GetBill(idHD);
        btn_hoantat_pkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Nhanphieukhambenh_Activity.this, Trangchu_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        btn_hoantat_pkb = (Button) findViewById(R.id.btn_hoantat_pkb);
        tv_tenphongkham_pkb = (TextView) findViewById(R.id.tv_tenphongkham_pkb);
        tv_tenbenhnhan_pkb = (TextView) findViewById(R.id.tv_tenbenhnhan_pkb);
        tv_sophieu_ttbn = (TextView) findViewById(R.id.tv_sophieu_ttbn);
        tv_ngayvaca_pkb = (TextView) findViewById(R.id.tv_ngayvaca_pkb);
        tv_giokham_pkb = (TextView) findViewById(R.id.tv_giokham_pkb);
        tv_bs = (TextView) findViewById(R.id.tv_bs);
        tv_tienkham_pkb = (TextView) findViewById(R.id.tv_tienkham_pkb);
        tv_bhyt_pkb = (TextView) findViewById(R.id.tv_bhyt_pkb);
    }


    private void GetBill(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.Bill(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }
    private void handleResponse(ArrayList<Hoadon> p) {
        dismissProgressDialog();
        tv_tenphongkham_pkb.setText(p.get(0).getPhongkham());
        tv_tenbenhnhan_pkb.setText(p.get(0).getHotenUser());
        tv_ngayvaca_pkb.setText(p.get(0).getNgaykham());
        tv_giokham_pkb.setText(p.get(0).getGiokham());
        tv_bs.setText(p.get(0).getTenBS());
        tv_tienkham_pkb.setText(p.get(0).getTongtien()+" VNĐ");
        tv_bhyt_pkb.setText(p.get(0).getBhyt());
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
        Toast.makeText(this, "Chưa có thông tin", Toast.LENGTH_SHORT).show();
    }
}