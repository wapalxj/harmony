package com.vero.weatherapp.data;

import com.vero.weatherapp.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.ArrayList;

/**
 * android ada
 */
public class ListItemProvider extends BaseItemProvider {
    private AbilitySlice mSlice;
    private ArrayList<DataModel> dataModels = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public ListItemProvider(AbilitySlice mSlice, OnItemClickListener onItemClickListener) {
        this.mSlice = mSlice;
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(ArrayList<CityModel> cityModels) {
        dataModels.clear();
        int i = 0;
        ArrayList<CityModel> tempList = new ArrayList<>();

        for (CityModel model : cityModels) {
            if (i == 3) {
                i = 0;
                dataModels.add(new DataModel(tempList));
                tempList = new ArrayList<>();
            }
            tempList.add(model);
            i++;
        }
        dataModels.add(new DataModel(tempList));
        this.notifyDataChanged();
    }

    @Override
    public int getCount() {
        return dataModels.size();
    }

    @Override
    public DataModel getItem(int position) {
        return dataModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component component, ComponentContainer componentContainer) {
        Component component1 = LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_list_item, null, false);

        if (!(component1 instanceof ComponentContainer)) {
            return null;
        }
        ComponentContainer rootLayout = (ComponentContainer) component1;
        DataModel dataModel = dataModels.get(position);
        for (CityModel model : dataModel.cityModels) {
            Text titleItem = (Text) LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_item_title, null, false);
            titleItem.setText(model.cityName);
            rootLayout.addComponent(titleItem);
            titleItem.setClickedListener(new Component.ClickedListener() {
                @Override
                public void onClick(Component component) {
                    onItemClickListener.onItemClick(model, position);
                }
            });

        }
        return component1;
    }

    public interface OnItemClickListener {
        void onItemClick(CityModel model, int position);
    }
}
