package com.vero.weatherapp.slice;

import com.vero.weatherapp.ResourceTable;
import com.vero.weatherapp.data.CityModel;
import com.vero.weatherapp.data.ListItemProvider;
import com.vero.weatherapp.net.HiNet;
import com.vero.weatherapp.net.IHiNet;
import com.vero.weatherapp.util.HiExecutor;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ListContainer;
import ohos.agp.components.RecycleItemProvider;
import org.devio.hi.json.HiJson;

import java.util.*;

public class MainAbilitySlice extends AbilitySlice implements ListItemProvider.OnItemClickListener {
    private ListContainer listContainer;
    private ListItemProvider listItemProvider;
    private ArrayList<CityModel> cityModels = new ArrayList<>();
    private HiNet hiNet;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        hiNet = new HiNet();
        initLayout();
        initCity();
    }

    private void initCity() {
        cityModels.add(new CityModel("深圳", "1100"));
        cityModels.add(new CityModel("北京", "1200"));
        cityModels.add(new CityModel("上海", "1300"));
        cityModels.add(new CityModel("广州", "1400"));
        cityModels.add(new CityModel("兴义", "1500"));
        cityModels.add(new CityModel("成都", "1600"));
        cityModels.add(new CityModel("重庆", "1700"));
        cityModels.add(new CityModel("武汉", "1800"));
        cityModels.add(new CityModel("南京", "1900"));
        listItemProvider.setData(cityModels);
        //harmony的bug 需要重新这行，不然不生效？
        listContainer.setItemProvider(listItemProvider);
    }

    private void initLayout() {
        listContainer = (ListContainer) findComponentById(ResourceTable.Id_list);
        listItemProvider = new ListItemProvider(this, this);
        listContainer.setItemProvider(listItemProvider);
    }


    @Override
    public void onItemClick(CityModel model, int position) {
        HiExecutor.runOnBG(new Runnable() {
            @Override
            public void run() {
                System.out.println("----HiExecutor====子线程===" + Thread.currentThread());


                HiExecutor.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("----HiExecutor====主线程" + Thread.currentThread());


                    }
                });
            }
        });
    }

    private void loadData(CityModel cityModel) {
        Map<String, String> params = new HashMap<>();
        params.put("city", cityModel.cityCode);
        params.put("key", "");

        hiNet.get("", params, new IHiNet.NetListener() {
            @Override
            public void onSuccess(HiJson res) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
