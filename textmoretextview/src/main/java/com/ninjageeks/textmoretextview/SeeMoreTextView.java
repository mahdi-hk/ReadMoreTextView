package com.ninjageeks.textmoretextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


public class SeeMoreTextView extends androidx.appcompat.widget.AppCompatTextView {

    public String TextOpenSeeMore = "show more";
    public String TextExitSeeMore = "show less";

    public int TextColorOpenMore;
    public int TextColorExitMore;

    public int mode;
    public int length;
    public static boolean is_open = false;
    public SeeMoreTextView(Context context) {
        this(context, null);
    }

    public SeeMoreTextView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        if (is_open == false){
            TypedArray property = context.obtainStyledAttributes(attrs, R.styleable.SeeMoreTextView);
            //
            this.TextOpenSeeMore =property.getString(R.styleable.SeeMoreTextView_setTextOpenSeeMore);
            this.TextExitSeeMore =property.getString(R.styleable.SeeMoreTextView_setTextExitSeeMore);
            //
            this.TextColorOpenMore = property.getColor(R.styleable.SeeMoreTextView_TextColorOpenMore, ContextCompat.getColor(context, R.color.accent));
            //
            this.TextColorExitMore = property.getColor(R.styleable.SeeMoreTextView_TextColorOpenMore, ContextCompat.getColor(context, R.color.colorPrimary));
            //
            this.mode = property.getInt(R.styleable.SeeMoreTextView_setMoreType,0);
            //
            this.length = property.getInt(R.styleable.SeeMoreTextView_setLength,100);
            //
            SpannableString ss;
            if (mode == 0){
                //
                final String[] lines = this.getText().toString().split("\\n");
                if (lines.length == 1 || lines.length < length){
                    //:D
                }else {
                    //
                }
                ss = new SpannableString(this.getText() + " " + TextOpenSeeMore);
            }else{
                ss = new SpannableString(this.getText() + " " + TextOpenSeeMore);
            }
            this.setText(Event(ss));
            this.setMovementMethod(LinkMovementMethod.getInstance());
        }else {

        }
    }

    //This Functions Coming Soon :D
    private SpannableString Event(SpannableString ss){
        int start = ss.length() - TextOpenSeeMore.length();
        String hey = ss.subSequence(start,ss.length()).toString();
        if (hey.equals(TextOpenSeeMore)){
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    handle();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                }
            };
            ss.setSpan(clickableSpan, ss.length() - TextOpenSeeMore.length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        }else{
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    handle();
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                }
            };
            ss.setSpan(clickableSpan, ss.length() - TextOpenSeeMore.length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        }

    }

    private void handle(){
        //:)
    }
}
