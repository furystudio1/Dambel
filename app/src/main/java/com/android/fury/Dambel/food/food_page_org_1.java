package com.android.fury.Dambel.food;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.fury.Dambel.App_food;
import com.android.fury.Dambel.Database.MyDatabaseHelper_sound;
import com.android.fury.Dambel.Database.MyDatabaseHelper_vibre;
import com.android.fury.Dambel.R;
import com.android.fury.Dambel.Search;

import java.util.HashMap;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 * Created by fury on 9/1/2016.
 */

public class food_page_org_1 extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public static ListView lview;

    private int mPage;

    public static listviewAdapter adapter;

    int ms = 1;

    private final int _splashTime3 = 250;

    DisplayMetrics displayMetrics;
    private Tooltip.TooltipView mCurrentTooltip;

    HashMap<String, String> map;

    EditText et;

    public static int s = 0;

    public static String FIRST_COLUMN;
    public static String SECOND_COLUMN;
    public static String SECOND_COLUMN_2;

    int time_1 = 1;

    MediaPlayer click;

    Vibrator vb;


    MyDatabaseHelper_sound sound_data;
    MyDatabaseHelper_vibre vibre_data;


    String music_check, music_off_check, vibre_check;

    private final int _splashTime = 2050;

    public static food_page_org_1 newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        food_page_org_1 fragment = new food_page_org_1();
        fragment.setArguments(args);
        return fragment;

    }

    int help_play, help_play2;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        one_play_preferences = getContext().getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        help_play = one_play_preferences.getInt("Help_play_7", 1);
        help_play2 = one_play_preferences.getInt("Help_play_org_2", 1);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        if (mPage == 1) {
            view = inflater.inflate(R.layout.food_page_org_1, container, false);


            try {

                //save music
                sound_data = new MyDatabaseHelper_sound(getActivity());
                Cursor res = sound_data.showAllData();
                StringBuffer data = new StringBuffer();
                while (res.moveToNext()) {
                    data.append(res.getString(1));
                }
                if (data.toString().length() == 4) {
                    music_check = "true";
                } else if (data.toString().length() == 5) {
                    music_check = "false";
                } else {
                    music_check = "true";
                }
                music_off_check = "false";

            } catch (Exception e) {
            }

            try {

                //save vibre
                vibre_data = new MyDatabaseHelper_vibre(getActivity());
                Cursor res_2 = vibre_data.showAllData();
                StringBuffer data_2 = new StringBuffer();
                while (res_2.moveToNext()) {
                    data_2.append(res_2.getString(1));
                }
                if (data_2.toString().length() == 4) {
                    vibre_check = "true";
                } else if (data_2.toString().length() == 5) {
                    vibre_check = "false";
                } else {
                    vibre_check = "false";
                }

            } catch (Exception e) {
            }

            final TextView about3 = (TextView) view.findViewById(R.id.txt_food_day_1_3);

            final RelativeLayout day10 = (RelativeLayout) view.findViewById(R.id.rl_food_day_1_1);


            if (help_play == 1) {

                final String s1111 = (String) getText(R.string.Help_start_24_1);
                final String s22 = (String) getText(R.string.Help_start_24_2);

                displayMetrics = getResources().getDisplayMetrics();

                mCurrentTooltip = Tooltip.make(
                        getActivity(),
                        new Tooltip.Builder(101)
                                .maxWidth((int) (displayMetrics.widthPixels / 2))
                                .anchor(day10, Tooltip.Gravity.TOP)
                                .closePolicy(Tooltip.ClosePolicy.TOUCH_INSIDE_NO_CONSUME, 0)
                                .text(s1111)
                                .fitToScreen(false)
                                .fadeDuration(200)
                                .showDelay(50)
                                .floatingAnimation(Tooltip.AnimationBuilder.DEFAULT)
                                .withStyleId(R.style.ToolTipLayoutCustomStyle)
                                .withCallback(
                                        new Tooltip.Callback() {
                                            @Override
                                            public void onTooltipClose(
                                                    final Tooltip.TooltipView v, final boolean fromUser, final boolean containsTouch) {
                                                mCurrentTooltip = null;
                                            }

                                            @Override
                                            public void onTooltipFailed(Tooltip.TooltipView view) {
                                            }

                                            @Override
                                            public void onTooltipShown(Tooltip.TooltipView view) {
                                            }

                                            @Override
                                            public void onTooltipHidden(Tooltip.TooltipView view) {
                                            }
                                        })
                                .build()
                );
                mCurrentTooltip.show();


            }


            day10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        vb = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        if (vibre_check == music_off_check) {
                            vb.cancel();
                        } else {
                            vb.vibrate(100);
                        }

                    } catch (Exception e) {
                    }

                    try {

                        click = MediaPlayer.create(getActivity(), R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {
                    }

                    if (help_play == 1) {
                        mCurrentTooltip = null;
                        one_play_editor.putInt("Help_play_7", 2);
                        one_play_editor.commit();
                    }

                    about3.setTextColor(getResources().getColor(R.color.background_sport));
                    day10.setBackgroundResource(R.drawable.circle_food_confirm_on);

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            about3.setTextColor(getResources().getColor(R.color.color30day));
                            day10.setBackgroundResource(R.drawable.circle_food_day_off);

                            Bundle colery2 = getActivity().getIntent().getExtras();
                            String colery_2 = colery2.getString("bmr_food_cahesh");
                            String colery_3 = colery2.getString("gender_food_1");
                            String colery_4 = colery2.getString("tall_food_1");
                            String colery_5 = colery2.getString("weight_food_meghdar_1");
                            String colery_6 = colery2.getString("weight_food_1");

                            Intent uou2 = new Intent(getActivity(), food_male_plan_10.class);
                            uou2.putExtra("colery2_came", colery_2);
                            uou2.putExtra("gender_food_2", colery_3);
                            uou2.putExtra("tall_food_2", colery_4);
                            uou2.putExtra("weight_food_meghdar_2", colery_5);
                            uou2.putExtra("weight_food_2", colery_6);
                            uou2.putExtra("zaban_fo", food_page_org.zaban);
                            startActivity(uou2);

                            try {
                                new Handler().postDelayed(new Thread() {
                                    @Override
                                    public void run() {
                                        App_food.close();
                                        getActivity().finish();
                                    }
                                }, _splashTime3);
                            } catch (Exception e) {
                            }

                        }
                    }, _splashTime3);

                }

            });


            final TextView about31 = (TextView) view.findViewById(R.id.txt_food_day_2_3);
            final RelativeLayout day101 = (RelativeLayout) view.findViewById(R.id.rl_food_day_2_1);


            day101.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        vb = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        if (vibre_check == music_off_check) {
                            vb.cancel();
                        } else {
                            vb.vibrate(100);
                        }

                    } catch (Exception e) {
                    }

                    try {

                        click = MediaPlayer.create(getActivity(), R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }

                    about31.setTextColor(getResources().getColor(R.color.background_sport));
                    day101.setBackgroundResource(R.drawable.circle_food_confirm_on);

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            about31.setTextColor(getResources().getColor(R.color.color30day));
                            day101.setBackgroundResource(R.drawable.circle_food_day_off);

                            Bundle colery2 = getActivity().getIntent().getExtras();
                            String colery_2 = colery2.getString("bmr_food_cahesh");
                            String colery_3 = colery2.getString("gender_food_1");
                            String colery_4 = colery2.getString("tall_food_1");
                            String colery_5 = colery2.getString("weight_food_meghdar_1");
                            String colery_6 = colery2.getString("weight_food_1");
                            int colery_7 = 2;

                            Intent uou2 = new Intent(getActivity(), food_male_plan_10.class);
                            uou2.putExtra("colery2_came", colery_2);
                            uou2.putExtra("gender_food_2", colery_3);
                            uou2.putExtra("tall_food_2", colery_4);
                            uou2.putExtra("weight_food_meghdar_2", colery_5);
                            uou2.putExtra("weight_food_2", colery_6);
                            uou2.putExtra("mp", colery_7);
                            uou2.putExtra("zaban_fo", food_page_org.zaban);
                            startActivity(uou2);

                            try {
                                new Handler().postDelayed(new Thread() {
                                    @Override
                                    public void run() {
                                        App_food.close();
                                        getActivity().finish();
                                    }
                                }, _splashTime3);
                            } catch (Exception e) {

                            }


                        }
                    }, _splashTime3);

                }

            });

            final TextView about311 = (TextView) view.findViewById(R.id.txt_food_day_3_3);
            final RelativeLayout day1011 = (RelativeLayout) view.findViewById(R.id.rl_food_day_3_1);

            day1011.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        vb = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        if (vibre_check == music_off_check) {
                            vb.cancel();
                        } else {
                            vb.vibrate(100);
                        }

                    } catch (Exception e) {

                    }

                    try {

                        click = MediaPlayer.create(getActivity(), R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }

                    about311.setTextColor(getResources().getColor(R.color.background_sport));
                    day1011.setBackgroundResource(R.drawable.circle_food_confirm_on);

                    new Handler().postDelayed(new Thread() {
                        @Override
                        public void run() {
                            super.run();

                            about311.setTextColor(getResources().getColor(R.color.color30day));
                            ;
                            day1011.setBackgroundResource(R.drawable.circle_food_day_off);

                            Bundle colery2 = getActivity().getIntent().getExtras();
                            String colery_2 = colery2.getString("bmr_food_cahesh");
                            String colery_3 = colery2.getString("gender_food_1");
                            String colery_4 = colery2.getString("tall_food_1");
                            String colery_5 = colery2.getString("weight_food_meghdar_1");
                            String colery_6 = colery2.getString("weight_food_1");
                            int colery_7 = 3;

                            Intent uou2 = new Intent(getActivity(), food_male_plan_10.class);
                            uou2.putExtra("colery2_came", colery_2);
                            uou2.putExtra("gender_food_2", colery_3);
                            uou2.putExtra("tall_food_2", colery_4);
                            uou2.putExtra("weight_food_meghdar_2", colery_5);
                            uou2.putExtra("weight_food_2", colery_6);
                            uou2.putExtra("mp", colery_7);
                            uou2.putExtra("zaban_fo", food_page_org.zaban);
                            startActivity(uou2);

                            try {
                                new Handler().postDelayed(new Thread() {
                                    @Override
                                    public void run() {
                                        App_food.close();
                                        getActivity().finish();
                                    }
                                }, _splashTime3);
                            } catch (Exception e) {
                                }


                        }
                    }, _splashTime3);

                }

            });

        } else if (mPage == 2) {

            view = inflater.inflate(R.layout.food_page_org_2, container, false);

            lview = (ListView) view.findViewById(R.id.listview);

            try {

                list_food_cal.getInstance(getActivity()).populateList();

            } catch (Exception e) {

            }

            try {

                adapter = new listviewAdapter(getActivity(), list_food_cal.list);

            } catch (Exception e) {

            }

            try {

                lview.setAdapter(adapter);
                lview.setFastScrollEnabled(false);
                lview.setVerticalScrollBarEnabled(false);
                lview.setTextFilterEnabled(true);

            } catch (Exception e) {

            }


            TextView txt_ser = (TextView) view.findViewById(R.id.txt_ser);
            txt_ser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {

                        vb = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                        if (vibre_check == music_off_check) {
                            vb.cancel();
                        } else {
                            vb.vibrate(100);
                        }

                    } catch (Exception e) {

                    }

                    try {

                        click = MediaPlayer.create(getActivity(), R.raw.button);
                        if (music_check == music_off_check) {
                            click.stop();
                        } else {
                            click.start();
                        }

                    } catch (Exception e) {

                    }

                    ms = 2;
                    Intent search = new Intent(getActivity(), Search.class);
                    getActivity().startActivity(search);

                    try {

                        list_food_cal.list.clear();
                        list_food_cal.list.addAll(list_food_cal.list_b);

                    } catch (Exception e) {

                    }


                }
                //   }
            });

        }

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //AdColony.resume(getActivity());

    }

    @Override
    public void onPause() {
        super.onPause();
        //AdColony.pause();

    }
}