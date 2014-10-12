**Dummy Localizer ** is an random location generator just for testing and help for customise your own positioning application. It helps you attach your positioning system with our logging application.

## Features

* Contain IPIN'14 Competition Common Library : `libs/ipin2014_common.jar`
* 1:1 Bluetooh Data Communication
* Random Coordinates Output Generation
* Sending the Output to Logging Application

## Demos

This is a sample sequence of location logging:

![test image](https://cloud.githubusercontent.com/assets/420433/4607553/dadbd2fe-5253-11e4-8716-be2f648821c4.jpg)


## Download

Download this repository. Copy the apk file to an Android that can run 4.x or greater.

## IPIN'14 Competition Common Library
### LocationSender Class

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

The coordinate of the location should be presented in WGS-84 (World Geodetic System) and it also should contain the information of floor level.
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

1. When `listen` button clicked, make bluetooh discoverable and listen
2. When `send` button clicked, send the location (lat, lng, level) to logging application
3. 2 process must be done after connect with logging application



## Support

If you need help using Daux.io, or have found a bug, please create an issue on the <a href="file:///Users/lang/git/daux.io/static/Getting_Started.html" target="_blank">GitHub repo</a>.
