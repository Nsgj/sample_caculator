package com.example.gary.caculatedemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    private Button num0;
    private Button clear;
    private Button delete;
    private Button multi;
    private Button divi;
    private Button redu;
    private Button add;
    private Button equal;
    private Button dot;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num0 = (Button)findViewById(R.id.btn_0);
        num1 = (Button)findViewById(R.id.btn_1);
        num2 = (Button)findViewById(R.id.btn_2);
        num3 = (Button)findViewById(R.id.btn_3);
        num4 = (Button)findViewById(R.id.btn_4);
        num5 = (Button)findViewById(R.id.btn_5);
        num6 = (Button)findViewById(R.id.btn_6);
        num7 = (Button)findViewById(R.id.btn_7);
        num8 = (Button)findViewById(R.id.btn_8);
        num9 = (Button)findViewById(R.id.btn_9);
        delete = (Button)findViewById(R.id.del);
        clear = (Button)findViewById(R.id.clear);
        dot = (Button)findViewById(R.id.btn_dot);
        multi = (Button)findViewById(R.id.multi);
        add = (Button)findViewById(R.id.add);
        divi = (Button)findViewById(R.id.divi);
        redu = (Button)findViewById(R.id.reduce);
        equal = (Button)findViewById(R.id.equal);

        editText = (EditText)findViewById(R.id.edit_input);

        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        redu.setOnClickListener(this);
        dot.setOnClickListener(this);
        divi.setOnClickListener(this);
        add.setOnClickListener(this);
        multi.setOnClickListener(this);
        delete.setOnClickListener(this);
        clear.setOnClickListener(this);
        equal.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
                editText.setText(str + ((Button)v).getText());
                break;
            case R.id.multi:
            case R.id.reduce:
            case R.id.add:
            case R.id.divi:
                editText.setText(str + ((Button)v).getText());
                break;
            case R.id.clear:
                editText.setText("");
                break;
            case R.id.del:
                editText.setText(str.substring(0, str.length() - 1));
                break;
            case R.id.equal:
//                getResult();
                double result = getResult2(editText.getText().toString());
                editText.setText(result + "");
                break;


        }
    }
    private double getResult2(String exp){

        if(exp.startsWith("-")){
            exp = "*" + exp.substring(1,exp.length());
        }
        String s1;
        String s2;
        //先切割高优先低的字符串
        if(exp.contains("+") || exp.contains("-")){
            if(!exp.contains("+") || ((exp.indexOf("+") > exp.indexOf("-")) && exp.contains("-"))){
                s1 = exp.split("-",2)[0];
                s2 = exp.split("-",2)[1];
                return getResult2(s1) - getResult2(s2);
            }else {
                s1 = exp.split("\\+",2)[0];
                s2 = exp.split("\\+",2)[1];
                return getResult2(s1) + getResult2(s2);
            }
        }
        //再切割高运算级的字符串
        else if(exp.contains("×") || exp.contains("÷")){
            if(!exp.contains("×") || ((exp.indexOf("×") > exp.indexOf("÷")) && exp.contains("÷"))){
                s1 = exp.split("÷",2)[0];
                s2 = exp.split("÷",2)[1];
                return getResult2(s1) / getResult2(s2);
            }else {
                s1 = exp.split("×",2)[0];
                s2 = exp.split("×",2)[1];
                return getResult2(s1) * getResult2(s2);
            }
        }
        //无需切的时候直接转换成double返回
        if(exp.startsWith("*"))
            return -Double.parseDouble(exp.substring(1,exp.length()));
        else
            return Double.parseDouble(exp);
    }
//    private void getResult(){
//        double result = 0;
//        String exp = editText.getText().toString();
//        if(exp == null || exp.equals(" "))
//            return;
//        if(!exp.contains(" ")){
//            return;
//        }
//        String s1 = exp.substring(0,exp.indexOf(" "));
//        String op = exp.substring(exp.indexOf(" ") + 1,exp.indexOf(" ") + 2);
//        String s2 = exp.substring(exp.indexOf(" ") + 3);
//
//        if(!s1.equals(" ") && !s2.equals(" ")){
//            double d1 = Double.parseDouble(s1);
//            double d2 = Double.parseDouble(s2);
//
//            if(op.equals("+")){
//                result =  d1 + d2;
//            }else if(op.equals("-")){
//                result = d1 - d2;
//            }else if(op.equals("×")){
//                result = d1 * d2;
//            }else if(op.equals("÷")){
//                if(d2 == 0)
//                    result = 0;
//                else
//                    result = d1 / d2;
//            }
//        }
//        if(!s1.contains(".") && !s2.contains(".")){
//            int r = (int)result;
//            editText.setText(r + "");
//        }else {
//            editText.setText(result + "");
//        }
//        if(!s1.equals("") && s2.equals("")){
//            editText.setText(exp);
//        }
 //   }
}
