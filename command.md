# Install de repo

```bash
mkdir ~/bin
PATH=~/bin:$PATH
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
```

# Initialisation d'un repo android 

```bash
mkdir AOSP
cd AOSP
repo init -u https://android.googlesource.com/platform/manifest -b android-10.0.0_r1
repo sync -j8 -c
```

```go
# choisir la plateform cible
lunch
# m # => build de la totalité de l'os
# mm => uniquement le module current
# mmm <path_to_module> 
```


## Exemple d'utilisation de NotificationManagerService

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.notification_icon)
        .setContentTitle("Ma notification")
        .setContentText("Bonjour depuis AOSP!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
notificationManager.notify(NOTIFICATION_ID, builder.build());
```


## Exemple d'utilisation de connectivity Manager pour vérifier la connectivité réseau

```java
ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
```

## Exemple de communication entre application et un serviceManager 

#### Afficher les paramètres wifit
```java
Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS)
startActivity(intent)
```

### Emettre un appel

```java
Intent intet = new Intent(Intent.ACTION_CALL);
intent.setData(Uri.parse("tel:0101010101"));
startActivity(intent)
```

### Package Manager

```java
PackageManager pm = getPackageManager();
try {
    pm.getPackageInfo("com.example.appname", PackageManager.GET_ACTIVITIES);
    // L'application est installée
} catch (PackageManager.NameNotFoundException e) {
    // L'application n'est pas installée
}
```

```java
import android.hardware.bluetooth@1.0;

IBluetooth bluetooth = IBluetooth.getService()
```


## Commande fastboot

```bash
fastboot flash [partition] [filename.img]
fastboot erase [parition]
fastboot getvar all
fastboot oem unlock
```