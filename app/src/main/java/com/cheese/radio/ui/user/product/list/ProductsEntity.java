package com.cheese.radio.ui.user.product.list;

import android.databinding.ObservableBoolean;
import android.view.View;

import com.binding.model.App;
import com.binding.model.adapter.AdapterType;
import com.binding.model.adapter.IEventAdapter;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewInflateRecycler;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Model;
import com.cheese.radio.R;

import java.util.Objects;

import static com.binding.model.adapter.AdapterType.select;

/**
 * Created by 29283 on 2018/3/24.
 */
@ModelView(R.layout.item_product)
public class ProductsEntity extends ViewInflateRecycler {


    /**
     * price : 600000
     * name : 123131312
     * description : 123131312
     * classHour : 30
     * id : 111
     */

    private int price;
    private String name;
    private String description;
    private int classHour;
    private int id;
    private String img;
    public transient ObservableBoolean checked = new ObservableBoolean();

    public int getPrice() {
        return price;
    }

    public String getPriceText() {
        return "¥ " + String.valueOf(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClassHour() {
        return classHour;
    }

    public String getClassHourText() {
        return String.valueOf(classHour) + "课时";
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void onClick(View view) {
        if (Event.event(R.id.EnrollModel, this, view) == 1)
            getIEventAdapter().setEntity(IEventAdapter.NO_POSITION, this, select, view);

//            Model.dispatchModel("getProductId",this);
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void onSelectAddClick(View view){
        if (Event.event(R.id.EnrollModel, this, view) == 1)
        getIEventAdapter().setEntity(IEventAdapter.NO_POSITION, this, select, view);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity entity = (ProductsEntity) o;
        return id == entity.id;
    }


}
