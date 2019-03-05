package com.example.a19633.callsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private OkHttpClient client = new OkHttpClient();
    private Button btn_json;
    private Button btn_get;
    private Button btn_post;
    private TextView tv_tips1;
    private TextView tv_json;
    private TextView tv_tips2;
    private TextView tv_gson;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private static final int GET = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET: {
                    Gson gson = new Gson();
                    Calls calls = gson.fromJson((String) msg.obj, Calls.class);

                    linearLayout1.setBackgroundColor(Color.parseColor("#eef7f2"));
                    linearLayout2.setBackgroundColor(Color.parseColor("#f1f0ed"));
                    tv_tips1.setText("Json内容为：");
                    tv_json.setText((String) msg.obj);
                    tv_tips2.setText("Gson转化为：");
                    tv_gson.setText(calls.toString());
                }
                break;
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_tips1 = findViewById(R.id.tv_tips1);
        tv_json = findViewById(R.id.tv_json);
        tv_tips2 = findViewById(R.id.tv_tips2);
        tv_gson = findViewById(R.id.tv_gson);
        linearLayout1 = findViewById(R.id.linear1);
        linearLayout2 = findViewById(R.id.linear2);
        btn_json = findViewById(R.id.btn_json);
        btn_get = findViewById(R.id.btn_get);
        btn_post = findViewById(R.id.btn_post);
        btn_json.setOnClickListener(this);
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_json:
                jsonToJavaObjectByGson();
                break;
            case R.id.btn_get:
                jsonGetByOkHttp();
                break;
            case R.id.btn_post:
                jsonPostByOkHttp();
                break;
        }
    }

    //将json格式对象转化成字符串对象
    private void jsonToJavaObjectByGson() {
        //创建或者获取json数据
        String json = "{\n" +
                "    \"code\":100,\n" +
                "    \"msg\":\"处理成功！\",\n" +
                "    \"extend\":{\n" +
                "        \"pageInfo\":{\n" +
                "            \"pageNum\":1,\n" +
                "            \"pageSize\":5,\n" +
                "            \"size\":4,\n" +
                "            \"startRow\":1,\n" +
                "            \"endRow\":4,\n" +
                "            \"total\":4,\n" +
                "            \"pages\":1,\n" +
                "            \"list\":[\n" +
                "                {\n" +
                "                    \"callId\":3,\n" +
                "                    \"subId\":2,\n" +
                "                    \"subTime\":1545549302000,\n" +
                "                    \"endTime\":1545549302000,\n" +
                "                    \"callTitle\":\"123\",\n" +
                "                    \"callDesp\":\"asd\",\n" +
                "                    \"callMoney\":12,\n" +
                "                    \"callNow\":\"y\",\n" +
                "                    \"recId\":2,\n" +
                "                    \"subName\":\"white\",\n" +
                "                    \"recName\":\"asdf\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"callId\":4,\n" +
                "                    \"subId\":2,\n" +
                "                    \"subTime\":1545571900000,\n" +
                "                    \"endTime\":1545571900000,\n" +
                "                    \"callTitle\":\"123\",\n" +
                "                    \"callDesp\":\"asd\",\n" +
                "                    \"callMoney\":12,\n" +
                "                    \"callNow\":\"y\",\n" +
                "                    \"recId\":2,\n" +
                "                    \"subName\":\"white\",\n" +
                "                    \"recName\":\"asdf\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"callId\":6,\n" +
                "                    \"subId\":2,\n" +
                "                    \"subTime\":1545575839000,\n" +
                "                    \"endTime\":1545575856000,\n" +
                "                    \"callTitle\":\"123\",\n" +
                "                    \"callDesp\":\"qianga\",\n" +
                "                    \"callMoney\":24,\n" +
                "                    \"callNow\":\"y\",\n" +
                "                    \"recId\":5,\n" +
                "                    \"subName\":\"white\",\n" +
                "                    \"recName\":\"whte\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"callId\":7,\n" +
                "                    \"subId\":2,\n" +
                "                    \"subTime\":1545513712000,\n" +
                "                    \"endTime\":1545513712000,\n" +
                "                    \"callTitle\":\"123\",\n" +
                "                    \"callDesp\":\"asd\",\n" +
                "                    \"callMoney\":12,\n" +
                "                    \"callNow\":\"y\",\n" +
                "                    \"recId\":5,\n" +
                "                    \"subName\":\"white\",\n" +
                "                    \"recName\":\"whte\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"prePage\":0,\n" +
                "            \"nextPage\":0,\n" +
                "            \"isFirstPage\":true,\n" +
                "            \"isLastPage\":true,\n" +
                "            \"hasPreviousPage\":false,\n" +
                "            \"hasNextPage\":false,\n" +
                "            \"navigatePages\":5,\n" +
                "            \"navigatepageNums\":[\n" +
                "                1\n" +
                "            ],\n" +
                "            \"navigateFirstPage\":1,\n" +
                "            \"navigateLastPage\":1,\n" +
                "            \"lastPage\":1,\n" +
                "            \"firstPage\":1\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //解析json数据
        Gson gson = new Gson();

        Calls calls = gson.fromJson(json, Calls.class);

        //展示数据
        linearLayout1.setBackgroundColor(Color.parseColor("#eef7f2"));
        linearLayout2.setBackgroundColor(Color.parseColor("#f1f0ed"));
        tv_tips1.setText("Json内容为：");
        tv_json.setText(json);
        tv_tips2.setText("Gson转化为：");
        tv_gson.setText(calls.toString());
    }

    private void jsonGetByOkHttp() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    //获取数据
                    String result = get("http://www.xinxianquan.xyz:8080/zhaqsq/call/calls");
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void jsonPostByOkHttp() {

    }

    String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}