package com.alfa.cell.push.sdk;

import com.alfa.cell.push.sdk.models.Delivery;
import com.alfa.cell.push.sdk.models.Register;
import com.alfa.cell.push.sdk.models.User;
import com.alfa.cell.push.sdk.models.delivery.DeliveryResult;
import com.alfa.cell.push.sdk.models.register.RegisterResult;
import com.alfa.cell.push.sdk.models.user.UserResult;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

class NetworkClient {

    private static final String BASE_URL = "https://rest.alfa-cell.com/";

    private static final String API_PUSH_DELIVERY = BASE_URL + "message/pushDelivered";
    private static final String API_REGISTER_TOKEN = BASE_URL + "device/token";
    private static final String API_REGISTER_USER = BASE_URL + "device/user";

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PushDeliveredInterface mPushDeliveredInterface;
    private RegisterDeviceInterface mRequestTicketInterface;
    private String mAppCode;
    private String mUserId;
    private String mDeviceId;

    void init(String appCode, String deviceId) {
        mAppCode = appCode;
        mDeviceId = deviceId;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        Retrofit mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setPrettyPrinting()
                        .create()))
                .build();

        mRequestTicketInterface = mRetrofitBuilder.create(RegisterDeviceInterface.class);
        mPushDeliveredInterface = mRetrofitBuilder.create(PushDeliveredInterface.class);
    }

    void registerToken(String token, String packageName, MobilyPushService.RegisterListener listener) {
        if (mDeviceId == null || mDeviceId.isEmpty()) {
            throw new RuntimeException("First must invoke initialization.");
        }
        mCompositeDisposable.add(mRequestTicketInterface.registerToken(new Register(token, mDeviceId, packageName, mAppCode))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(registerResult -> {
                    mUserId = registerResult.getData().getId();
                    if (listener != null) {
                        listener.onRegistered(registerResult);
                    }
                }, error -> {
                    if (listener != null) {
                        listener.onError(error);
                    }
                }));
    }

    void registerUser(User user, MobilyPushService.UserRegisterListener listener) {
        if (mDeviceId == null || mDeviceId.isEmpty()) {
            throw new RuntimeException("First must invoke initialization.");
        }
        user.setAppCode(mAppCode);
        user.setDeviceId(mDeviceId);
        if (mUserId != null && !mUserId.isEmpty()) {
            user.setId(mUserId);
        }
        mCompositeDisposable.add(mRequestTicketInterface.registerUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(registerResult -> {
                    if (listener != null) {
                        listener.onRegistered(registerResult);
                    }
                }, error -> {
                    if (listener != null) {
                        listener.onError(error);
                    }
                }));
    }

    void pushDelivered(String pushID, MobilyPushService.PushDataListener listener) {
        if (mDeviceId == null || mDeviceId.isEmpty()) {
            throw new RuntimeException("First must invoke initialization.");
        }
        mCompositeDisposable.add(mPushDeliveredInterface.delivery(new Delivery(pushID, mDeviceId, mAppCode))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(registerResult -> {
                    if (listener != null) {
                        listener.onDelivered(registerResult);
                    }
                }, error -> {
                    if (listener != null) {
                        listener.onError(error);
                    }
                }));
    }

    interface RegisterDeviceInterface {
        @Headers("Content-Type: application/json")
        @POST(NetworkClient.API_REGISTER_TOKEN)
        Observable<RegisterResult> registerToken(@Body Register register);

        @Headers("Content-Type: application/json")
        @POST(NetworkClient.API_REGISTER_USER)
        Observable<UserResult> registerUser(@Body User user);
    }

    interface PushDeliveredInterface {
        @Headers("Content-Type: application/json")
        @POST(NetworkClient.API_PUSH_DELIVERY)
        Observable<DeliveryResult> delivery(@Body Delivery delivery);
    }
}