package activity.example.yuan.cn.test_citylist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import activity.example.yuan.cn.test_citylist.city.City;
import activity.example.yuan.cn.test_citylist.city.ListViewAdapter;
import activity.example.yuan.cn.test_citylist.city.StringHelper;
import activity.example.yuan.cn.test_citylist.city.StringUtil;

public class SearchActivity extends Activity {
    private HashMap<String, Integer> selector;
    private LinearLayout layoutIndex;
    private ListView listView;
    private TextView tv_show,tv_allcity;
    private ListViewAdapter adapter;


    private String[] indexStr = { "*", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };

    private List<City> newCitys = new ArrayList<City>();
    private List<City> hotCitys = new ArrayList<City>();
    private List<City> allCitys = new ArrayList<City>();

    private int height;
    private boolean flag = false;

    private EditText et_seacher;

    private String currentCity = "长沙市";//当前定位到的城市

    private List<String> city1 = new ArrayList<String>();

    public LinkedHashMap<String, List> cityMap = new LinkedHashMap<String, List>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    public void initView() {
        layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(onItemClickAvoidForceListener);
        tv_show = (TextView) findViewById(R.id.tv);
        tv_show.setVisibility(View.GONE);

        intCityList();

        findViewById(R.id.tv_allcity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectCity("0");
                finish();
            }
        });


    }

    @SuppressWarnings("unchecked")
    private void setSelectCity(String selectcity){
        if(selectcity.equals("0")){
//            DE.setCityID(SearchActivity.this,"-1");
//            DE.setCity(SearchActivity.this,"所有城市");
            return;
        }
//        DE.setCity(SearchActivity.this,selectcity);
        String cityid = "";
        for(int i =0; i < cityList.size(); i++){
            if(cityList.get(i).get(1).equals(selectcity)){
                cityid = String.valueOf(cityList.get(i).get(0));
                break;
            }
        }
//        DE.setCityID(SearchActivity.this,cityid);
    }

    ArrayList<String> resultlist;


    List<ArrayList<String>> cityList = new ArrayList<>();

    public void intCityList() {

        try {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                AssetManager assetManager = getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        assetManager.open("city.json")));
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line.trim());
                }
                bf.close();
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray citylist = (JSONArray)jsonObject.get("citylist");
                for(int i = 0; i <citylist.length(); i++){
                    JSONObject ob = (JSONObject)citylist.get(i);

                    JSONArray citylist2 = (JSONArray)ob.get("city");
                    for(int j = 0; j <citylist2.length(); j++){
                        JSONObject ob2 = (JSONObject)citylist2.get(j);
                        if(!(ob2.get("name").toString().trim()).equals("其他")){
                            ArrayList<String> arrayList = new ArrayList<>();
                            arrayList.add("");
                            arrayList.add(ob2.get("name").toString().trim());
                            cityList.add(arrayList);
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("getUserCityList", cityList.size()+"-");
//            cityList = (List<ArrayList<String>>) map.get("data");
//            LogUtil.d("getUserCityList", cityList.toString());
            for(int i =0; i < cityList.size(); i++){
                city1.add(StringUtil.getKeyValue(cityList.get(i).get(1)));
            }
            Log.d("getUserCityList", city1.toString());
            Log.d("LinkedHashMap","start");
            LinkedHashMap<String, List> maps = new LinkedHashMap<String, List>();
            List<String> city2 = new ArrayList<String>();
            maps.put("ktcs", city1);
            maps.put("rmcs", city2);

            cityMap = maps;

            if (cityMap.size() > 0) {
                for (int i = 0; i < (cityMap.get("ktcs") == null ? 0
                        : cityMap.get("ktcs").size()); i++) {
                    allCitys.add(new City(cityMap.get("ktcs").get(i)
                            .toString()));
                }
                for (int i = 0; i < (cityMap.get("rmcs") == null ? 0
                        : cityMap.get("rmcs").size()); i++) {
                    hotCitys.add(new City(cityMap.get("rmcs").get(i)
                            .toString()));
                }
            }
            mhandler.sendEmptyMessage(1);
        }
        catch (Exception ex){
        }
    }

    private AdapterView.OnItemClickListener onItemClickAvoidForceListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                          int position, long id) {
            if(!newCitys.get(position).getName().isEmpty() && newCitys.get(position).getName().length() > 1){
                setSelectCity(newCitys.get(position).getName());
                finish();
            }

        }

    };



    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (allCitys.size() > 0) {
                        updataData();
                    }
                    break;
                case 2:
                    adapter.notifyDataSetChanged();
                    break;

                default:
                    break;
            }
        }
    };

    public void updataData() {
        String[] allNames = sortIndex(allCitys);
        // add current city
        for (int i = 0; i < hotCitys.size(); i++) {
            newCitys.add(hotCitys.get(i));
        }

        sortList(newCitys, allNames, allCitys);
        newCitys.add(0, new City(currentCity));
        selector = new HashMap<String, Integer>();
        for (int j = 0; j < indexStr.length; j++) {
            for (int i = 0; i < newCitys.size(); i++) {
                if (newCitys.get(i).getName().equals(indexStr[j])) {
                    selector.put(indexStr[j], i);
                }
            }

        }

        adapter = new ListViewAdapter(this, newCitys);
        listView.setAdapter(adapter);
    }

    private void sortList(List<City> newCitys, String[] allNames,
                          List<City> citys) {
        for (int i = 0; i < allNames.length; i++) {
            if (allNames[i].length() != 1) {
                for (int j = 0; j < citys.size(); j++) {
                    if (allNames[i].equals(citys.get(j).getPinYinName())) {
                        City p = new City(citys.get(j).getName(), citys.get(j)
                                .getPinYinName());
                        newCitys.add(p);
                    }
                }
            } else {
                newCitys.add(new City(allNames[i]));
            }
        }
    }

    /**
     * Activity
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!flag) {//
            height = layoutIndex.getMeasuredHeight() / indexStr.length;
            getIndexView();
            flag = true;
        }
    }

    /**
     *
     *
     * @param citys
     * @return
     */
    public String[] sortIndex(List<City> citys) {
        TreeSet<String> set = new TreeSet<String>();
        // ��ȡ��ʼ�����Դ�е�����ĸ����ӵ�set��
        for (City city : citys) {
            set.add(StringHelper.getPinYinHeadChar(city.getName()).substring(0,
                    1));
        }
        String[] names = new String[citys.size() + set.size()];
        int i = 0;
        for (String string : set) {
            names[i] = string;
            i++;
        }
        String[] pinYinNames = new String[citys.size()];
        for (int j = 0; j < citys.size(); j++) {
            citys.get(j).setPinYinName(
                    StringHelper.getPingYin(citys.get(j).getName().toString()));
            pinYinNames[j] = StringHelper.getPingYin(citys.get(j).getName()
                    .toString());
        }
        System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
        // �Զ���������ĸ����
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        return names;
    }

    /**
     */
    public void getIndexView() {
        LinearLayout.LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, height);
        for (int i = 0; i < indexStr.length; i++) {
            final TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(indexStr[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,12.0f);
            tv.setTextColor(Color.parseColor("#21b751"));
            tv.setPadding(20, 0, 20, 0);
            layoutIndex.addView(tv);
            layoutIndex.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event)

                {
                    try {
                        float y = event.getY();
                        int index = (int) (y / height);
                        if (index > -1 && index < indexStr.length) {// ��ֹԽ��
                            String key = indexStr[index];
                            if (selector.containsKey(key)) {
                                int pos = selector.get(key) + 1;
                                if (listView.getHeaderViewsCount() > 0) {// ��ֹListView�б�������������û�С�
                                    listView.setSelectionFromTop(
                                            pos
                                                    + listView
                                                    .getHeaderViewsCount(),
                                            0);
                                } else {
                                    listView.setSelectionFromTop(pos, 0);// ��������һ��
                                }
                                tv_show.setVisibility(View.VISIBLE);
                                tv_show.setText(indexStr[index]);
                            }
                        }
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
//                                layoutIndex.setBackgroundColor(Color
//                                        .parseColor("#f0f0f0"));
                                break;

                            case MotionEvent.ACTION_MOVE:

                                break;
                            case MotionEvent.ACTION_UP:
//                                layoutIndex.setBackgroundColor(Color
//                                        .parseColor("#f0f0f0"));
                                tv_show.setVisibility(View.GONE);
                                break;
                        }

                    } catch (Exception e) {
                    }
                    return true;
                }
            });
        }
    }

}