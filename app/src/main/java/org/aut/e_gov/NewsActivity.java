package org.aut.e_gov;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.aut.e_gov.models.DatabaseAccess;
import org.aut.e_gov.models.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    //https://www.youtube.com/watch?v=a4o9zFfyIM4
    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList;
    public String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        final DatabaseAccess dbAccess = new DatabaseAccess(this);

        newsList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

//                dbAccess.getDb().insert("Carts", null, cartCv);

        Cursor newsCursor = dbAccess.getDb().rawQuery("SELECT * FROM News", null);
        newsCursor.moveToFirst();
        int[] image = {R.drawable.rohani, R.drawable.vazir, R.drawable.trump ,R.drawable.sekke, R.drawable.metro};//new int[5];
        int i = 0;
        while(!newsCursor.isAfterLast()){
            int id = newsCursor.getInt(0);
            String title = newsCursor.getString(1);
            String shortDesc = newsCursor.getString(2);
            double rating = newsCursor.getDouble(4);
            newsList.add(new News(id,title,shortDesc,"",rating, 0,image[i]));
            i++;
            newsCursor.moveToNext();
        }



        //creating recyclerview adapter
        NewsAdapter adapter = new NewsAdapter(this, newsList,userId);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
