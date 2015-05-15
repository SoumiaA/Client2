package dz.esi.life.network;


import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Soham Banerjee on 1/25/15.
 */

public class RestClient {

    private static Restapi REST_CLIENT;
    public static String ROOT = "https://frozen-peak-9792.herokuapp.com";


    static {
        setupRestClient();
    }

    public static Restapi get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {

        //First create a new okhttpClient (this is okhttpnative)
        OkHttpClient client = new OkHttpClient(); //create OKHTTPClient
        //create a cookieManager so your client can be cookie persistant
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieManager); //finally set the cookie handler on client
        //OkClient is retrofit default client, ofcourse since is based on OkHttClient
        //you can decorate your existing okhttpclient with retrofit's okClient
        OkClient serviceClient = new OkClient(client);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ROOT)
                .setClient(serviceClient)
                .setRequestInterceptor(new SessionRequestInterceptor())
                .build();

        REST_CLIENT = restAdapter.create(Restapi.class);
    }
}