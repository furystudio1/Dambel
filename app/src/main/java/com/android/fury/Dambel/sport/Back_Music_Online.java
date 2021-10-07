package com.android.fury.Dambel.sport;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.android.fury.Dambel.R;

/**
 * Created by fury on 2/28/2017.
 */
public class Back_Music_Online extends Service {

    public static MediaPlayer music;

    SharedPreferences sport_namber;

    SharedPreferences.Editor nameEditor_sport_namber;

    int m,mm,nov ;

    String music_check_1 , music_off_check_1;

    String mo1 = "http://vip.opload.ir/vipdl/96/1/furystudio_1/11-Dimitri-Vegas-Like-Mike-Martin-Garrix-Tremor-320-2-.mp3";
    String mo2 = "http://vip.opload.ir/vipdl/96/1/furystudio_1/12-Armin-Van-Buuren-Ping-Pong-320-1-.mp3";
    String mo3 = "http://vip.opload.ir/vipdl/96/1/furystudio_1/2.mp3";
    String mo4 = "http://vip.opload.ir/vipdl/96/1/furystudio_1/1.mp3";
    String mo5 = "http://vip.opload.ir/vipdl/95/12/furystudio_1/mp10.mp3";
    String mo6 = "http://vip.opload.ir/vipdl/96/1/furystudio_1/Dubways-86-2-.mp3";
    String mo7 = "http://vip.opload.ir/vipdl/95/8/furystudio_1/music-online-7-set-2-3-4-5-6.mp3";
    String mo9 = "http://vip.opload.ir/vipdl/95/8/furystudio_1/music-online-9-set-2-3-4-5-6.mp3";
    String mo10 = "http://vip.opload.ir/vipdl/96/1/furystudio_1/Dubways-86-1-.mp3";
    String mo11 = "http://vip.opload.ir/vipdl/95/8/furystudio_1/music-online-11-set-2-3-4-5-6.mp3";
    String mo12 = "http://vip.opload.ir/vipdl/95/8/furystudio_1/music-online-12-set-2-3-4-5-6-ICY-MONCEY.mp3";
    String mo13 = "http://vip.opload.ir/vipdl/95/12/furystudio_1/mp17.mp3";
    String mo14 = "http://vip.opload.ir/vipdl/95/8/furystudio_1/music-online-15.mp3";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            sport_namber = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
            nameEditor_sport_namber = sport_namber.edit();
        } catch (Exception e) {
        }

        m = sport_namber.getInt("nov_music_offline", 1);
        mm = sport_namber.getInt("nov_music_online_mm", 1);
        nov = sport_namber.getInt("nov_music_online", 0);
        music_check_1 = sport_namber.getString("music_check_offline", "k");
        music_off_check_1 = sport_namber.getString("music_off_check_offline", "g");

        if (nov == 1) {

            music = new MediaPlayer();

            if (m == 1) {
                try {
                    music.setDataSource(mo1);
                } catch (Exception e) {
                }
            } else if (m == 2) {
                try {
                    music.setDataSource(mo2);
                } catch (Exception e) {
                }
            } else if (m == 3) {
                try {
                    music.setDataSource(mo3);
                } catch (Exception e) {
                }
            } else if (m == 4) {
                try {
                    music.setDataSource(mo4);
                } catch (Exception e) {
                }
            } else if (m == 5) {
                try {
                    music.setDataSource(mo5);
                } catch (Exception e) {
                }
            } else if (m == 6) {
                try {
                    music.setDataSource(mo6);
                } catch (Exception e) {
                }
            } else if (m == 7) {
                try {
                    music.setDataSource(mo7);
                } catch (Exception e) {
                }
            } else if (m == 9) {
                try {
                    music.setDataSource(mo9);
                } catch (Exception e) {
                }
            } else if (m == 10) {
                try {
                    music.setDataSource(mo10);
                } catch (Exception e) {
                }
            } else if (m == 11) {
                try {
                    music.setDataSource(mo11);
                } catch (Exception e) {
                }
            } else if (m == 12) {
                try {
                    music.setDataSource(mo12);
                } catch (Exception e) {
                }
            } else if (m == 13) {
                try {
                    music.setDataSource(mo13);
                } catch (Exception e) {
                }
            } else if (m == 14) {
                try {
                    music.setDataSource(mo14);
                } catch (Exception e) {
                }
            }

            music.prepareAsync();
            music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer music) {
                    // TODO Auto-generated method stub
                    music.start();
                }
            });

        }else {

            if (mm == 1) {
                try {
                    music = MediaPlayer.create(this, R.raw.music_1);
                } catch (Exception e) {
                    try {
                        music = MediaPlayer.create(this, R.raw.music_1);
                    }catch (Exception a){
                    }
                }
            } else if (mm == 2) {
                try {
                    music = MediaPlayer.create(this, R.raw.music_2);
                } catch (Exception e) {
                    try {
                        music = MediaPlayer.create(this, R.raw.music_2);
                    }catch (Exception a){
                    }
                }
            } else if (mm == 3) {
                try {
                    music = MediaPlayer.create(this, R.raw.music_3);
                } catch (Exception e) {
                    try {
                        music = MediaPlayer.create(this, R.raw.music_3);
                    }catch (Exception a){
                    }
                }
            } else if (mm == 4) {
                try {
                    music = MediaPlayer.create(this, R.raw.music_4);
                } catch (Exception e) {
                    try {
                        music = MediaPlayer.create(this, R.raw.music_4);
                    }catch (Exception a){
                    }
                }
            }
            if (music_check_1 == music_off_check_1) {
                music.stop();
            } else {
                music.start();
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        music.stop();
        music.release();
        music = null;
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        music.stop();
        music.release();
        music = null;

    }

}
