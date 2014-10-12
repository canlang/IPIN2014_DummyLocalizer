package ipin2014.competition.localizer.comm;


import ipin2014.competition.common.io.BluetoothCallback;
import ipin2014.competition.common.io.BluetoothConnectivity;
import ipin2014.competition.common.io.BluetoothPair;
import ipin2014.competition.common.protocol.Location;
import android.content.Context;
import android.widget.Toast;

public final class LocationSender {
	public static final String LOGTAG = LocationSender.class.getSimpleName();

	private Context mContext = null;
	
	private BluetoothConnectivity mConnectivity = null;
	private BluetoothPair mPair = null;
	
	public LocationSender(Context context) {
		mContext = context;
		mConnectivity = new BluetoothConnectivity();
	}
	
	public void listen() {
		mConnectivity.listen(mBtComm);
	}
	
	
	public boolean sendLocation(double lat, double lng, int lvl) {
		if ( mPair != null ) {
			mPair.sendObject(new Location(lat, lng, lvl, System.currentTimeMillis()));
			return true;
		}
		return false;
	}
	
	private BluetoothCallback mBtComm = new BluetoothCallback() {
		@Override
		public void onRecvObject(Object obj) {
			
		}		
	
		@Override
		public void onConnect(BluetoothPair pared) {
			Toast.makeText(mContext, "Successfully connected.", Toast.LENGTH_SHORT).show();
			mPair = pared;
		}
		
		@Override
		public void onConnectError() {
			Toast.makeText(mContext, "Connection failed.", Toast.LENGTH_SHORT).show();
			mPair = null;
		}
	};
	
	
	protected void toastMessage(String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}
}
