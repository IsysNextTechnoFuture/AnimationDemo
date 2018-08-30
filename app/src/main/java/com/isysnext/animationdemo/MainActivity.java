package com.isysnext.animationdemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class MainActivity extends AppCompatActivity implements android.app.FragmentManager.OnBackStackChangedListener {
    private boolean mShowingBack = false;
    public static NiftyDialogBuilder materialDesignAnimatedDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,new CardFontFragment())
                    .commit();
        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }
        getFragmentManager().addOnBackStackChangedListener(this);
        materialDesignAnimatedDialog = NiftyDialogBuilder.getInstance(this);
    }
            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                super.onCreateOptionsMenu(menu);
                MenuItem item = menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
                        mShowingBack ?
                                R.string.action_photo :
                                R.string.action_info);
                item.setIcon(mShowingBack ?
                        R.drawable.ic_action_photo :
                        R.drawable.ic_action_info);
                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                return true;
            }
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:
                        // Navigate "up" the demo structure to the launchpad activity.
                        // See http://developer.android.com/design/patterns/navigation.html for more.
                        NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
                        return true;
                    case R.id.action_flip:
                        flipCard();
                        return true;
                    case R.id.dialog:
                        //buildDialog();
                        return true;
                }
                return super.onOptionsItemSelected(item);
            }
            private void flipCard() {
                if (mShowingBack) {
                    getFragmentManager().popBackStack();
                    return;
                }
                // Flip to the back.
                mShowingBack = true;
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.animator.right_in, R.animator.right_out,
                                R.animator.left_in, R.animator.left_out)
                        .replace(R.id.container, new CardBackFragment())
                        // Add this transaction to the back stack, allowing users to press Back
                        // to get to the front of the card.
                        .addToBackStack(null)
                        // Commit the transaction.
                        .commit();
            }
            @Override
            public void onBackStackChanged() {
                mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
                // When the back stack changes, invalidate the options menu (action bar).
                invalidateOptionsMenu();
            }
            public static class CardFontFragment extends android.app.Fragment {
                public CardFontFragment() {}
                private View view;
                private TextView fliphDialog,fallDialog,shakeDialog,slideTopDialog,slideBottomDialog;
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    view = inflater.inflate(R.layout.fragment_card_front, container, false);
                    TextView tv_dialog = view.findViewById(R.id.tv_dialog);
                    slideBottomDialog = view.findViewById(R.id.tv_slide_bottom_dialog);
                    slideTopDialog = view.findViewById(R.id.tv_slide_top_dialog);
                    shakeDialog = view.findViewById(R.id.tv_shake_dialog);
                    fallDialog =  view.findViewById(R.id.tv_fall_dialog);
                    fliphDialog = view.findViewById(R.id.tv_fliph_dialog);
                    tv_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setTitle("Message Dialog");
                            builder.setMessage("Test Dialog");
                            builder.setNegativeButton("OK", null);
                            AlertDialog dialog = builder.create();
                            dialog.getWindow().getAttributes().windowAnimations =  R.style.DialogAnimation;
                            dialog.show();
                        }
                    });
                    slideTopDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                materialDesignAnimatedDialog
                                        .withTitle("SlideTop Animation Dialog")
                                        .withMessage("Show Your Content Here. Content Description.")
                                        .withDialogColor("#7f0000")
                                        .withButton1Text("OK")
                                        .withButton2Text("Cancel")
                                        .withDuration(700)
                                        .withEffect(Effectstype.Slidetop)
                                        .show();
                            }
                    });
                    shakeDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            materialDesignAnimatedDialog
                                    .withTitle("Shake Animation Dialog")
                                    .withMessage("Show Your Content Here. Content Description.")
                                    .withDialogColor("#7f0000")
                                    .withButton1Text("OK")
                                    .withButton2Text("Cancel")
                                    .withDuration(700)
                                    .withEffect(Effectstype.Shake)
                                    .show();
                        }
                    });
                    fallDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            materialDesignAnimatedDialog
                                    .withTitle("Fall Animation Dialog")
                                    .withMessage("Show Your Content Here. Content Description.")
                                    .withDialogColor("#7f0000")
                                    .withButton1Text("OK")
                                    .withButton2Text("Cancel")
                                    .withDuration(700)
                                    .withEffect(Effectstype.Fall)
                                    .show();
                        }
                    });
                    fliphDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            materialDesignAnimatedDialog
                                    .withTitle("Fliph Animation Dialog")
                                    .withMessage("Show Your Content Here. Content Description.")
                                    .withDialogColor("#7f0000")
                                    .withButton1Text("OK")
                                    .withButton2Text("Cancel")
                                    .withDuration(700)
                                    .withEffect(Effectstype.Fliph)
                                    .show();
                        }
                    });
                    slideBottomDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            materialDesignAnimatedDialog
                                    .withTitle("Slide Bottom Animation Dialog")
                                    .withMessage("Show Your Content Here. Content Description.")
                                    .withDialogColor("#7f0000")
                                    .withButton1Text("OK")
                                    .withButton2Text("Cancel")
                                    .withDuration(700)
                                    .withEffect(Effectstype.SlideBottom)
                                    .show();
                        }
                    });
                    return view;

                }
            }
            public static class CardBackFragment extends android.app.Fragment {
                public CardBackFragment() {}
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                         Bundle savedInstanceState) {
                    return inflater.inflate(R.layout.fragment_card_back, container, false);
                }
            }
    private void buildDialog() {

    }
    }

