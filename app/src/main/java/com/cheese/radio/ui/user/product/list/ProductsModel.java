package com.cheese.radio.ui.user.product.list;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.App;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Inflate;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityProductListBinding;
import com.cheese.radio.inject.api.RadioApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/24.
 */
@ModelView(R.layout.activity_product_list)
public class ProductsModel extends RecyclerModel<ProductsActivity,ActivityProductListBinding,ProductsEntity> {

    @Inject ProductsModel(){}
    @Inject RadioApi api;
    private ProductsParams params=new ProductsParams("getProducts");
    private final List<ProductsEntity> list=new ArrayList<>();
    @Override
    public void attachView(Bundle savedInstanceState, ProductsActivity productsActivity) {
        super.attachView(savedInstanceState, productsActivity);
        getDataBinding().layoutRecycler.setVm(this);

        setRcHttp((offset1, refresh) -> api.getProducts(params).compose(new RestfulTransformer<>()));
        addEventAdapter((position, o, type, view)-> {
            for (ProductsEntity entity: getAdapter().getList()) {
                entity.checked.set(false);
            }
            o.checked.set(true);
            return false;
        });
    }


}
