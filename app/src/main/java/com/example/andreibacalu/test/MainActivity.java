package com.example.andreibacalu.test;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.KeyAttributes;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintSet;

import static android.view.View.VISIBLE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity implements MotionLayout.TransitionListener {

    private MotionLayout motionLayout;
    private MotionScene motionScene;
    private ConstraintSet startSet;
    private ConstraintSet endSet;
    private View view;
    private MotionScene.Transition transition;
    private MotionScene.Transition transition2;
    private ConstraintSet set1;
    private ConstraintSet set2;
    private ConstraintSet set3;
    private ConstraintSet setEnd;
    private MotionScene.Transition transition1;
    private MotionScene.Transition transition3;
    private MotionScene.Transition transitionEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        motionLayout = findViewById(R.id.root);
        findViewById(R.id.buttonA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button) v).setText(((Button) v).getText().toString() + ((Button) v).getText());
            }
        });
        findViewById(R.id.textView2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                view = v;
                Log.e("!!!", event.toString());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (motionScene == null) {
                        motionScene = new MotionScene(motionLayout);
                        motionScene.getConstraintSetIds();
                        startSet = new ConstraintSet();
                        startSet.clone(motionLayout);
                        set1 = new ConstraintSet();
                        set1.clone(motionLayout);
                        set1.setScaleX(v.getId(), 0.5f);
                        set1.setScaleY(v.getId(), 0.5f);
                        transition1 = buildTransition(motionScene, R.id.t1, R.id.s1, startSet, R.id.s2, set1);
                        transition1.setDuration(900);
                        set2 = new ConstraintSet();
                        set2.clone(motionLayout);
                        set2.setScaleX(v.getId(), 1);
                        set2.setScaleY(v.getId(), 1);
                        transition2 = buildTransition(motionScene, R.id.t2, R.id.s2, set1, R.id.s3, set2);
                        transition2.setDuration(900);
                        set3 = new ConstraintSet();
                        set3.constrainWidth(v.getId(), WRAP_CONTENT);
                        set3.constrainHeight(v.getId(), WRAP_CONTENT);
                        set3.connect(v.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
                        set3.connect(v.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
                        set3.connect(v.getId(), ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM);
                        set3.connect(v.getId(), ConstraintSet.END, motionLayout.getId(), ConstraintSet.END);
                        set3.getConstraint(v.getId()).motion.mPathMotionArc = 1;
                        set3.getConstraint(v.getId()).motion.mTransitionEasing = Easing.NAMED_EASING[2];
                        transition3 = buildTransition(motionScene, R.id.t3, R.id.s3, set2, R.id.e1, set3);
                        transition3.setAutoTransition(4);
                        transition3.setDuration(1500);
                        setEnd = new ConstraintSet();
                        setEnd.connect(v.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
                        setEnd.connect(v.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
                        setEnd.setScaleX(v.getId(), 0.3f);
                        setEnd.setScaleY(v.getId(), 0.3f);
                        setEnd.setRotation(v.getId(), -360);
                        setEnd.setElevation(v.getId(), 5);
                        setEnd.setAlpha(v.getId(), 0);
                        setEnd.connect(R.id.background, ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
                        setEnd.connect(R.id.background, ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
                        setEnd.connect(R.id.background, ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM);
                        setEnd.connect(R.id.background, ConstraintSet.END, motionLayout.getId(), ConstraintSet.END);
                        setEnd.setElevation(R.id.background, 3);
                        setEnd.setVisibility(R.id.background, VISIBLE);
                        transitionEnd = buildTransition(motionScene, R.id.e3, R.id.e1, set3, R.id.e2, setEnd);
                        transitionEnd.setAutoTransition(4);
                        transitionEnd.setDuration(3500);
                        motionLayout.setScene(motionScene);
                        motionLayout.transitionToState(R.id.s1);
                    }
                    motionLayout.transitionToState(R.id.s2);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    motionLayout.transitionToState(R.id.s3);
                    return true;
                }
                return false;
            }
        });
    }

    private void variant1_down() {
        motionScene = new MotionScene(motionLayout);
        MotionScene.Transition transition = createTransition();
        transition.setDuration(1000);
        motionScene.setDuration(1000);
        motionLayout.setTransitionDuration(1000);
        endSet.setScaleX(view.getId(), 0.5f);
        endSet.setScaleY(view.getId(), 0.5f);
        motionScene.addTransition(transition);
        motionScene.setTransition(transition);
        motionLayout.setScene(motionScene);
        motionLayout.setTransitionListener(MainActivity.this);
        motionLayout.transitionToEnd();
    }

    private void variant2_down() {
        motionScene = new MotionScene(motionLayout);
        transition = createTransition();
        transition.setDuration(1000);
        motionScene.setDuration(1000);
        motionLayout.setTransitionDuration(1000);
        endSet.setScaleX(view.getId(), 0.5f);
        endSet.setScaleY(view.getId(), 0.5f);

        ConstraintSet anotherEndSet = new ConstraintSet();
        anotherEndSet.clone(endSet);
        anotherEndSet.clear(view.getId(), ConstraintSet.END);
        anotherEndSet.connect(view.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
        anotherEndSet.connect(view.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
        anotherEndSet.setScaleX(view.getId(), 0.1f);
        anotherEndSet.setScaleY(view.getId(), 0.1f);
        anotherEndSet.setAlpha(view.getId(), 0f);
        transition2 = buildTransition(motionScene, View.generateViewId(), transition.getEndConstraintSetId(), endSet,
                View.generateViewId(), anotherEndSet);

        motionScene.addTransition(transition);
        motionScene.addTransition(transition2);
        motionScene.setTransition(transition);
        motionLayout.setScene(motionScene);
        motionLayout.setTransitionListener(MainActivity.this);
        motionLayout.transitionToEnd();
    }

    private void variant2_up() {
        //motionScene.setTransition(transition2);
        motionScene = new MotionScene(motionLayout);
        MotionScene.Transition transition = createTransition();
        transition.setDuration(10000);
        motionScene.setDuration(10000);
        motionLayout.setTransitionDuration(10000);
        endSet.clear(view.getId(), ConstraintSet.END);
        endSet.connect(view.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
        endSet.connect(view.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
        endSet.setScaleX(view.getId(), 0.1f);
        endSet.setScaleY(view.getId(), 0.1f);
        endSet.setAlpha(view.getId(), 0f);

        KeyAttributes keyAttributes = new KeyAttributes();
        keyAttributes.setValue("scaleX", 0.5f);
        keyAttributes.setValue("scaleY", 0.5f);
        keyAttributes.setValue("framePosition", 1);

        motionScene.addTransition(transition);
        motionScene.setTransition(transition);
        motionLayout.setScene(motionScene);
        motionLayout.transitionToEnd();
    }

    private void variant1_up() {
        motionScene = new MotionScene(motionLayout);
        MotionScene.Transition transition = createTransition();
        endSet.setScaleX(view.getId(), 1f);
        endSet.setScaleY(view.getId(), 1f);
                    /*endSet.connect(v.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
                    endSet.connect(v.getId(), ConstraintSet.END, motionLayout.getId(), ConstraintSet.END);
                    endSet.connect(v.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
                    endSet.connect(v.getId(), ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM);*/
        motionScene.addTransition(transition);
        motionScene.setTransition(transition);
        motionLayout.setScene(motionScene);
        motionLayout.setTransitionListener(MainActivity.this);
        motionLayout.transitionToEnd();
        motionLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                startSet = endSet;
                endSet = new ConstraintSet();
                endSet.clone(motionLayout);
                MotionScene.Transition transition2 = buildTransition(motionScene, View.generateViewId(), View.generateViewId(), startSet, View.generateViewId(), endSet);
                endSet.connect(view.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
                endSet.connect(view.getId(), ConstraintSet.END, motionLayout.getId(), ConstraintSet.END);
                endSet.connect(view.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
                endSet.connect(view.getId(), ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM);
                transition2.setDuration(10000);
                motionScene.addTransition(transition2);
                motionScene.setTransition(transition2);
                motionLayout.setScene(motionScene);
                motionLayout.transitionToEnd();
            }
        }, 300);
    }

    private MotionScene.Transition createTransition() {
        startSet = new ConstraintSet();
        startSet.clone(motionLayout);
        endSet = new ConstraintSet();
        endSet.clone(motionLayout);
        return buildTransition(motionScene, View.generateViewId(), View.generateViewId(), startSet, View.generateViewId(), endSet);
    }

    public static MotionScene.Transition buildTransition(MotionScene scene, int transitionId, int startConstraintSetId, ConstraintSet startConstraintSet, int endConstraintSetId, ConstraintSet endConstraintSet) {
        MotionScene.Transition transition = new MotionScene.Transition(transitionId, scene, startConstraintSetId, endConstraintSetId);
        updateConstraintSetInMotionScene(scene, transition, startConstraintSet, endConstraintSet);
        return transition;
    }

    private static void updateConstraintSetInMotionScene(MotionScene scene, MotionScene.Transition transition, ConstraintSet startConstraintSet, ConstraintSet endConstraintSet) {
        int startId = transition.getStartConstraintSetId();
        int endId = transition.getEndConstraintSetId();
        scene.setConstraintSet(startId, startConstraintSet);
        scene.setConstraintSet(endId, endConstraintSet);
        scene.addTransition(transition);
    }

    @Override
    public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
        Log.e("!!!", "onTransitionStarted");
    }

    @Override
    public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
        Log.e("!!!", "onTransitionChange");
    }

    @Override
    public void onTransitionCompleted(MotionLayout motionLayout, int i) {
        Log.e("!!!", "onTransitionCompleted " + i);
        if (i == 4) {
            MotionScene.Transition transition = createTransition();
            endSet.connect(view.getId(), ConstraintSet.START, motionLayout.getId(), ConstraintSet.START);
            endSet.connect(view.getId(), ConstraintSet.END, motionLayout.getId(), ConstraintSet.END);
            endSet.connect(view.getId(), ConstraintSet.TOP, motionLayout.getId(), ConstraintSet.TOP);
            endSet.connect(view.getId(), ConstraintSet.BOTTOM, motionLayout.getId(), ConstraintSet.BOTTOM);
            motionScene.addTransition(transition);
            motionScene.setTransition(transition);
            motionLayout.transitionToEnd();
        }
    }

    @Override
    public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {
        Log.e("!!!", "onTransitionTrigger");
    }
}
