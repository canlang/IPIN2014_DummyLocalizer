Two apps are prepared for the IPIN2014 competitors: one is a logging app to collect the estimated location information from positioning systems, and the other one is a dummy app to send an empty location data to the logging app via bluetooth communications. Here, the **dummy app.**(dummy localizer) generates empty locations just for test and to be refered by competitors own positioning systems. The competitors positioning systems are supposed to send their estimated location information to the logging app in the same way as the dummy app does. The IPIN2014 common library have been prepared to support this.

## Major features

* IPIN'14 competition common library : `libs/ipin2014_common.jar`
* 1:1 Bluetooth data communication
* Empty location output generation
* Sending the output to the logging app

## The connections between the apps and a positioning system
![interaction2](https://cloud.githubusercontent.com/assets/420433/4608526/8206e612-5282-11e4-8a37-04112f015673.png)

## Demos

This is a sample sequence of location logging:

1.	_(Logging App)_ Select a competitor ID from the list. Initially, dummy list of competitors is provided. 
2.	_(Dummy App)_ Click the “listen” button.
3.	_(Logging App)_ Click the “scan” button to find the measurement device to be paired.
4.	_(Logging App)_ Select the device and click the “connect” button to pair with the device. The mac address of the selected device is presented on the top of the app.
5.	_(Logging App)_ After the pairing is made, a button represents current key point is activated. Initially, dummy list of key point is provided.
* _(Dummy App)_ When you click “send location” button, dummy app send location (.0, .0, 0) to logging app. **However, the implemented measurement app should send an estimated location periodically at highest frequency.** The most recent location log is presented on the top of the application.

![demo1](https://cloud.githubusercontent.com/assets/420433/4610527/94f69848-52b0-11e4-8a34-7ec521328518.png)

## Files

* Logging App. 		: `IPIN2014_Logger.apk`
* Dummy App. 		: `bin/IPIN2014_DummyLocalizer.apk`
* IPIN Common Lib.	: `libs/ipin2014_common.jar`

## Download

Download this repository. Copy the apk file to an Android that can run 4.x or greater.

## IPIN'14 competition common library
### LocationSender class

```java
LocationSender(Context context)
```

Class which has the bluetooth connectivity and pairing information.

```java
LocationSender.listen()
```

Keep listening until a socket is connected.

After this method is called, you can make a connection by pushing "Connect" button on logging app.
Note that when this method is called, the bluetooth adapter should be discoverable by logging app to make a connection.

```java
sendLocation(double lat, double lng, int level)
```

Send estimated location with timestamp to the connected logging app.

The coordinate of the location should be presented in latitude and longitude. You have to refer and calculate latitude and longitude of the map which been provided. It also should contain the information of floor level.
This method should be called whenever you have a new positioning result, while the bluetooth connection is maintained.

## Sample Code Review

```java
private View.OnClickListener mBtnListener = new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        switch ( v.getId() ) {
        case R.id.btn_listen:
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);

            mSender.listen();

            break;
        case R.id.btn_sendLocation:
            mSender.sendLocation(.0, .0, 0);
            break;
        }
    }
};
```
** comments: **

1. When `listen` button clicked, make bluetooth discoverable and listen
2. When `send` button clicked, send the location (lat, lng, level) to logging application
3. 2 process must be done after connect with logging application

## Logging Application Output

* Output files are located in `/sdcard/ipin2014/` folder and two text files will be create and the format like this:
	* `<id>_keypoint.txt`: < id >, timestamp, keypoint numbering(eg, 0,1,2,3,...)
	* `<id>_keypoint.txt`: < id >, timestamp(recv), timestampe(send), lat, lng, floor level

## Support

This work was contributed by Intelligent Service Integration Laboratory([ISILAB](http://isilab.kaist.ac.kr)) of KAIST. If you need help using Dummy Localizer, or have found a bug, please create an issue on the <a href="https://github.com/canlang/IPIN2014_DummyLocalizer/issues" target="_blank">GitHub repo</a>