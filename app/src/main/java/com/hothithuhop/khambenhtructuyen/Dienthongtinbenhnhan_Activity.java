package com.hothithuhop.khambenhtructuyen;

import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.dismissProgressDialog;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.showProgressDialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import com.hothithuhop.khambenhtructuyen.TrangChu.Trangchu_Activity;
import com.hothithuhop.khambenhtructuyen.model.Dangky;
import com.hothithuhop.khambenhtructuyen.model.InfUser;
import com.hothithuhop.khambenhtructuyen.model.MessageLogin;
import com.hothithuhop.khambenhtructuyen.model.UpdateUser;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import kotlin.text.UStringsKt;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dienthongtinbenhnhan_Activity extends AppCompatActivity {
    private Button btn_luu_ttbn;
    private RadioButton rb_2, rb_1;
    private ImageButton img_back_ttbn;
    private String id;
    private int sdt;
    String gt = "";
    LocalDate now;
    Date date1;
    private TextView edt_taikhoan_ttbn, edt_namsinh_ttbn1, edt_namsinh_ttbn;
    private EditText edt_matkhau_ttbn, edt_ten_ttbn, edt_cmnd_ttbn, edt_nghenghiep_ttbn, edt_tinhtp_ttbn, edt_sdt_ttbn;
    DatePickerDialog.OnDateSetListener setListener; //lịch

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthongtinbenhnhan);
        anhxa();

        GetUser(Integer.parseInt(readID()));
        rb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gt="Nữ";
            }
        });
        rb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gt="Nam";
            }
        });

        btn_luu_ttbn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                now = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                now.format(formatter);
                Date date = java.sql.Date.valueOf(String.valueOf(now));
                try {
                    date1=new SimpleDateFormat("dd/MM/yyyy").parse(edt_namsinh_ttbn.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (edt_sdt_ttbn.getText().length() ==0 || edt_matkhau_ttbn.getText().length() ==0 || edt_ten_ttbn.getText().length() ==0
                || edt_namsinh_ttbn.getText().length() == 0)
                    Toast.makeText(Dienthongtinbenhnhan_Activity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else
                if (date1.compareTo(date) >= 0 )
                    Toast.makeText(Dienthongtinbenhnhan_Activity.this, "Vui lòng chọn ngày khác", Toast.LENGTH_SHORT).show();
                else
                {
                    sdt = Integer.parseInt(edt_sdt_ttbn.getText().toString());sdt = Integer.parseInt(edt_sdt_ttbn.getText().toString());
                    showProgressDialog(Dienthongtinbenhnhan_Activity.this, "Đang lưu...");
                    UpdateUser update = new UpdateUser( edt_ten_ttbn.getText().toString(),edt_taikhoan_ttbn.getText().toString()
                            , edt_matkhau_ttbn.getText().toString(),sdt, edt_namsinh_ttbn.getText().toString()
                            ,gt,edt_cmnd_ttbn.getText().toString(), edt_nghenghiep_ttbn.getText().toString()
                            , edt_tinhtp_ttbn.getText().toString());
                    Update(Integer.parseInt(readID()), update);
                }
            }
        });
        img_back_ttbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dienthongtinbenhnhan_Activity.this, Taohosobenhnhan_Activity.class);
                startActivity(intent);
            }
        });

        Calendar calendarSchedule = Calendar.getInstance();
        final int year = calendarSchedule.get(Calendar.YEAR);
        final int month = calendarSchedule.get(Calendar.MONTH);
        final int day = calendarSchedule.get(Calendar.DAY_OF_MONTH);

        // cài đặt lịch theo hình vuông
        edt_namsinh_ttbn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Dienthongtinbenhnhan_Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                edt_namsinh_ttbn.setText(date);
            }
        };
    }

    private void GetUser(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetInf(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }
    private void handleResponse(InfUser infors) {
        try {
            edt_taikhoan_ttbn.setText(infors.getTaiKhoan());
            edt_matkhau_ttbn.setText(infors.getMatKhau());
            edt_ten_ttbn.setText(infors.getHoten());
            edt_namsinh_ttbn.setText(infors.getNgaysinh());
            if (infors.getGioitinh().equals("Nam"))
                rb_2.setChecked(true);
            else
                rb_1.setChecked(true);
            edt_cmnd_ttbn.setText(infors.getCMND());
            edt_nghenghiep_ttbn.setText(infors.getNghenghiep());
            edt_tinhtp_ttbn.setText(infors.getDiaChi());
            edt_sdt_ttbn.setText(infors.getSDT()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
        Toast.makeText(Dienthongtinbenhnhan_Activity.this, "Lỗi", Toast.LENGTH_SHORT).show();
    }

    private void Update(int id, UpdateUser update) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.UpdateUser(id, update)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponses, this::handleError)
        );
    }

    private void handleResponses(MessageLogin mess) {
        try {
            if(mess.getStatus() == 1)
                startActivity(new Intent(Dienthongtinbenhnhan_Activity.this, Trangchu_Activity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dismissProgressDialog();
    }


    private void anhxa() {
        btn_luu_ttbn = (Button) findViewById(R.id.btn_luu_ttbn);
        img_back_ttbn = (ImageButton) findViewById(R.id.img_back_ttbn);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        edt_taikhoan_ttbn = findViewById(R.id.edt_taikhoan_ttbn);
        edt_matkhau_ttbn = (EditText) findViewById(R.id.edt_matkhau_ttbn);
        edt_ten_ttbn = (EditText) findViewById(R.id.edt_ten_ttbn);
        edt_namsinh_ttbn = (TextView) findViewById(R.id.edt_namsinh_ttbn);
        edt_namsinh_ttbn1 = (TextView) findViewById(R.id.edt_namsinh_ttbn1);
        edt_cmnd_ttbn = (EditText) findViewById(R.id.edt_cmnd_ttbn);
        edt_nghenghiep_ttbn = (EditText) findViewById(R.id.edt_nghenghiep_ttbn);
        edt_tinhtp_ttbn = (EditText) findViewById(R.id.edt_tinhtp_ttbn);
        edt_sdt_ttbn = (EditText) findViewById(R.id.edt_sdt_ttbn);
    }

    private String readID() {
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE); //file này ở chế độ RIÊNG TƯ đối với các ứng dụng khác có trong điện thoại
        id = pr.getString("idUser", "");
        return id;
    }
}