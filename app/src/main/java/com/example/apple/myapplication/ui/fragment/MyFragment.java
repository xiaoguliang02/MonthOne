package com.example.apple.myapplication.ui.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyFragment extends Fragment {

    @BindView(R.id.image_animation)
    ImageView imageAnimation;
    @BindView(R.id.text_animation)
    TextView textAnimation;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_layout, null);
        unbinder = ButterKnife.bind(this, view);
        textAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = new ObjectAnimator();
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageAnimation, "scaleY", 1f,2f);
                ObjectAnimator rotationY = ObjectAnimator.ofFloat(imageAnimation, "rotationY", 180f);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(scaleY,rotationY);
                set.setDuration(3000);
                set.start();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
