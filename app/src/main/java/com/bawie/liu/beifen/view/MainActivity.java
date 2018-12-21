package com.bawie.liu.beifen.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawie.liu.beifen.R;
import com.bawie.liu.beifen.adapter.CarAdapter;
import com.bawie.liu.beifen.core.DataCall;
import com.bawie.liu.beifen.http.Result;
import com.bawie.liu.beifen.http.Shop;
import com.bawie.liu.beifen.presenter.CartPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCall<List<Shop>>,CarAdapter.TotalPriceListener{

    private CheckBox checkAll;
    private TextView priceAll;
    private ExpandableListView list_car;
    private CarAdapter mCartAdapter;
    private CartPresenter cartPresenter = new CartPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAll = findViewById(R.id.checkAll);
        priceAll = findViewById(R.id.priceAll);
        list_car = findViewById(R.id.list_car);
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCartAdapter.checkAll(isChecked);
            }
        });
        mCartAdapter = new CarAdapter();
        list_car.setAdapter(mCartAdapter);
        mCartAdapter.setTotalPriceListener(this);//设置总价回调器
        list_car.setGroupIndicator(null);

        list_car.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return true;
            }
        });
        cartPresenter.requestData();

    }

    @Override
    public void totalPrice(double totalPrice) {
        priceAll.setText(String.valueOf(totalPrice));
    }

    @Override
    public void success(List<Shop> data) {
        mCartAdapter.addAll(data);
        //遍历所有group,将所有项设置成默认展开
        int groupCount = data.size();
        for (int i = 0; i < groupCount; i++) {
            list_car.expandGroup(i);
        }

        mCartAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(Result result) {
        cartPresenter.unBindCall();
        Toast.makeText(this, result.getCode() + "   " + result.getMsg(), Toast.LENGTH_LONG).show();
    }
}
