package com.example.myfirstapp;

import com.example.myfirstapp.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.ace.ability.AceAbility;

public class MainAbility extends AceAbility {
    @Override
    public void onStart(Intent intent) {
        //打开js page
        setInstanceName("default");
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
    }
}
