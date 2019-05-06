# PushSDK

[ ![Download](https://api.bintray.com/packages/alfa-cell/PushSdk/com.alfa.cell.push.sdk/images/download.svg?version=1.0.0) ](https://bintray.com/alfa-cell/PushSdk/com.alfa.cell.push.sdk/1.0.0/link)

## Getting started

### Setting up the dependency
The first step is to include PushSdk into your project:

Gradle:
```groovy
dependencies {
    implementation 'com.alfa.cell.push.sdk:sdk:1.0.0'
}
```

### Setting up the SDK
The second step is to initialize SDK, with two parameters:  
APPLICATION_CODE you can get from the [alfa-cell website](https://dashboard.alfa-cell.com/)  
UNIQUE_DEVICE_ID is any unique device identifier.  
You can initialize the SDK in Application or Activity.

```java
import com.alfa.cell.push.sdk.MobilyPushService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobilyPushService.init(APPLICATION_CODE, UNIQUE_DEVICE_ID);
    }
}
```

The third step is to register your push token with SDK.  
You can get this token from the Firebase Cloud Messaging and register it in the Activity or in the FCM services

```java
public void tokenRegistration(String token) {
        MobilyPushService.registerToken(token, new MobilyPushService.RegisterListener() {
            @Override
            public void onRegistered(RegisterResult result) {
              
            }

            @Override
            public void onError(Throwable throwable) {
              
            }
        });
    }
```

The fourth step is to register your push status with SDK.  
The messageId you will receive from the json from the key "messageId".
```java
@Override
public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            if (remoteMessage.getData().containsKey("messageId")) {
               pushStatusRegistration(remoteMessage.getData().get("messageId"));
            }
        }
}

public void pushStatusRegistration(String messageId) {
        MobilyPushService.pushDelivered(messageId, new MobilyPushService.PushDataListener() {
            @Override
            public void onDelivered(DeliveryResult result) {
              
            }

            @Override
            public void onError(Throwable throwable) {
              
            }
        });
    }
```

Optional you can register the user in our alfa-cell service with SDK
```java
import com.alfa.cell.push.sdk.models.User;

public void userRegistration(User user) {
        MobilyPushService.registerUser(user, new MobilyPushService.UserRegisterListener() {
            @Override
            public void onRegistered(UserResult result) {
              
            }

            @Override
            public void onError(Throwable throwable) {
              
            }
        });
    }
```

### License

PushSDK is released under the [Apache 2.0 license](LICENSE).

```
Copyright 2008 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```