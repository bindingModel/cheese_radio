package com.cheese.radio.ui.user.product.list;

import android.os.Bundle;
import android.view.View;

import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityProductListBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/24.
 */
@ModelView(R.layout.activity_product_list)
public class ProductsModel extends RecyclerModel<ProductsActivity,ActivityProductListBinding,ProductsEntity> {
     private int productId;
     private int courseTypeId;
    @Inject ProductsModel(){}
    @Inject RadioApi api;
    private ProductsParams params=new ProductsParams("getProducts2");
    private final List<ProductsEntity> list=new ArrayList<>();
    @Override
    public void attachView(Bundle savedInstanceState, ProductsActivity productsActivity) {
        super.attachView(savedInstanceState, productsActivity);
        getDataBinding().layoutRecycler.setVm(this);
        courseTypeId = getT().getIntent().getIntExtra(Constant.courseTypeId,0);
        productId= getT().getIntent().getIntExtra(Constant.productId,0);
        params.setCourseTypeId(courseTypeId);
        setRcHttp((offset1, refresh) -> api.getProduct2(params).compose(new RestfulTransformer<>()).map(
                productsEntities -> {
                    for (ProductsEntity entity:
                            productsEntities ) {
                        if(entity.getId()==productId){
                            entity.checked.set(true);
                        }
                    }
                    list.clear();
                    list.addAll(productsEntities);
                    return list;
                }
        ));
        //getProduct();
        addEventAdapter((position, o, type, view)-> {
            for (ProductsEntity entity: getAdapter().getList()) {
                entity.checked.set(false);
            }
            o.checked.set(true);
//            getDataBinding().save.setVisibility(View.VISIBLE);
            getT().finish();
            return false;
        });
    }

    private void getProduct() {
        setRcHttp((offset1, refresh) -> api.getProducts(params).compose(new RestfulTransformer<>()).map(
                productsEntities -> {
                    for (ProductsEntity entity:
                    productsEntities ) {
                       if(entity.getId()==productId){
                           entity.checked.set(true);
                       }
                    }
                    list.clear();
                    list.addAll(productsEntities);
                    return list;
                }
        ));
    }

    public void onFinishClick(View view){
        finish();
    }


}
