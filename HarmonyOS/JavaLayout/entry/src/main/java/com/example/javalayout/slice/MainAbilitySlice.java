package com.example.javalayout.slice;

import com.example.javalayout.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;

public class MainAbilitySlice extends AbilitySlice {
    private int count = 0;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);

        //xml 布局
        super.setUIContent(ResourceTable.Layout_main_layout);
        setXml();


        //java 代码布局
//        super.setUIContent(getContainer());
    }
    //xml 布局
    private void setXml() {
        Text text = (Text) findComponentById(ResourceTable.Id_text);
        Button button= (Button) findComponentById(ResourceTable.Id_button);
        button.setText("Click me");
        button.setTextSize(50);
        button.setTextColor(Color.WHITE);

        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(188, 125, 255));
        element.setCornerRadius(20);
        button.setBackground(element);
        button.setPadding(10, 10, 10, 10);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                count++;
                text.setText("vero:" + count);
            }
        });

    }

    //java 代码布局
    private ComponentContainer getContainer() {
        DirectionalLayout directionalLayout = new DirectionalLayout(this);
        directionalLayout.setWidth(ComponentContainer.LayoutConfig.MATCH_PARENT);
        directionalLayout.setHeight(ComponentContainer.LayoutConfig.MATCH_PARENT);
        directionalLayout.setOrientation(Component.VERTICAL);
        directionalLayout.setPadding(30, 30, 30, 30);

        //创建text
        Text text = new Text(this);
        text.setText("vero:" + count);
        text.setTextSize(50);
        DirectionalLayout.LayoutConfig layoutConfig = new DirectionalLayout.LayoutConfig(
                ComponentContainer.LayoutConfig.MATCH_CONTENT,
                ComponentContainer.LayoutConfig.MATCH_CONTENT
        );
        layoutConfig.alignment= LayoutAlignment.HORIZONTAL_CENTER;
        text.setLayoutConfig(layoutConfig);
        directionalLayout.addComponent(text);

        //创建button
        Button button = new Button(this);
        layoutConfig.setMargins(0, 50, 0, 0);
        button.setLayoutConfig(layoutConfig);
        button.setText("Click me");
        button.setTextSize(50);
        button.setTextColor(Color.WHITE);

        ShapeElement element = new ShapeElement();
        element.setRgbColor(new RgbColor(188, 125, 255));
        element.setCornerRadius(20);
        button.setBackground(element);
        button.setPadding(10, 10, 10, 10);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                count++;
                text.setText("vero:" + count);
            }
        });
        directionalLayout.addComponent(button);
        return directionalLayout;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
