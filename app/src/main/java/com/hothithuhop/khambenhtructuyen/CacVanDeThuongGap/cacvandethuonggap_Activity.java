package com.hothithuhop.khambenhtructuyen.CacVanDeThuongGap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.hothithuhop.khambenhtructuyen.R;
import com.hothithuhop.khambenhtructuyen.TrangChu.Trangchu_Activity;

public class cacvandethuonggap_Activity extends AppCompatActivity {
    private TabLayout tab_layout;
    private ViewPager viewPager;
    private ImageButton img_back_cacvande;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cacvandethuonggap);
        anhxa();

        ViewPager_cacvandethuonggap_Adapter viewPager_cacvandethuonggap_Adapter=new ViewPager_cacvandethuonggap_Adapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPager_cacvandethuonggap_Adapter);
        tab_layout.setupWithViewPager(viewPager);

        img_back_cacvande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cacvandethuonggap_Activity.this, Trangchu_Activity.class);
                startActivity(intent);
            }
        });

    }
    private void anhxa()
    {
        img_back_cacvande=(ImageButton) findViewById(R.id.img_back_cacvande);
        tab_layout=(TabLayout) findViewById(R.id.tab_layout);
        viewPager=(ViewPager) findViewById(R.id.view_paper);
    }
}