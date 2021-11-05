package com.hothithuhop.khambenhtructuyen.Fragment_dangnhapdangky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hothithuhop.khambenhtructuyen.R;

public class DangNhapDangKi_Activity extends AppCompatActivity {
    ViewPager view_paperdndk;
    Button btn_dangnhap;
    BottomNavigationView bottom_navigation_dndk;
    EditText edt_taikhoan_dangnhap,edt_matkhau_dangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhapdangky);
        anhxa();

        ViewTabLayoutAdapter adapter=new ViewTabLayoutAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        view_paperdndk.setAdapter(adapter);


        view_paperdndk.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch ( position){
                    case 0:
                        bottom_navigation_dndk.getMenu().findItem(R.id.menu_dangnhap).setChecked(true);
                        break;
                    case 1:
                        bottom_navigation_dndk.getMenu().findItem(R.id.menu_dangky).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottom_navigation_dndk.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_dangnhap:
                        view_paperdndk.setCurrentItem(0);
                        break;

                    case R.id.menu_dangky:
                        view_paperdndk.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });


    }


    private void anhxa(){
        bottom_navigation_dndk=(BottomNavigationView) findViewById(R.id.bottom_navigation_dndk);
        view_paperdndk=(ViewPager) findViewById(R.id.view_paperdndk);
        btn_dangnhap=(Button) findViewById(R.id.btn_dangnhap);
        edt_matkhau_dangnhap=findViewById(R.id.edt_matkhau_dangnhap);
        edt_taikhoan_dangnhap=findViewById(R.id.edt_taikhoan_dangnhap);
    }
}