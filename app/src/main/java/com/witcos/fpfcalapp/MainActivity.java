package com.witcos.fpfcalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView ding_view;
    private RadioGroup actors_rgp;
    private RadioButton radioButtonThree;
    private int actors = 4;//参与人数
    private EditText editText[][] = new EditText[3][4];
    //文本框二维数组：一维表示【0，1，2】：0代表活鸟参数，1代表拖鸟参数，2代表胡息参数
    //另外一维表示玩家【0，1，2，3】为甲乙丙丁
    private int huoNiao[] = {0, 0, 0, 0};//玩家甲乙丙丁的活鸟参数数组
    private EditText price_edit;//打牌彩头的文本框对象
    private double price = 0.5;
    private int tuoNiao[] = {0, 0, 0, 0};//玩家甲乙丙丁的拖鸟参数数组
    private int huXi[] = {0, 0, 0, 0};//玩家甲乙丙丁的胡息积分参数数组
    double result[] = {0, 0, 0, 0};//甲乙丙丁的钱
    private TextView result_view[] = new TextView[4];
    private Button calculate_btn, clean_btn, exit_btn;
    private MyCalculateOnClickListener myCal = new MyCalculateOnClickListener();
    //计算按钮对应的单击事件监听对象
    private MyOnFocusChangListener myOnFocusChangListener = new MyOnFocusChangListener();
    //文本框对应的焦点改变事件监听对象
    private MyClearOnClickListener MyClearOnClickListener = new MyClearOnClickListener();
    //清除按钮对应的单击事件监听对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //设置布局文件
        findObject();//寻找控件
        actors_rgp.setOnCheckedChangeListener(new MyOnCheckChangListener());
        //注册玩家人数事件监听对象
        calculate_btn.setOnClickListener(myCal);
        //注册计算按钮单击事件监听对象
        setEditFocusListener();//注册edit的焦点监听事件
        exit_btn.setOnClickListener(new View.OnClickListener() {
            //设置退出按钮监听事件
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        clean_btn.setOnClickListener(MyClearOnClickListener);
    }

    class MyClearOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            clear();
        }
    }

    private void clear() {//清除方法
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                editText[i][j].setText("0");
            }
        }
        for (int j = 0; j < 4; j++) {
            result_view[j].setText("0");
        }
    }

    private void setEditFocusListener() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                editText[i][j].setOnFocusChangeListener(myOnFocusChangListener);
            }
        }
        price_edit.setOnFocusChangeListener(myOnFocusChangListener);
    }

    private void init() {
        for(int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                if(editText[i][j].getText().toString().equals(""))
                    editText[i][j].setText("0");                
            }
        }
        if(price_edit.getText().toString().equals(""))
            price_edit.setText("0.5");
        try {
            for (int i = 0; i < actors; i++) {//i是玩家                
                huoNiao[i] = Integer.parseInt(editText[0][i].getText().toString());
                tuoNiao[i] = Integer.parseInt(editText[1][i].getText().toString());
                huXi[i] = Integer.parseInt(editText[2][i].getText().toString());
            }
            price = Double.parseDouble(price_edit.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int myInt(int huxi) {
        int tag = 1, huxi_abs;
        huxi_abs = Math.abs(huxi);
        if (huxi < 0) tag = -1;
        if (huxi_abs % 10 >= 5)
            huxi_abs = (huxi_abs / 10 + 1) * 10;
        else
            huxi_abs = huxi_abs / 10 * 10;
        return huxi_abs * tag;
    }

    class MyOnFocusChangListener implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText editText = (EditText) v;
            if (hasFocus) {
                editText.setText("");
            } else {
                if (editText.getText().toString().equals("")) {
                    if (editText.getId() == R.id.price)
                        editText.setText("0.5");
                    else
                        editText.setText("0");
                }
            }
        }
    }

    private void calculate() {//计算方法
        result[0]=result[1]=result[2]=result[3]=0;
        //先行初始化输赢结果为0
        //下面采用两两结算的方法
        for (int i = 0; i < actors; i++) {
            for (int j = 0; j < actors; j++) {
                result[i] += compare(huXi[i], huXi[j]) * (tuoNiao[i] + tuoNiao[j]);
                //第一步：计算i玩家对j玩家在拖鸟上输赢
                result[i] += (myInt(huXi[i]) - myInt(huXi[j])) * (huoNiao[i] + 1) * (huoNiao[j] + 1) * price;
                //第二步：计算i玩家对j玩家在活鸟上输赢，并累计拖鸟输赢
            }
        }
    }

    private void view() {
        for (int i = 0; i < actors; i++) {
            result_view[i].setText(String.valueOf(Math.round(result[i] * 10 / 10.0)));
        }
    }


    class MyCalculateOnClickListener implements View.OnClickListener {
        //计算最终结果
        @Override
        public void onClick(View v) {
            init();
            calculate();
            view();
        }
    }

    private int compare(int x, int y) {//比较xy,x==y返回0，x>y返回1，x<y返回-1
        if (x == y) return 0;
        return x > y ? 1 : -1;
    }

    class MyOnCheckChangListener implements RadioGroup.OnCheckedChangeListener {
        //选择参与人数时执行
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            clear();
            boolean tag;
            if (radioButtonThree.getId() == checkedId) {
                actors = 3;
                tag=false;
            } else {
                actors = 4;
                tag=true;
            }
            for(int i=0;i<3;i++){
                editText[i][3].setEnabled(tag);
            }
            result_view[3].setEnabled(tag);
            ding_view.setEnabled(tag);
        }
    }

    private void findObject() {
        actors_rgp = (RadioGroup) findViewById(R.id.gamer);
        ding_view = (TextView) findViewById(R.id.ding);
        radioButtonThree = (RadioButton) findViewById(R.id.three);
        price_edit = (EditText) findViewById(R.id.price);
        editText[0][0] = (EditText) findViewById(R.id.huoniao1);
        editText[0][1] = (EditText) findViewById(R.id.huoniao2);
        editText[0][2] = (EditText) findViewById(R.id.huoniao3);
        editText[0][3] = (EditText) findViewById(R.id.huoniao4);
        editText[1][0] = (EditText) findViewById(R.id.tuoniao1);
        editText[1][1] = (EditText) findViewById(R.id.tuoniao2);
        editText[1][2] = (EditText) findViewById(R.id.tuoniao3);
        editText[1][3] = (EditText) findViewById(R.id.tuoniao4);
        result_view[0] = (TextView) findViewById(R.id.result1);
        result_view[1] = (TextView) findViewById(R.id.result2);
        result_view[2] = (TextView) findViewById(R.id.result3);
        result_view[3] = (TextView) findViewById(R.id.result4);
        editText[2][0] = (EditText) findViewById(R.id.huxi1);
        editText[2][1] = (EditText) findViewById(R.id.huxi2);
        editText[2][2] = (EditText) findViewById(R.id.huxi3);
        editText[2][3] = (EditText) findViewById(R.id.huxi4);

        calculate_btn = (Button) findViewById(R.id.calculate);
        clean_btn = (Button) findViewById(R.id.clean);
        exit_btn = (Button) findViewById(R.id.exit);

    }

}

