/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.projects.moi.ca.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.projects.moi.ca.data.entity.NewsEntity;
import com.projects.moi.ca.data.entity.mapper.NewsEntityJsonMapper;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

    /**
     * The context app
     */
    private final Context context;

    /**
     * The News entity json mapper
     */
    private final NewsEntityJsonMapper userEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param userEntityJsonMapper {@link NewsEntityJsonMapper}.
     */
    public RestApiImpl(Context context, NewsEntityJsonMapper userEntityJsonMapper) {
        if (context == null || userEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    /**
     * News entity list
     * @return the news entity list
     */
    @Override
    public List<NewsEntity> newsEntityList() {
        List<NewsEntity> listUserEntity = new ArrayList<>();
        if (isThereInternetConnection()) {
            try {
                String responseUserEntities = getUserEntitiesFromApi();
                if (responseUserEntities != null) {

                    listUserEntity = new ArrayList<>();
                    listUserEntity.add(new NewsEntity("NETWORK New 1", "NETWORK The new 1"));
                    listUserEntity.add(new NewsEntity("NETWORK New 2", "NETWORK The new 2"));
                    listUserEntity.add(new NewsEntity("NETWORK New 3", "NETWORK The new 3"));
                    listUserEntity.add(new NewsEntity("NETWORK New 4", "NETWORK The new 4"));
                    listUserEntity.add(new NewsEntity("NETWORK New 5", "NETWORK The new 5"));

                    // TODO: transform user entity
//                    subscriber.onNext(userEntityJsonMapper.transformUserEntityCollection(
//                            responseUserEntities));
//                    subscriber.onCompleted();
                } else {
                    // launch error
//                    subscriber.onError(new NetworkConnectionException());
                }
            } catch (Exception e) {
                // launch error
//                subscriber.onError(new NetworkConnectionException(e.getCause()));
            }
        } else {
            // launch error
//            subscriber.onError(new NetworkConnectionException());
        }

        return listUserEntity;
    }

    private String getUserEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(RestApi.API_URL_GET_NEWS_LIST).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

}
