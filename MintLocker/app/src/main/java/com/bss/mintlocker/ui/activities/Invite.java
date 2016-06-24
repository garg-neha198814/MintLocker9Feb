package com.bss.mintlocker.ui.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;

public class Invite extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;

    EditText emailEditText;
    Button inviteButton;
    RelativeLayout inviteLink;
    TextView inviteLinkTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_invite);

        //inflate toolbar
        inflateToolbar();

        // initialize view
        initialiseView();
    }

    // function to show the toolbar at the top of activity
    public void inflateToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(Constants.homeButtonDrawable());
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText("Invite");
    }

    // function to initialise the view
    public void initialiseView() {
        emailEditText = (EditText) findViewById(R.id.email);
        inviteButton = (Button) findViewById(R.id.inviteButton);
        inviteLink = (RelativeLayout) findViewById(R.id.inviteLink);
        inviteLinkTextView = (TextView) findViewById(R.id.inviteLinkText);

        inviteButton.setOnClickListener(this);
        inviteLink.setOnClickListener(this);
        inviteLink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("inviteLink", inviteLinkTextView.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Constants.showToast("Copied to clipboard", 0);
                } catch (Exception e) {
                    Constants.showToast("Unable to copy the fragment_invite code to clipboard", 0);
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inviteButton:

                break;
            case R.id.inviteLink:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, inviteLinkTextView.getText().toString());
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Invite Via.."));
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return super.onKeyDown(keyCode, event);
        }
        return false;
    }
}
