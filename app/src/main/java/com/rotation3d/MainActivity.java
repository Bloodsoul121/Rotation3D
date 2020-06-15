package com.rotation3d;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.big_card)
    ImageView mCardIv;
    @BindView(R.id.big_radiance)
    ImageView mRadianceIv;
    @BindView(R.id.big_card_gift)
    ImageView mBigCardGiftIv;
    @BindView(R.id.big_card_gift_num)
    TextView mBigCardGiftNumTv;
    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCardIv.setImageResource(R.drawable.sign_in_gold_card_back_big);
    }

    @OnClick(R.id.big_card)
    public void onViewClicked() {
        if (mAnimatorSet != null && mAnimatorSet.isStarted()) {
            Toast.makeText(this, "anim is running", Toast.LENGTH_SHORT).show();
            return;
        }

        mAnimatorSet = new AnimatorSet();
        ObjectAnimator alphaAnimCard1 = ObjectAnimator.ofFloat(mCardIv, "alpha", 0f, 1f);
        alphaAnimCard1.setDuration(300);
        ObjectAnimator alphaAnimCard2 = ObjectAnimator.ofFloat(mCardIv, "alpha", 1f, 0f);
        alphaAnimCard2.setDuration(300);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mCardIv, "scaleX", 0.6f, 1f);
        scaleXAnim.setDuration(300);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mCardIv, "scaleY", 0.6f, 1f);
        scaleYAnim.setDuration(300);

        ObjectAnimator rotation3DBackAnim1 = ObjectAnimator.ofFloat(mCardIv, "rotationY", 0f, -13f);
        rotation3DBackAnim1.setDuration(300);
        ObjectAnimator rotation3DBackAnim2 = ObjectAnimator.ofFloat(mCardIv, "rotationY", -13f, 90f);
        rotation3DBackAnim2.setDuration(500);
        ObjectAnimator rotation3DFrontAnim = ObjectAnimator.ofFloat(mCardIv, "rotationY", -90f, 0f);
        rotation3DFrontAnim.setDuration(400);

        ObjectAnimator alphaAnimRadiance1 = ObjectAnimator.ofFloat(mRadianceIv, "alpha", 0f, 1f);
        alphaAnimRadiance1.setDuration(400);
        alphaAnimRadiance1.setStartDelay(1100);
        ObjectAnimator alphaAnimRadiance2 = ObjectAnimator.ofFloat(mRadianceIv, "alpha", 1f, 0f);
        alphaAnimRadiance2.setDuration(300);
        alphaAnimRadiance2.setStartDelay(3200);
        ObjectAnimator rotationAnimRadiance = ObjectAnimator.ofFloat(mRadianceIv, "rotation", 0f, 360f);
        rotationAnimRadiance.setDuration(3500);

        ObjectAnimator rotationAnimGift1 = ObjectAnimator.ofFloat(mBigCardGiftIv, "alpha", 0f, 1f);
        rotationAnimGift1.setDuration(300);
        rotationAnimGift1.setStartDelay(1300);
        ObjectAnimator rotationAnimGift2 = ObjectAnimator.ofFloat(mBigCardGiftIv, "alpha", 1f, 0f);
        rotationAnimGift2.setDuration(300);
        rotationAnimGift2.setStartDelay(3200);

        ObjectAnimator rotationAnimGiftNum1 = ObjectAnimator.ofFloat(mBigCardGiftNumTv, "alpha", 0f, 1f);
        rotationAnimGiftNum1.setDuration(300);
        ObjectAnimator rotationAnimGiftNum2 = ObjectAnimator.ofFloat(mBigCardGiftNumTv, "alpha", 1f, 0f);
        rotationAnimGiftNum2.setDuration(300);

        rotation3DFrontAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mCardIv.setImageResource(R.drawable.sign_in_gold_card_front_big);
            }
        });

        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mCardIv.setImageResource(R.drawable.sign_in_gold_card_back_big);
                mRadianceIv.setAlpha(0f);
                mRadianceIv.setImageResource(R.drawable.sign_in_radiance_big);
                mBigCardGiftIv.setAlpha(0f);
                mBigCardGiftIv.setImageResource(R.drawable.sign_in_treasure_box_icon);
                mBigCardGiftNumTv.setAlpha(0f);
                mBigCardGiftNumTv.setText("×99");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mCardIv.setAlpha(1f);
                mCardIv.setImageResource(R.drawable.sign_in_gold_card_back_big);
            }
        });

        mAnimatorSet.playTogether(alphaAnimCard1, scaleXAnim, scaleYAnim);
        mAnimatorSet.play(rotation3DBackAnim1).after(alphaAnimCard1);
        mAnimatorSet.play(rotation3DBackAnim2).after(rotation3DBackAnim1);
        mAnimatorSet.play(rotation3DFrontAnim).after(rotation3DBackAnim2);
        mAnimatorSet.play(alphaAnimRadiance1);
        mAnimatorSet.play(alphaAnimRadiance2).with(alphaAnimCard2);
        mAnimatorSet.play(rotationAnimRadiance);
        mAnimatorSet.playTogether(rotationAnimGift1, rotationAnimGiftNum1);
        mAnimatorSet.playTogether(rotationAnimGift2, rotationAnimGiftNum2);
        mAnimatorSet.start();
    }
}
