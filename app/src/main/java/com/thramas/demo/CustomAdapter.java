package com.thramas.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Samarth Khare on 22-Feb-16.
 */
public class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.ContactViewHolder> implements View.OnClickListener{

    private List<StoryObject> contactList;
    private Context context;

    private int clicked = 0;
    private Context imageContext;

    public CustomAdapter(List<StoryObject> contactList,Context context) {
        this.contactList = contactList;
        this.context = context;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.activity_card, viewGroup, false);

        imageContext = viewGroup.getContext();
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        StoryObject ci = contactList.get(i);
        contactViewHolder.authorName.setText(ci.author);
        contactViewHolder.verbName.setText(ci.verb);
        contactViewHolder.title.setText(ci.title);
        if(ci.isFollowing.equals("true")) {
            contactViewHolder.followBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.follow));
        } else {
            contactViewHolder.followBtn.setImageDrawable(context.getResources().getDrawable(R.drawable.unfollow));
        }

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
        protected CircleImageView authorImage;

        public ContactViewHolder(View v) {
            super(v);
            authorName =  (TextView) v.findViewById(R.id.author_name);
            verbName = (TextView)  v.findViewById(R.id.verb_name);
            title = (TextView)  v.findViewById(R.id.title);
            image = (ImageView) v.findViewById(R.id.card_image);
            followBtn = (ImageView) v.findViewById(R.id.follow_button);
            authorImage = (CircleImageView)v.findViewById(R.id.author_image);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            position = pos;
        }
    }
}
