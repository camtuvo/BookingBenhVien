package com.hothithuhop.khambenhtructuyen.Fragment_dangnhapdangky;

import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.dismissProgressDialog;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.showProgressDialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.hothithuhop.khambenhtructuyen.R;
import com.hothithuhop.khambenhtructuyen.Taohosobenhnhan_Activity;
import com.hothithuhop.khambenhtructuyen.model.Dangky;
import com.hothithuhop.khambenhtructuyen.model.MessageLogin;
import com.hothithuhop.khambenhtructuyen.model.RegisterResponse;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Fragment_dangky extends Fragment {
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private int passwordNotVisible=1;
    Button btn_dangky_dangky;
    EditText edt_hoten_dangky, edt_taikhoan_dangky, edt_sdt_dangky, edt_matkhau_dangky, edt_nhapmatkhau_dangky;
    ImageView imgV_hienmk_dangky, imgV_hiennhapmk_dangky;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky, container, false);

        btn_dangky_dangky = view.findViewById(R.id.btn_dangky_dangky);
        edt_hoten_dangky = view.findViewById(R.id.edt_hoten_dangky);
        edt_sdt_dangky = view.findViewById(R.id.edt_sdt_dangky);
        edt_taikhoan_dangky = view.findViewById(R.id.edt_taikhoan_dangky);
        edt_matkhau_dangky = view.findViewById(R.id.edt_matkhau_dangky);
        edt_nhapmatkhau_dangky = view.findViewById(R.id.edt_nhapmatkhau_dangky);
        imgV_hienmk_dangky = view.findViewById(R.id.imgV_hienmk_dangky);
        imgV_hiennhapmk_dangky = view.findViewById(R.id.imgV_hiennhapmk_dangky);

        btn_dangky_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_hoten_dangky.getText().toString().length() == 0 || edt_taikhoan_dangky.getText().toString().length() == 0
                        || edt_sdt_dangky.getText().toString().length() == 0 || edt_matkhau_dangky.getText().toString().length() == 0)
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                else if (!edt_matkhau_dangky.getText().toString().equals(edt_nhapmatkhau_dangky.getText().toString()))
                    Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                else {
                    showProgressDialog(getContext(), "Đang đăng ký...");
                    Dangky dangky = new Dangky(edt_hoten_dangky.getText().toString(), edt_sdt_dangky.getText().toString(), edt_taikhoan_dangky.getText().toString(), edt_matkhau_dangky.getText().toString());
                    RegisterAPI(dangky);
                }
            }
        });
        imgV_hienmk_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    edt_matkhau_dangky.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    edt_matkhau_dangky.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
            }
        });
        imgV_hiennhapmk_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    edt_nhapmatkhau_dangky.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    edt_nhapmatkhau_dangky.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
            }
        });
        return view;
    }

    private void RegisterAPI(Dangky dangky) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.Register(dangky) //here!
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleResponse(RegisterResponse message) {
        dismissProgressDialog();
        try {
            if (message.getStatus() == 1) {
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), message.getNotification(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleError(Throwable throwable) {
        dismissProgressDialog();
        Toast.makeText(getContext(), "Lỗi: " + throwable.toString(), Toast.LENGTH_LONG).show();
    }
}