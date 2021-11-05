package com.hothithuhop.khambenhtructuyen.Lichsukham;

import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.dismissProgressDialog;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.showProgressDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hothithuhop.khambenhtructuyen.R;
import com.hothithuhop.khambenhtructuyen.TrangChu.Trangchu_Activity;
import com.hothithuhop.khambenhtructuyen.model.BillResponse;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryBill extends AppCompatActivity {
    private RecyclerView rcllichukham;
    private ImageButton img_back_licsukham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_bill);
        rcllichukham = findViewById(R.id.rcllichukham);
        img_back_licsukham = findViewById(R.id.img_back_licsukham);
        showProgressDialog(HistoryBill.this, "Đang tải dữ liệu... ");
        getHistory(Integer.parseInt(readID()));

        img_back_licsukham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HistoryBill.this, Trangchu_Activity.class));
            }
        });
    }
    private void getHistory(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.HistoryBill(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(ArrayList<BillResponse> arrayList) {
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rcllichukham.setLayoutManager(linearLayoutManager);

            BillAdapter adapter = new BillAdapter(HistoryBill.this, arrayList);
            rcllichukham.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }

    private void handleError(Throwable error) {
        dismissProgressDialog();
        Toast.makeText(HistoryBill.this, "Lỗi: "+ error, Toast.LENGTH_SHORT).show();
    }
    private String readID() {
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE); //file này ở chế độ RIÊNG TƯ đối với các ứng dụng khác có trong điện thoại
        String id = pr.getString("idUser", "");
        return id;
    }
}