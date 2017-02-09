package com.witcos.fpfcalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView ding_view;
    private RadioGroup actors_rgp;
    private RadioButton radioButtonThree,radioButtonFour;
    private int actors=4;//参与人数
    private EditText huoniao_edit1,huoniao_edit2,huoniao_edit3,huoniao_edit4;
    private int huoniao1=0,huoniao2=0,huoniao3=0,huoniao4=0;
    private EditText jiage_edit;
    private double jiage=0.5;
    private EditText tuoniao_edit1,tuoniao_edit2,tuoniao_edit3,tuoniao_edit4;
    private int tuoniao1=0,tuoniao2=0,tuoniao3=0,tuoniao4=0;
    private EditText huxi_edit1,huxi_edit2,huxi_edit3,huxi_edit4;
    private int huxi1,huxi2,huxi3,huxi4;
    private  int hx1,hx2,hx3,hx4;
    private TextView jieguo_view1,jieguo_view2,jieguo_view3,jieguo_view4;
    private Button jisuan_btn,clean_btn,exit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findObject();
        actors_rgp.setOnCheckedChangeListener(new MyOnCheckChangLinster());
        jisuan_btn.setOnClickListener(new MyOnClickLinster());
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        clean_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huoniao_edit1.setText("0");
                huoniao_edit2.setText("0");
                huoniao_edit3.setText("0");
                huoniao_edit4.setText("0");
                tuoniao_edit1.setText("0");
                tuoniao_edit2.setText("0");
                tuoniao_edit3.setText("0");
                tuoniao_edit4.setText("0");
                huxi_edit1.setText("0");
                huxi_edit2.setText("0");
                huxi_edit3.setText("0");
                huxi_edit4.setText("0");
            }
        });

    }

    private void init(){
        try {
            huoniao1 = Integer.parseInt(huoniao_edit1.getText().toString());
            huoniao2 = Integer.parseInt(huoniao_edit2.getText().toString());
            huoniao3 = Integer.parseInt(huoniao_edit3.getText().toString());

            tuoniao1=Integer.parseInt(tuoniao_edit1.getText().toString());
            tuoniao2=Integer.parseInt(tuoniao_edit2.getText().toString());
            tuoniao3=Integer.parseInt(tuoniao_edit3.getText().toString());

            jiage=Double.parseDouble(jiage_edit.getText().toString());

            huxi1=Integer.parseInt(huxi_edit1.getText().toString());
            huxi2=Integer.parseInt(huxi_edit2.getText().toString());
            huxi3=Integer.parseInt(huxi_edit3.getText().toString());
            hx1=myInt(huxi1);
            hx2=myInt(huxi2);
            hx3=myInt(huxi3);
            tuoniao4=huxi4=huoniao4=0;
            if(actors==4){
                huoniao4 = Integer.parseInt(huoniao_edit4.getText().toString());
                tuoniao4=Integer.parseInt(tuoniao_edit4.getText().toString());
                huxi4=Integer.parseInt(huxi_edit4.getText().toString());
                hx4=myInt(huxi4);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private int myInt(int huxi){
        int tag=1,huxi_abs;
        huxi_abs=Math.abs(huxi);
        if(huxi<0) tag=-1;
        if(huxi_abs%10>=5)
            huxi_abs=(huxi_abs/10+1)*10;
        else
            huxi_abs=huxi_abs/10*10;
        return huxi_abs*tag;
    }

    class MyOnClickLinster implements View.OnClickListener{
        //计算最终结果
        @Override
        public void onClick(View v) {
            init();
            if(huoniao1+huoniao2+huoniao3+huoniao4+tuoniao1+tuoniao2+tuoniao3+tuoniao4+jiage>=0) {
                int jia = 0, yi = 0, bing = 0, ding = 0;//甲乙丙丁的胡子
                double jia_moneny = 0, yi_moneny = 0, bing_moneny = 0, ding_moneny = 0;//甲乙丙丁的钱
                if (actors == 4) {
                    //计算胡息
                    jia = ((hx1 - hx2) * (huoniao2 + 1) + (hx1 - hx3) * (huoniao3 + 1) + (hx1 - hx4) * (huoniao4 + 1)) * (huoniao1 + 1);
                    yi = ((hx2 - hx1) * (huoniao1 + 1) + (hx2 - hx3) * (huoniao3 + 1) + (hx2 - hx4) * (huoniao4 + 1)) * (huoniao2 + 1);
                    bing = ((hx3 - hx1) * (huoniao1 + 1) + (hx3 - hx2) * (huoniao2 + 1) + (hx3 - hx4) * (huoniao4 + 1)) * (huoniao3 + 1);
                    ding = ((hx4 - hx1) * (huoniao1 + 1) + (hx4 - hx3) * (huoniao3 + 1) + (hx4 - hx2) * (huoniao2 + 1)) * (huoniao4 + 1);
                    //计算拖鸟输赢
                    jia_moneny+=compare(huxi1,huxi2)*(tuoniao1+tuoniao2)+compare(huxi1,huxi3)*(tuoniao1+tuoniao3)+compare(huxi1,huxi4)*(tuoniao1+tuoniao4);
                    yi_moneny+=compare(huxi2,huxi1)*(tuoniao2+tuoniao1)+compare(huxi2,huxi3)*(tuoniao2+tuoniao3)+compare(huxi2,huxi4)*(tuoniao2+tuoniao4);
                    bing_moneny+=compare(huxi3,huxi1)*(tuoniao3+tuoniao1)+compare(huxi3,huxi2)*(tuoniao3+tuoniao2)+compare(huxi3,huxi4)*(tuoniao3+tuoniao4);
                    ding_moneny+=compare(huxi4,huxi1)*(tuoniao4+tuoniao1)+compare(huxi4,huxi2)*(tuoniao4+tuoniao2)+compare(huxi4,huxi3)*(tuoniao4+tuoniao3);
                    //计算活鸟输赢
                    jia_moneny += jia * jiage;
                    yi_moneny += yi * jiage;
                    bing_moneny += bing * jiage;
                    ding_moneny += ding * jiage;
                    //显示
                    jieguo_view4.setText(String.valueOf(Math.round(ding_moneny*10/10.0)));
                }
                else {
                    //三人玩，计算胡息
                    jia = ((hx1 - hx2) * (huoniao2 + 1) + (hx1 - hx3) * (huoniao3 + 1) ) * (huoniao1 + 1);
                    yi = ((hx2 - hx1) * (huoniao1 + 1) + (hx2 - hx3) * (huoniao3 + 1) ) * (huoniao2 + 1);
                    bing = ((hx3 - hx1) * (huoniao1 + 1) + (hx3 - hx2) * (huoniao2 + 1) ) * (huoniao3 + 1);

                    //三人版，计算拖鸟输赢
                    jia_moneny+=compare(huxi1,huxi2)*(tuoniao1+tuoniao2)+compare(huxi1,huxi3)*(tuoniao1+tuoniao3);
                    yi_moneny+=compare(huxi2,huxi1)*(tuoniao2+tuoniao1)+compare(huxi2,huxi3)*(tuoniao2+tuoniao3);
                    bing_moneny+=compare(huxi3,huxi1)*(tuoniao3+tuoniao1)+compare(huxi3,huxi2)*(tuoniao3+tuoniao2);

                    //三人版，计算活鸟输赢
                    jia_moneny += jia * jiage;
                    yi_moneny += yi * jiage;
                    bing_moneny += bing * jiage;
                }
                jieguo_view1.setText(String.valueOf(Math.round(jia_moneny*10/10.0)));
                jieguo_view2.setText(String.valueOf(Math.round(yi_moneny*10/10.0)));
                jieguo_view3.setText(String.valueOf(Math.round(bing_moneny*10/10.0)));
            }else {
                Toast.makeText(getApplicationContext(), "不能输入负数", Toast.LENGTH_SHORT).show();
                jiage_edit.setFocusable(true);
            }
        }

        private int compare(int x,int y){
            if(x==y) return 0;
            return  x>y?1:-1;
        }
    }



    class MyOnCheckChangLinster implements RadioGroup.OnCheckedChangeListener{
        //选择参与人数时执行
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(radioButtonThree.getId()==checkedId){
                actors=3;
                huoniao_edit4.setText("");
                tuoniao_edit4.setText("");
                huxi_edit4.setText("");
                jieguo_view4.setText("");
                jieguo_view4.setEnabled(false);
                huoniao_edit4.setEnabled(false);
                tuoniao_edit4.setEnabled(false);
                huxi_edit4.setEnabled(false);
                ding_view.setText("");
            }
            else {
                actors=4;
                huoniao_edit4.setText("0");
                tuoniao_edit4.setText("0");
                huxi_edit4.setText("0");
                jieguo_view4.setText("0");
                jieguo_view4.setEnabled(true);
                huoniao_edit4.setEnabled(true);
                tuoniao_edit4.setEnabled(true);
                huxi_edit4.setEnabled(true);
                ding_view.setText("丁");
            }
        }
    }


    private void findObject(){
        actors_rgp=(RadioGroup)findViewById(R.id.gamer);
        ding_view=(TextView)findViewById(R.id.ding);
        radioButtonFour=(RadioButton)findViewById(R.id.four);
        radioButtonThree=(RadioButton)findViewById(R.id.three);
        jiage_edit=(EditText)findViewById(R.id.jiage);
        huoniao_edit1=(EditText)findViewById(R.id.huoniao1);
        huoniao_edit2=(EditText)findViewById(R.id.huoniao2);
        huoniao_edit3=(EditText)findViewById(R.id.huoniao3);
        huoniao_edit4=(EditText)findViewById(R.id.huoniao4);
        tuoniao_edit1=(EditText)findViewById(R.id.tuoniao1);
        tuoniao_edit2=(EditText)findViewById(R.id.tuoniao2);
        tuoniao_edit3=(EditText)findViewById(R.id.tuoniao3);
        tuoniao_edit4=(EditText)findViewById(R.id.tuoniao4);
        jieguo_view1=(TextView)findViewById(R.id.jieguo1);
        jieguo_view2=(TextView)findViewById(R.id.jieguo2);
        jieguo_view3=(TextView)findViewById(R.id.jieguo3);
        jieguo_view4=(TextView)findViewById(R.id.jieguo4);
        huxi_edit1=(EditText)findViewById(R.id.huxi1);
        huxi_edit2=(EditText)findViewById(R.id.huxi2);
        huxi_edit3=(EditText)findViewById(R.id.huxi3);
        huxi_edit4=(EditText)findViewById(R.id.huxi4);
        jisuan_btn=(Button)findViewById(R.id.jisuan);
        clean_btn=(Button)findViewById(R.id.clean);
        exit_btn=(Button)findViewById(R.id.exit);

    }

}

