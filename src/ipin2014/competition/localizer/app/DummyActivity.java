package ipin2014.competition.localizer.app;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import ipin2014.competition.localizer.R;
import ipin2014.competition.localizer.comm.LocationSender;

public class DummyActivity extends Activity{
	private LocationSender mSender;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		mSender = new LocationSender(getApplicationContext());
		
		findViewById(R.id.btn_listen).setOnClickListener(mBtnListener);
		findViewById(R.id.btn_sendLocation).setOnClickListener(mBtnListener);		    	
	}
}
