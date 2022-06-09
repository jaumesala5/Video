package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button btn_musica, btn_local, btn_remot;
    private VideoView mVideoView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_musica = findViewById(R.id.btn_musica);
        btn_local = this.findViewById(R.id.btn_local);
        btn_remot = this.findViewById(R.id.btn_remot);
        mVideoView = findViewById(R.id.mVideoView);
        webView = findViewById(R.id.webView);
        music();
        video();
        videoR();
    }

    private void music() {
        btn_musica.setText(R.string.sound_player);
        mediaPlayer = MediaPlayer.create(this, R.raw.bagreee);

        btn_musica.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btn_musica.setText(R.string.sound_player);
                btn_musica.setAlpha(1f);
            }
            else {
                mediaPlayer.start();
                btn_musica.setText(R.string.listening);
                btn_musica.setAlpha(.5f);
            }
        });
    }

    private void video() {
        btn_local.setText(R.string.video_player);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bagre);
        mVideoView.setVideoURI(video);
        mVideoView.setMediaController(new MediaController(this));

        mVideoView.setOnCompletionListener(mp -> {
            btn_local.setText(R.string.video_player);
            btn_local.setAlpha(1f);
        });

        btn_local.setOnClickListener(v -> {
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
                btn_local.setText(R.string.paused);
                btn_local.setAlpha(1f);
            }
            else {
                mVideoView.requestFocus();
                mVideoView.start();
                btn_local.setText(R.string.showing);
                btn_local.setAlpha(.5f);
            }
        });
    }

    private void videoR() {

        btn_remot.setOnClickListener(v -> {
            btn_local.setText(R.string.video_player);
            webView.loadUrl("https://www.youtube.com/watch?v=hFCX42B76oo&t=3610s");
        });
    }

}