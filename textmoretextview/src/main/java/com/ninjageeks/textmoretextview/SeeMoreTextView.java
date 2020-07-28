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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    SpannableString ss;
     TextView hee;
     Context context;
    public SeeMoreTextView(Context context) {
        this(context, null);
    }

    public SeeMoreTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
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

            if (mode == 0){
                hee = this;
                this.post(new Runnable() {
                    @Override
                    public void run() {
                        int lineCount = hee.getLineCount();
                        if (lineCount == 1 || lineCount < length){
                            Handle(false);
                        }else {
                            Handle(true);
                           // ss = new SpannableString(this.getText() + " " + TextOpenSeeMore);
                           // this.setText(Event(ss));
                          //  this.setMovementMethod(LinkMovementMethod.getInstance());
                        }
                    }
                });

            }else{
                int lengthget = this.getText().toString().length();
                if (lengthget <= length){
                    ss = new SpannableString(this.getText());
                    this.setText(ss);
                }else{
                    //ss = new SpannableString(this.getText().subSequence(start, end) + " " + TextOpenSeeMore);
                    //this.setText(Event(ss));
                    //this.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
        }else {

        }
    }
    String full = "";
    public void Handle(Boolean state){
        if (state == false){
            ss = new SpannableString(this.getText());
            this.setText(ss);
        }else{
            String last = "";
            List<CharSequence> lines = new ArrayList<>();
            //
            full = this.getText().toString();
            int count = hee.getLineCount();
            int men = length -1;
            for (int line = 0; line < length; line++) {
                int start = hee.getLayout().getLineStart(line);
                int end = hee.getLayout().getLineEnd(line);
                CharSequence substring = hee.getText().subSequence(start, end);
                last = last + substring;
                if (line == men){
                    last = last + " " + TextOpenSeeMore;
                }
            }

            this.setText(Event(last));
            this.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }


    //This Functions Coming Soon :D
    private SpannableString Event(String ss1){
        SpannableString ss = SpannableString.valueOf(ss1);
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
        this.setText(full + " " + "کوچک کردن");
    }
}
