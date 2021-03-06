package com.example.bogdan.testtest.di.module;

import android.content.Context;

import com.example.bogdan.testtest.App;
import com.example.bogdan.testtest.api.ApiHelper;
import com.example.bogdan.testtest.model.BestiaModel;
import com.example.bogdan.testtest.model.BestiaModelImpl;
import com.example.bogdan.testtest.model.BitmapMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.06.16
 */
@Module
public class AppModule {
    private App mApplication;

    public AppModule(App application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    Context provideAppication() {
        return mApplication;
    }

    @Singleton
    @Provides
    Observable.Transformer provideSchedulerTransformer() {
        return o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Singleton
    @Provides
    BestiaModel provideBestiaModel(ApiHelper apiInterface, Observable.Transformer schedulersTransformer) {
        return new BestiaModelImpl(apiInterface, schedulersTransformer);
    }

    @Singleton
    @Provides
    BitmapMapper provideBitmapMapper() {
        return new BitmapMapper();
    }
}
