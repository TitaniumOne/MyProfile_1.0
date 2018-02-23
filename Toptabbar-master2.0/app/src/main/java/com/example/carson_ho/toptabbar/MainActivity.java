package com.example.carson_ho.toptabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button btnCountManger;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        initViews();

        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //账户管理按钮
        btnCountManger = (Button)findViewById(R.id.btn_countManager);
        btnCountManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CountManger.class);
                startActivity(intent);
            }
        });
    }

    private void initFruits()
    {
        Fruit fruit1 = new Fruit("兑换积分",R.mipmap.money,R.mipmap.arrow);
        fruitList.add(fruit1);
        Fruit fruit2 = new Fruit("信用值历史记录",R.mipmap.history,R.mipmap.arrow);
        fruitList.add(fruit2);
        Fruit fruit3 = new Fruit("系统设置",R.mipmap.gear,R.mipmap.arrow);
        fruitList.add(fruit3);

    }

    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);

        //设置Tab的图标
        one.setIcon(R.drawable.shouye);
        two.setIcon(R.drawable.yuyue);
        three.setIcon(R.drawable.dingdan);
        four.setIcon(R.drawable.wode);


    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.dingdantixing:
                Toast.makeText(this,"you clicked 订单详情",Toast.LENGTH_SHORT).show();
                break;
            case R.id.zaixiankefu:
                Toast.makeText(this,"you clicked 在线客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.saoyisao:
                Toast.makeText(this,"you clicked 扫一扫",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}