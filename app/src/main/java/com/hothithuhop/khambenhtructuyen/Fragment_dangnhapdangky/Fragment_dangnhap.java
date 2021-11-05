package com.hothithuhop.khambenhtructuyen.Fragment_dangnhapdangky;

import static android.content.Context.MODE_PRIVATE;
import static com.hothithuhop.khambenhtructuyen.other.ServiceAPI.BASE_Service;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.dismissProgressDialog;
import static com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser.showProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.hothithuhop.khambenhtructuyen.R;
import com.hothithuhop.khambenhtructuyen.Taohosobenhnhan_Activity;
import com.hothithuhop.khambenhtructuyen.model.Info;
import com.hothithuhop.khambenhtructuyen.model.MessageLogin;
import com.hothithuhop.khambenhtructuyen.other.ServiceAPI;
import com.hothithuhop.khambenhtructuyen.other.ShowNotifyUser;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_dangnhap extends Fragment  {

    Button btn_dangnhap;
    EditText edt_matkhau_dangnhap, edt_taikhoan_dangnhap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dangnhap, container, false);

        btn_dangnhap = view.findViewById(R.id.btn_dangnhap);
        edt_matkhau_dangnhap = view.findViewById(R.id.edt_matkhau_dangnhap);
        edt_taikhoan_dangnhap = view.findViewById(R.id.edt_taikhoan_dangnhap);

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_taikhoan_dangnhap.getText().toString().length()==0 || edt_matkhau_dangnhap.getText().toString().length() ==0){
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                } else {
                    showProgressDialog(getContext(), "Đang đăng nhập...");
                    Info info = new Info(edt_taikhoan_dangnhap.getText().toString(), edt_matkhau_dangnhap.getText().toString());
                    LoginAPI(info);
                }

            }
        });
        return view;
    }

    private void LoginAPI(Info info) {
        ServiceAPI requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_Service)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ServiceAPI.class);
        new CompositeDisposable().add(requestInterface.Login(info)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)

        );
    }
    private void handleResponse(MessageLogin message) {
        dismissProgressDialog();
        try {
            if (message.getStatus() == 1) {
                saveID(message.getId()+"");
                Intent intent= new Intent(getActivity(), Taohosobenhnhan_Activity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getContext(),message.getNotification(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleError(Throwable throwable) {
        dismissProgressDialog();
        Toast.makeText(getContext(), "Lỗi: "+throwable.toString(), Toast.LENGTH_LONG).show();
    }

    private void saveID (String id){
        SharedPreferences pr = getActivity().getSharedPreferences("dangnhap", MODE_PRIVATE); //file này ở chế độ RIÊNG TƯ đối với các ứng dụng khác có trong điện thoại
        SharedPreferences.Editor editor = pr.edit();
        editor.putString("idUser",id);
        editor.commit();
    }
}