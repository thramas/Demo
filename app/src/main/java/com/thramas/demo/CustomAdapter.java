package com.thramas.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Samarth Khare on 22-Feb-16.
 */
public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.ContactViewHolder> implements View.OnClickListener{

    private List<StoryObject> contactList;
    public static Context context;
    private String followFlag;
    private String file;
    private HashMap<String,Boolean> authorMap;
    private int clicked = 0;
    private int position;
    private Context imageContext;
    private RecyclerView.LayoutManager llm;
    private ArrayList<JSONObject> stories = new ArrayList<>();
    private ArrayList<JSONObject> authorList = new ArrayList<>();
    private JSONArray mArray;
    private View.OnClickListener listener;

    public CustomAdapter(List<StoryObject> contactList,Context context,String file, HashMap<String,Boolean> authorMap,RecyclerView.LayoutManager llm, ArrayList<String> isFollowing,View.OnClickListener listener) {
        this.contactList = contactList;
        this.context = context;
        this.authorMap = authorMap;
        this.file = file;
        this.llm = llm;
        this.listener = listener;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.activity_card, viewGroup, false);

        imageContext = viewGroup.getContext();
        return new ContactViewHolder(itemView);
    }

    private void getData() {
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
    }
    @Override
    public void onBindViewHolder(final ContactViewHolder contactViewHolder, int i) {
        StoryObject ci = contactList.get(i);
        contactViewHolder.authorName.setText(ci.author);
        contactViewHolder.verbName.setText(ci.verb);
        contactViewHolder.title.setText(ci.title);
        contactViewHolder.cardView.setTag("unfollowed");
        contactViewHolder.followBtn.setId(i);
        getData();
        if(ci.isFollowing.equals("true")) {
            contactViewHolder.followBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.follow));
            contactViewHolder.followBtn.setTag("followed");
            contactViewHolder.cardView.setTag("followed");
        } else {
            contactViewHolder.followBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.unfollow));
            contactViewHolder.followBtn.setTag("unfollowed");
            contactViewHolder.cardView.setTag("unfollowed");
        }

        position = i;
       contactViewHolder.followBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View vvvvv) {
               final String getAuthorIndex = getIdOfAuthor();
               if (contactViewHolder.followBtn.getTag().equals("unfollowed")) {
                   contactViewHolder.followBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.follow));
                   contactViewHolder.followBtn.setTag("followed");
                   contactViewHolder.cardView.setTag("followed");
                   followFlag = "true";
                   setFlags(0, getAuthorIndex);
               } else {
                   contactViewHolder.followBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.unfollow));
                   contactViewHolder.followBtn.setTag("unfollowed");
                   contactViewHolder.cardView.setTag("unfollowed");
                   followFlag = "false";
                   setFlags(1, getAuthorIndex);
               }
               updateFollowingList();
           }
       });
//
//        contactViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, NewStoryActivity.class);
//                i.putExtra("position", i);
//                i.putExtra("file",file);
//                i.putExtra("authorMap", authorMap);
//                ((Activity)context).startActivityForResult(i, 0);
//                ((Activity)context).overridePendingTransition(R.anim.slidebottomtop, R.anim.stillanim);
//            }
//        });
        clicked = contactViewHolder.position;

        Picasso.with(context)
                .load(ci.imageURL)
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .into(contactViewHolder.image);

        Picasso.with(context)
                .load(ci.profileUrl)
                .placeholder(R.drawable.profile)
                .into(contactViewHolder.authorImage);

        View v1 = new View(context);
        v1.setTag(i);
        listener.onClick(v1);

    }

    private void updateFollowingList() {
        for (int i = 0; i < MainActivity.isFollowing.size(); i++) {
            try {
                if(authorMap.get(stories.get(i).getString("db"))) {
                    MainActivity.isFollowing.set(i, authorMap.get(stories.get(position).getString("db")).toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(context,NewStoryActivity.class);
        i.putExtra("postion",clicked);
        context.startActivity(i);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView authorName;
        protected TextView verbName;
        protected TextView title;
        protected ImageView image;
        protected ImageView followBtn;
        protected int position;
        protected CardView cardView;
        protected CircleImageView authorImage;

        public ContactViewHolder(View v) {
            super(v);
            authorName =  (TextView) v.findViewById(R.id.author_name);
            verbName = (TextView)  v.findViewById(R.id.verb_name);
            title = (TextView)  v.findViewById(R.id.title);
            image = (ImageView) v.findViewById(R.id.card_image);
            followBtn = (ImageView) v.findViewById(R.id.follow_button);
            cardView = (CardView) v.findViewById(R.id.card_view);
            authorImage = (CircleImageView)v.findViewById(R.id.author_image);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            position = pos;
        }
    }
}
