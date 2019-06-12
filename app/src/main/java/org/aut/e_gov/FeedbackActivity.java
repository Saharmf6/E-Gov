package org.aut.e_gov;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.e_gov.models.DatabaseAccess;
import org.aut.e_gov.models.User;

import static org.aut.e_gov.GlobalVariables.balance;
import static org.aut.e_gov.GlobalVariables.password;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    int newsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.edtFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        newsId = getIntent().getIntExtra("newsId", -1);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

            }
        });


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("خیلی بد");
                        break;
                    case 2:
                        mRatingScale.setText("نسبتا بد");
                        break;
                    case 3:
                        mRatingScale.setText("خوب");
                        break;
                    case 4:
                        mRatingScale.setText("خیلی خوب");
                        break;
                    case 5:
                        mRatingScale.setText("بسیار عالی");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });


        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if (mFeedback.getText().toString().isEmpty()) {
                 //   Toast.makeText(FeedbackActivity.this, "لطفا کادر بالا را پر کنید.", Toast.LENGTH_LONG).show();
                //} else {

                    Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM User", null);
                    userCursor.moveToFirst();
                    while (!userCursor.isAfterLast()) {
                        if(GlobalVariables.id.equals(userCursor.getString(0)))
                            break;
                        userCursor.moveToNext();
                    }
                    int rating = (int)mRatingBar.getRating();
                    ContentValues usercv = new ContentValues();
                    usercv.put("id", userCursor.getString(0));
                    usercv.put("password", userCursor.getString(1));
                    usercv.put("name", userCursor.getString(2));
                    usercv.put("balance", userCursor.getDouble(3));
                    usercv.put("phone_number", userCursor.getString(4));
                    String previousRatings = userCursor.getString(5);
                    String newString = "";
                    boolean newsAdded = false;
                    if(previousRatings != null) {
                        String[] parsing = previousRatings.split(",");
                        for (int i = 0; i < parsing.length; i++) {
                            String[] datas = parsing[i].split(":");
                            if (Integer.parseInt(datas[0]) == newsId) {
                                datas[1] = Integer.toString(rating);
                                newsAdded = true;
                            }
                            newString = newString + datas[0] + ':' + datas[1] + ',';
                        }
                        if (newsAdded == false){
                            newString += newsId + ":" + rating + ",";
                        }
                    }
                    else if (previousRatings == null || previousRatings.equals("")) {
                        newString = newsId + ":" + rating + ",";
                    }
                    usercv.put("news_rating", newString);
                    dbAccess.getDb().update("User", usercv, "id = '"+GlobalVariables.id+"'", null);
                    //TODO: change the rating on the news database
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(FeedbackActivity.this, "ممنون از نظر شما", Toast.LENGTH_SHORT).show();
                }
           // }
        });
    }
}
