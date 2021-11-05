package com.hothithuhop.khambenhtructuyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hothithuhop.khambenhtructuyen.Fragment_dangnhapdangky.DangNhapDangKi_Activity;
import com.hothithuhop.khambenhtructuyen.TrangChu.Trangchu_Activity;

public class Taohosobenhnhan_Activity extends AppCompatActivity {
    private Button btn_datung_taohoso, btn_chuatung_taohoso;
    private ImageButton img_back_taohoso;
    private TextView tv_call1, tv_call2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taohosobenhnhan);
        anhxa();


        btn_datung_taohoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Taohosobenhnhan_Activity.this, Trangchu_Activity.class);
                startActivity(intent);
            }
        });
        btn_chuatung_taohoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Taohosobenhnhan_Activity.this, Dienthongtinbenhnhan_Activity.class);
                startActivity(intent);
            }
        });
        img_back_taohoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Taohosobenhnhan_Activity.this, DangNhapDangKi_Activity.class);
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

    private void anhxa() {
        btn_datung_taohoso = (Button) findViewById(R.id.btn_datung_taohoso);
        btn_chuatung_taohoso = (Button) findViewById(R.id.btn_chuatung_taohoso);
        img_back_taohoso = (ImageButton) findViewById(R.id.img_back_taohoso);
        tv_call1 = (TextView) findViewById(R.id.tv_call1);
        tv_call2 = (TextView) findViewById(R.id.tv_call2);
    }
}