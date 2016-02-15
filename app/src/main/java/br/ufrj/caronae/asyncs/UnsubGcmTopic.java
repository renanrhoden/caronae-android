package br.ufrj.caronae.asyncs;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;

import java.io.IOException;

import br.ufrj.caronae.SharedPref;
import br.ufrj.caronae.models.ActiveRideId;

public class UnsubGcmTopic extends AsyncTask<Void, Void, Void> {
    private final String dbId;
    private final GcmPubSub gcmPubSub;

    public UnsubGcmTopic(Context activity, String dbId) {
        this.dbId = dbId;
        gcmPubSub = GcmPubSub.getInstance(activity);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            gcmPubSub.unsubscribe(SharedPref.getUserGcmToken(), "/topics/" + dbId);

            ActiveRideId.deleteAll(ActiveRideId.class, "ride_id = ?", dbId);

            Log.i("logOut", "unsubscribed from ride " + dbId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
