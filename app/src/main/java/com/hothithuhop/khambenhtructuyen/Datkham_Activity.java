package com.hothithuhop.khambenhtructuyen;

import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.dismissProgressDialog;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.showProgressDialog;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.hothithuhop.khambenhtructuyen.TrangChu.Trangchu_Activity;
import com.hothithuhop.khambenhtructuyen.model.Bacsi;
import com.hothithuhop.khambenhtructuyen.model.Chuyenkhoa;
import com.hothithuhop.khambenhtructuyen.model.DatKham;
import com.hothithuhop.khambenhtructuyen.model.InfUser;
import com.hothithuhop.khambenhtructuyen.model.Notification;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Datkham_Activity extends AppCompatActivity {
    private Button btn_datkham;
    private RadioButton rb_1, rb_2;
    private int ID_user;
    LocalDate now;
    Date date1;
    private String id;
    private TextView tv_phongkhamgiokham, tv_ngaydatkham, tv_chuyenkhoa_datkham, tv_tenbacsi,tv_tenbenhnhan, tv_phongkhamg_datkham1;
    private ImageButton tv_phongkhamg_datkham, img_back_lichdatkham, img_chongio_datkham, img_chonlich_datkham, img_chonchuyenkhoa_datkham, img_chonbacsi_datkham;

    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] ds_phongkham = {"Phòng vip", "phòng thường"};
    String phong = "", idKhoa = "", nameKhoa = "", nameBS = "", BHYT = "";
    int idBacsi, n = 0;
    int TienKham;
    ArrayList<Chuyenkhoa> dschuyenkhoa;
    ArrayList<Bacsi> dsbacsi;

    int t1Hour, t1Minute, t2Hour, t2Minute; //giờ
    DatePickerDialog.OnDateSetListener setListener; //lịch

    private void GetUser(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetInf(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseUser, this::handleError)
        );
    }
    private void handleResponseUser(InfUser infors) {
        try {
            tv_tenbenhnhan.setText(infors.getHoten());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datkham);
        anhxa();

        GetUser(Integer.parseInt(readID()));
        rb_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BHYT = "Không";
            }
        });
        rb_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BHYT = "Có";
            }
        });
        img_chonchuyenkhoa_datkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(Datkham_Activity.this);
                builder.setTitle("Chọn Chuyên khoa");
                String[] ds = new String[dschuyenkhoa.size()];
                int i = 0;
                for (Chuyenkhoa chuyenkhoa : dschuyenkhoa) {
                    ds[i] = chuyenkhoa.getChuyenkhoa();
                    i++;
                }
                builder.setSingleChoiceItems(ds, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        idKhoa = String.valueOf(dschuyenkhoa.get(which).getId());
                        nameKhoa = dschuyenkhoa.get(which).getChuyenkhoa();
                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_chuyenkhoa_datkham.setText(nameKhoa);
                        showProgressDialog(Datkham_Activity.this, "...");
                        GetBS(Integer.parseInt(idKhoa));

                    }
                });
                builder.setNegativeButton("thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
        img_chonbacsi_datkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(Datkham_Activity.this);
                builder.setTitle("Chọn Bác Sĩ");
                String[] dsbs = new String[dsbacsi.size()];
                int i = 0;
                for (Bacsi bacsi : dsbacsi) {
                    dsbs[i] = bacsi.getTenBS();
                    i++;
                }
                builder.setSingleChoiceItems(dsbs, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameBS = dsbacsi.get(which).getTenBS();
                        idBacsi=dsbacsi.get(which).getId_bs();
                        TienKham=dsbacsi.get(which).getGiaTien();
                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_tenbacsi.setText(nameBS);
                    }
                });
                builder.setNegativeButton("thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
        tv_phongkhamg_datkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(Datkham_Activity.this);
                builder.setTitle("Chọn phòng khám");
                builder.setSingleChoiceItems(ds_phongkham, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phong = ds_phongkham[which];
                    }
                });

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         tv_phongkhamg_datkham1.setText(phong);
                    }
                });
                builder.setNegativeButton("thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
        btn_datkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID_user=(Integer.parseInt(readID()));
                now = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                now.format(formatter);
                Date date = java.sql.Date.valueOf(String.valueOf(now));
                if (BHYT.equals("") || tv_ngaydatkham.getText().length() == 0 || tv_chuyenkhoa_datkham.getText().length() == 0
                        || tv_tenbacsi.getText().length() == 0
                        || tv_phongkhamg_datkham1.getText().length() == 0 || tv_phongkhamgiokham.getText().length() == 0)
                    Toast.makeText(Datkham_Activity.this, "Vòng lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                else if (date1.compareTo(date) <=0 )
                    Toast.makeText(Datkham_Activity.this, "Vui lòng chọn ngày khác", Toast.LENGTH_SHORT).show();
                else {
                    savedata(idBacsi+"",TienKham+"");
                    startActivity(new Intent(Datkham_Activity.this, Chonphuongthucthanhtoan_Activity.class));                }
            }
        });
        img_back_lichdatkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Datkham_Activity.this, Trangchu_Activity.class);
                startActivity(intent);
            }
        });
        img_chongio_datkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog( //time
                        Datkham_Activity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hour, t1Minute);//set hour,munite
                                tv_phongkhamgiokham.setText(DateFormat.format("hh:m aa", calendar));
                            }
                        }, 12, 0, false
                );
                timePickerDialog.updateTime(t1Hour, t1Minute); //selendar selected, show
                timePickerDialog.show();
            }
        });

        Calendar calendarSchedule = Calendar.getInstance();
        final int year = calendarSchedule.get(Calendar.YEAR);
        final int month = calendarSchedule.get(Calendar.MONTH);
        final int day = calendarSchedule.get(Calendar.DAY_OF_MONTH);

        img_chonlich_datkham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Datkham_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        tv_ngaydatkham.setText(date);
                        try {
                            date1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }}, year, month, day
                );
                datePickerDialog.show();
            }
        });
    }

    private void anhxa() {
        btn_datkham = (Button) findViewById(R.id.btn_datkham);
        img_back_lichdatkham = (ImageButton) findViewById(R.id.img_back_lichdatkham);
        img_chongio_datkham = (ImageButton) findViewById(R.id.img_chongio_datkham);
        tv_phongkhamgiokham = (TextView) findViewById(R.id.tv_phongkhamgiokham);
        img_chonlich_datkham = (ImageButton) findViewById(R.id.img_chonlich_datkham);
        tv_ngaydatkham = (TextView) findViewById(R.id.tv_ngaydatkham);
        img_chonchuyenkhoa_datkham = (ImageButton) findViewById(R.id.img_chonchuyenkhoa_datkham);
        tv_chuyenkhoa_datkham = (TextView) findViewById(R.id.tv_chuyenkhoa_datkham);
        tv_phongkhamg_datkham1 = (TextView) findViewById(R.id.tv_phongkhamg_datkham1);
        tv_phongkhamg_datkham = (ImageButton) findViewById(R.id.tv_phongkhamg_datkham);
        img_chonbacsi_datkham = (ImageButton) findViewById(R.id.img_chonbacsi_datkham);
        tv_tenbacsi = (TextView) findViewById(R.id.tv_tenbacsi);
        tv_tenbenhnhan = (TextView) findViewById(R.id.tv_tenbenhnhan);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);

        GetCK();
    }

    //get data từ API
    private void GetCK() {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetChuyenkhoa()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseKhoa, this::handleError)

        );
    }
    private void GetBS(int id) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);

        new CompositeDisposable().add(requestInterface.GetDoctor(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseBS, this::handleError)
        );
    }
    private void handleResponseBS(ArrayList<Bacsi> p) {
        dismissProgressDialog();
        dsbacsi = p;
    }
    private void handleResponseKhoa(ArrayList<Chuyenkhoa> p) {
        dschuyenkhoa = p;
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
        Toast.makeText(this, "Lỗi " + throwable.toString(), Toast.LENGTH_SHORT).show();
    }

    private String readID() {
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        id = pr.getString("idUser", "");
        return id;
    }
    private void savedata (String idBacsi, String TienKham){
        SharedPreferences pr = getSharedPreferences("dangnhap", MODE_PRIVATE);
        SharedPreferences.Editor editor = pr.edit();
        editor.putString("idBS",idBacsi);
        editor.putString("BS",tv_tenbacsi.getText().toString());
        editor.putString("ngay",tv_ngaydatkham.getText().toString());
        editor.putString("BHYT", BHYT);
        editor.putString("phong",tv_phongkhamg_datkham1.getText().toString());
        editor.putString("tienKham", TienKham);
        editor.putString("gio", tv_phongkhamgiokham.getText().toString());
        editor.commit();
    }
}