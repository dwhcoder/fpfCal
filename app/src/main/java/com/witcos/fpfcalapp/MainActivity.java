package com.witcos.fpfcalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView ding_view;
    private RadioGroup actors_rgp;
    private RadioButton radioButtonThree;
    private int actors = 4;//参与人数
    private EditText editText[][]=new EditText[3][4];   
    private int huoniao1 = 0, huoniao2 = 0, huoniao3 = 0, huoniao4 = 0;
    private EditText jiage_edit;
    private double jiage = 0.5;
    private int tuoniao1 = 0, tuoniao2 = 0, tuoniao3 = 0, tuoniao4 = 0;
    private int huxi1, huxi2, huxi3, huxi4;
    private TextView jieguo_view1, jieguo_view2, jieguo_view3, jieguo_view4;
    private Button jisuan_btn, clean_btn, exit_btn;
    private MyJiSuanOnClickLinstener myjisuan = new MyJiSuanOnClickLinstener();
    private MyOnFocusChangListener myOnFocusChangListener = new MyOnFocusChangListener();
    private MyClearOnClickLinstener myClearOnClickLinstener=new MyClearOnClickLinstener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置布局文件

        findObject();//寻找控件
        actors_rgp.setOnCheckedChangeListener(new MyOnCheckChangLinstener());
        //设置玩家人数事件监听对象
        jisuan_btn.setOnClickListener(myjisuan);
        //设置计算按钮单击事件监听对象
        setEditFocusLinstener();//注册edit的焦点监听事件

        exit_btn.setOnClickListener(new View.OnClickListener() {
            //设置退出按钮监听事件
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        clean_btn.setOnClickListener(myClearOnClickLinstener);

    }
    
    class MyClearOnClickLinstener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            clear();
        }
    }

    private void clear() {
        editText[0][0].setText("0");
        editText[0][1].setText("0");
        editText[0][2].setText("0");
        editText[0][3].setText("0");
        editText[1][0].setText("0");
        editText[1][1].setText("0");
        editText[1][2].setText("0");
        editText[1][3].setText("0");
        editText[2][0].setText("0");
        editText[2][1].setText("0");
        editText[2][2].setText("0");
        editText[2][3].setText("0");
        jieguo_view1.setText("0");
        jieguo_view2.setText("0");
        jieguo_view3.setText("0");
        jieguo_view4.setText("0");
    }

    private void setEditFocusLinstener() {
        editText[0][0].setOnFocusChangeListener(myOnFocusChangListener);
        editText[0][1].setOnFocusChangeListener(myOnFocusChangListener);
        editText[0][2].setOnFocusChangeListener(myOnFocusChangListener);
        editText[0][3].setOnFocusChangeListener(myOnFocusChangListener);
        editText[1][0].setOnFocusChangeListener(myOnFocusChangListener);
        editText[1][1].setOnFocusChangeListener(myOnFocusChangListener);
        editText[1][2].setOnFocusChangeListener(myOnFocusChangListener);
        editText[1][3].setOnFocusChangeListener(myOnFocusChangListener);
        editText[2][0].setOnFocusChangeListener(myOnFocusChangListener);
        editText[2][1].setOnFocusChangeListener(myOnFocusChangListener);
        editText[2][2].setOnFocusChangeListener(myOnFocusChangListener);
        editText[2][3].setOnFocusChangeListener(myOnFocusChangListener);
        jiage_edit.setOnFocusChangeListener(myOnFocusChangListener);
    }

    private void init() {
        try {
            huoniao1 = Integer.parseInt(editText[0][0].getText().toString());
            huoniao2 = Integer.parseInt(editText[0][1].getText().toString());
            huoniao3 = Integer.parseInt(editText[0][2].getText().toString());

            tuoniao1 = Integer.parseInt(editText[1][0].getText().toString());
            tuoniao2 = Integer.parseInt(editText[1][1].getText().toString());
            tuoniao3 = Integer.parseInt(editText[1][2].getText().toString());

            jiage = Double.parseDouble(jiage_edit.getText().toString());

            huxi1 = Integer.parseInt(editText[2][0].getText().toString());
            huxi2 = Integer.parseInt(editText[2][1].getText().toString());
            huxi3 = Integer.parseInt(editText[2][2].getText().toString());

            tuoniao4 = huxi4 = huoniao4 = 0;
            if (actors == 4) {
                huoniao4 = Integer.parseInt(editText[0][3].getText().toString());
                tuoniao4 = Integer.parseInt(editText[1][3].getText().toString());
                huxi4 = Integer.parseInt(editText[2][3].getText().toString());
            }
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
                if (editText.getText().toString().equals("")){
                    if(editText.getId()==R.id.jiage)
                        editText.setText("0.5");
                    else
                        editText.setText("0");
                }

            }
        }
    }


    class MyJiSuanOnClickLinstener implements View.OnClickListener {
        //计算最终结果
        @Override
        public void onClick(View v) {
            init();
            if (huoniao1 + huoniao2 + huoniao3 + huoniao4 + tuoniao1 + tuoniao2 + tuoniao3 + tuoniao4 + jiage >= 0) {
                double jia_moneny = 0, yi_moneny = 0, bing_moneny = 0, ding_moneny = 0;//甲乙丙丁的钱
                if (actors == 4) {
                    //拖鸟输赢
                    jia_moneny += compare(huxi1, huxi2) * (tuoniao1 + tuoniao2) + compare(huxi1, huxi3) * (tuoniao1 + tuoniao3) + compare(huxi1, huxi4) * (tuoniao1 + tuoniao4);
                    yi_moneny += compare(huxi2, huxi1) * (tuoniao2 + tuoniao1) + compare(huxi2, huxi3) * (tuoniao2 + tuoniao3) + compare(huxi2, huxi4) * (tuoniao2 + tuoniao4);
                    bing_moneny += compare(huxi3, huxi1) * (tuoniao3 + tuoniao1) + compare(huxi3, huxi2) * (tuoniao3 + tuoniao2) + compare(huxi3, huxi4) * (tuoniao3 + tuoniao4);
                    ding_moneny += compare(huxi4, huxi1) * (tuoniao4 + tuoniao1) + compare(huxi4, huxi2) * (tuoniao4 + tuoniao2) + compare(huxi4, huxi3) * (tuoniao4 + tuoniao3);

                    //计算活鸟输赢,并累计了拖鸟输赢
                    jia_moneny += (myInt(huxi1) - myInt(huxi2)) * (huoniao1 + 1) * (huoniao2 + 1) * jiage +
                            (myInt(huxi1) - myInt(huxi3)) * (huoniao1 + 1) * (huoniao3 + 1) * jiage +
                            (myInt(huxi1) - myInt(huxi4)) * (huoniao1 + 1) * (huoniao4 + 1) * jiage;
                    yi_moneny += (myInt(huxi2) - myInt(huxi1)) * (huoniao2 + 1) * (huoniao1 + 1) * jiage +
                            (myInt(huxi2) - myInt(huxi3)) * (huoniao2 + 1) * (huoniao3 + 1) * jiage +
                            (myInt(huxi2) - myInt(huxi4)) * (huoniao2 + 1) * (huoniao4 + 1) * jiage;
                    bing_moneny += (myInt(huxi3) - myInt(huxi1)) * (huoniao3 + 1) * (huoniao1 + 1) * jiage +
                            (myInt(huxi3) - myInt(huxi2)) * (huoniao3 + 1) * (huoniao2 + 1) * jiage +
                            (myInt(huxi3) - myInt(huxi4)) * (huoniao3 + 1) * (huoniao4 + 1) * jiage;
                    ding_moneny += (myInt(huxi4) - myInt(huxi1)) * (huoniao4 + 1) * (huoniao1 + 1) * jiage +
                            (myInt(huxi4) - myInt(huxi2)) * (huoniao4 + 1) * (huoniao2 + 1) * jiage +
                            (myInt(huxi4) - myInt(huxi3)) * (huoniao4 + 1) * (huoniao3 + 1) * jiage;
                    //显示
                    jieguo_view1.setText(String.valueOf(Math.round(jia_moneny * 10 / 10.0)));
                    jieguo_view2.setText(String.valueOf(Math.round(yi_moneny * 10 / 10.0)));
                    jieguo_view3.setText(String.valueOf(Math.round(bing_moneny * 10 / 10.0)));
                    jieguo_view4.setText(String.valueOf(Math.round(ding_moneny * 10 / 10.0)));
                } else {
                    //三人版，计算拖鸟输赢
                    jia_moneny += compare(huxi1, huxi2) * (tuoniao1 + tuoniao2) + compare(huxi1, huxi3) * (tuoniao1 + tuoniao3);
                    yi_moneny += compare(huxi2, huxi1) * (tuoniao2 + tuoniao1) + compare(huxi2, huxi3) * (tuoniao2 + tuoniao3);
                    bing_moneny += compare(huxi3, huxi1) * (tuoniao3 + tuoniao1) + compare(huxi3, huxi2) * (tuoniao3 + tuoniao2);


                    //三人版，计算活鸟输赢，并累计拖鸟输赢
                    jia_moneny += (myInt(huxi1) - myInt(huxi2)) * (huoniao1 + 1) * (huoniao2 + 1) * jiage +
                            (myInt(huxi1) - myInt(huxi3)) * (huoniao1 + 1) * (huoniao3 + 1) * jiage ;
                    yi_moneny += (myInt(huxi2) - myInt(huxi1)) * (huoniao2 + 1) * (huoniao1 + 1) * jiage +
                            (myInt(huxi2) - myInt(huxi3)) * (huoniao2 + 1) * (huoniao3 + 1) * jiage ;
                    bing_moneny += (myInt(huxi3) - myInt(huxi1)) * (huoniao3 + 1) * (huoniao1 + 1) * jiage +
                            (myInt(huxi3) - myInt(huxi2)) * (huoniao3 + 1) * (huoniao2 + 1) * jiage ;
                    //显示
                    jieguo_view1.setText(String.valueOf(Math.round(jia_moneny * 10 / 10.0)));
                    jieguo_view2.setText(String.valueOf(Math.round(yi_moneny * 10 / 10.0)));
                    jieguo_view3.setText(String.valueOf(Math.round(bing_moneny * 10 / 10.0)));
                }

            } else {
                Toast.makeText(getApplicationContext(), "不能输入负数", Toast.LENGTH_SHORT).show();
                jiage_edit.setFocusable(true);
            }
        }




    }

    private int compare(int x, int y) {
        if (x == y) return 0;
        return x > y ? 1 : -1;
    }

    class MyOnCheckChangLinstener implements RadioGroup.OnCheckedChangeListener {
        //选择参与人数时执行
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            clear();
            if (radioButtonThree.getId() == checkedId) {
                actors = 3;
                editText[0][3].setText("");
                editText[1][3].setText("");
                editText[2][3].setText("");
                jieguo_view4.setText("");
                jieguo_view4.setEnabled(false);
                editText[0][3].setEnabled(false);
                editText[1][3].setEnabled(false);
                editText[2][3].setEnabled(false);
                ding_view.setText("");
            } else {
                actors = 4;
                editText[0][3].setText("0");
                editText[1][3].setText("0");
                editText[2][3].setText("0");
                jieguo_view4.setText("0");
                jieguo_view4.setEnabled(true);
                editText[0][3].setEnabled(true);
                editText[1][3].setEnabled(true);
                editText[2][3].setEnabled(true);
                ding_view.setText("丁");
            }
        }
    }


    private void findObject() {
        actors_rgp = (RadioGroup) findViewById(R.id.gamer);
        ding_view = (TextView) findViewById(R.id.ding);
        radioButtonThree = (RadioButton) findViewById(R.id.three);
        jiage_edit = (EditText) findViewById(R.id.jiage);
        editText[0][0] = (EditText) findViewById(R.id.huoniao1);
        editText[0][1] = (EditText) findViewById(R.id.huoniao2);
        editText[0][2] = (EditText) findViewById(R.id.huoniao3);
        editText[0][3] = (EditText) findViewById(R.id.huoniao4);
        editText[1][0] = (EditText) findViewById(R.id.tuoniao1);
        editText[1][1] = (EditText) findViewById(R.id.tuoniao2);
        editText[1][2] = (EditText) findViewById(R.id.tuoniao3);
        editText[1][3] = (EditText) findViewById(R.id.tuoniao4);
        jieguo_view1 = (TextView) findViewById(R.id.jieguo1);
        jieguo_view2 = (TextView) findViewById(R.id.jieguo2);
        jieguo_view3 = (TextView) findViewById(R.id.jieguo3);
        jieguo_view4 = (TextView) findViewById(R.id.jieguo4);
        editText[2][0] = (EditText) findViewById(R.id.huxi1);
        editText[2][1] = (EditText) findViewById(R.id.huxi2);
        editText[2][2] = (EditText) findViewById(R.id.huxi3);
        editText[2][3] = (EditText) findViewById(R.id.huxi4);
        
        jisuan_btn = (Button) findViewById(R.id.jisuan);
        clean_btn = (Button) findViewById(R.id.clean);
        exit_btn = (Button) findViewById(R.id.exit);

    }

}

