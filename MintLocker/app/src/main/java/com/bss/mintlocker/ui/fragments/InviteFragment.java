package com.bss.mintlocker.ui.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bss.mintlocker.constants.Constants;
import com.bss.mintlocker.R;

/**
 * Created by bhawanisingh on 06/12/15.
 */
public class InviteFragment extends Fragment implements View.OnClickListener {
    EditText emailEditText;
    Button inviteButton;
    RelativeLayout inviteLink;
    TextView inviteLinkTextView;
    public InviteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_invite, container, false);
        emailEditText = (EditText) v.findViewById(R.id.email);
        inviteButton = (Button) v.findViewById(R.id.inviteButton);
        inviteLink = (RelativeLayout) v.findViewById(R.id.inviteLink);
        inviteLinkTextView = (TextView) v.findViewById(R.id.inviteLinkText);

        inviteButton.setOnClickListener(this);
        inviteLink.setOnClickListener(this);
        inviteLink.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("inviteLink", inviteLinkTextView.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Constants.showToast("Copied to clipboard", 0);
                } catch (Exception e) {
                    Constants.showToast("Unable to copy the invite code to clipboard", 0);
                }
                return true;
            }
        });

        return v;

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
}

