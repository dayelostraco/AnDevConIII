package com.sparcedge.andevconiii;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

/**
 * User: dayelostraco
 * Date: 5/15/12
 * Time: 1:56 PM
 */
public class ActionBarExampleActivity extends SherlockActivity {

    public static final int REQUEST_CODE = 5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}