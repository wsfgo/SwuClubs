package com.example.swuclubmanage.com.wsf.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swuclubmanage.R;
import com.example.swuclubmanage.com.wsf.utils.JugeNetWorkAvialiable;
import com.example.swuclubmanage.com.wsf.utils.TitleBar;
import com.example.swuclubmanage.com.wsf.utils.Tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;
    Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            if("success".equals(msg.obj)){
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            }else if("fail".equals(msg.obj)){
                Toast.makeText(LoginActivity.this, "密码或用户名错误", Toast.LENGTH_SHORT).show();
            }
            if(msg.what==0){
                Toast.makeText(LoginActivity.this,"服务器出错啦！！！",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
         loginbtn= (Button) findViewById(R.id.loginbtn);

        initTitleBar();
        if(JugeNetWorkAvialiable.isNetWorkAviliable(this)) {
            loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText et_name = (EditText) findViewById(R.id.et_studentname);
                    EditText et_pass = (EditText) findViewById(R.id.et_stupassword);

                    final String name = et_name.getText().toString();
                    final String pass = et_pass.getText().toString();

                    Thread t = new Thread() {
                        @Override
                        public void run() {
                            String path = "http://106.88.164.52:8080/SwuClubManage/servlet/Login";
                            try {
                                URL url = new URL(path);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                                conn.setRequestMethod("POST");
                                conn.setConnectTimeout(8000);
                                conn.setReadTimeout(8000);

                                //添加post请求头中自动添加的属性
                                //流里的数据的mimetype
                                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                                String content = "name=" + URLEncoder.encode(name) + "&pass=" + pass;
                                //流里数据的长度
                                conn.setRequestProperty("Content-Length", content.length() + "");

                                //打开连接对象的输出流
                                conn.setDoOutput(true);
                                //获取连接对象的输出流
                                OutputStream os = conn.getOutputStream();
                                //把数据写入输出流中
                                os.write(content.getBytes());

                                if (conn.getResponseCode() == 200) {
                                    InputStream is = conn.getInputStream();
                                    String text = Tools.getTextFromStream(is);

                                    Message msg = handler.obtainMessage();
                                    msg.obj = text;
                                    handler.sendMessage(msg);
                                }else{
                                    handler.sendEmptyMessage(0);
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        }
                    };
                    t.start();
                }
            });
        }else{
            Toast.makeText(this,"当前网络不可用",Toast.LENGTH_LONG).show();
        }

    }

    private void initTitleBar() {
        new TitleBar(this).setTitle("用户登录")
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // handler.sendEmptyMessage(0);
                        finish();
                    }
                });
    }


}
