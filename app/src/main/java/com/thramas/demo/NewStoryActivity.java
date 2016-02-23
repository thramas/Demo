package com.thramas.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class NewStoryActivity extends Activity {


    private TextView follow;
    private int flag;
    ArrayList<JSONObject> stories = new ArrayList<>();
    ArrayList<JSONObject> authorList = new ArrayList<>();
    private int position;
    private HashMap<String, Boolean> authorMap;
    private String file;
    private JSONArray mArray;
    private ImageView backbutton;
    private TextView description;
    private TextView articleTitle;
    private ImageView storyImage;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);

        getData();

        follow = (TextView)findViewById(R.id.follow_author);
        backbutton = (ImageView)findViewById(R.id.back_button);
        description = (TextView)findViewById(R.id.article_desription);
        articleTitle = (TextView)findViewById(R.id.article_title);
        storyImage = (ImageView)findViewById(R.id.story_image);
        ImageView authorDp = (ImageView) findViewById(R.id.author_dp);
        TextView authorNameSmall = (TextView) findViewById(R.id.author_name_small);
        TextView visit = (TextView)findViewById(R.id.open_web);
        String imgUrl = "";
        String authorImg = "";
        url = "";
        try {
            description.setText(stories.get(position).getString("description"));
            articleTitle.setText(stories.get(position).getString("title"));
            imgUrl = stories.get(position).getString("si");
            if(stories.get(position).getString("db").equals(authorList.get(0).getString("id"))){
                authorImg = authorList.get(0).getString("image");
                authorNameSmall.setText(authorList.get(0).getString("username"));
                url = stories.get(position).getString("url");
            } else {
                authorImg = authorList.get(1).getString("image");
                authorNameSmall.setText(authorList.get(1).getString("username"));
                url = stories.get(position).getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewStoryActivity.this,WebViewActivity.class);
                i.putExtra("url",url);
                startActivity(i);
            }
        });
        Picasso.with(this)
                .load(imgUrl)
                .placeholder(R.drawable.profile)
                .into(storyImage);

        if(!authorImg.equals("")){
        Picasso.with(this)
                .load(authorImg)
                .placeholder(R.drawable.profile)
                .into(authorDp);
        }

        if(description.getText().toString().equals("")) {
            description.setText("Content");
        }
        setFollowText();
        final String getAuthorIndex = getIdOfAuthor();
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("authorMap",authorMap);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                overridePendingTransition(R.anim.stillanim, R.anim.slidedown);
            }
        });
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(follow.getText().toString().equals("FOLLOW")){
                    follow.setText("UNFOLLOW");
                    setFlags(0,getAuthorIndex);
                } else {
                    follow.setText("FOLLOW");
                    setFlags(1,getAuthorIndex);
                }
                Intent returnIntent = new Intent();
                returnIntent.putExtra("authorMap",authorMap);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                overridePendingTransition(R.anim.stillanim, R.anim.slidedown);
            }
        });
    }

    private String getIdOfAuthor() {
        for(int i = 0;i < authorList.size();i++) {
            try {
                if(authorList.get(i).getString("id").equals(stories.get(position).getString("db"))) {
                    return authorList.get(i).getString("id");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void setFlags(int i,String authorId) {
        if(i == 0){
            authorMap.remove(authorId);
            authorMap.put(authorId,true);
        } else {
            authorMap.remove(authorId);
            authorMap.put(authorId,false);
        }
    }

    private void setFollowText() {
        try {
            if(stories.get(position).get("db").toString().equals(authorList.get(0).get("id").toString())) {
                if(authorMap.get(authorList.get(0).getString("id"))) {
                    follow.setText("UNFOLLOW");
                }  else {
                    follow.setText("FOLLOW");
                }
            } else {
                if(authorMap.get(authorList.get(1).getString("id"))) {
                    follow.setText("UNFOLLOW");
                }  else {
                    follow.setText("FOLLOW");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getData() {
        Intent i = getIntent();
        position = i.getIntExtra("position",0);
        file = i.getStringExtra("file");
        try {
            mArray = new JSONArray(file);
            authorList.add(mArray.getJSONObject(0));
            authorList.add(mArray.getJSONObject(1));
            for(int p = 2;p < mArray.length();p++) {
                JSONObject story = mArray.getJSONObject(p);
                stories.add(story);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        authorMap = (HashMap<String, Boolean>) i.getSerializableExtra("authorMap");
    }
}
