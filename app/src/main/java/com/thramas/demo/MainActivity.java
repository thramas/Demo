package com.thramas.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends Activity{

    String file = "[{\n" +
            "    \"about\": \"Mother, actor, entrepreneur, fitness enthusiast and an eternal positive thinker\",\n" +
            "    \"id\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"username\": \"Shilpa shetty kundra \",\n" +
            "    \"followers\": 35215,\n" +
            "    \"following\": 5,\n" +
            "    \"image\": \"http://img.ropose.com/userImages/13632253306661657730401415143533525274225757_circle.png\",\n" +
            "    \"url\": \"http://www.roposo.com/profile/shilpa-shetty-kundra-/238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"handle\": \"@shilpashettykundra\",\n" +
            "    \"is_following\": false,\n" +
            "    \"createdOn\": 1439530320545\n" +
            "}, {\n" +
            "    \"about\": \"Www.Nargisfakhri.com Instagram- @NargisFakhri Twitter- @NargisFakhri FaceBook- Nargis Fakhri \",\n" +
            "    \"id\": \"cda99c24-955a-4c58-a6a8-c811938df530\",\n" +
            "    \"username\": \"Nargis Fakhri\",\n" +
            "    \"followers\": 83878,\n" +
            "    \"following\": 50,\n" +
            "    \"image\": \"http://img5.ropose.com/userImages/16557067377322653404501466127117042162245336_circle_100x100.png\",\n" +
            "    \"url\": \"http://www.roposo.com/profile/nargis-fakhri/cda99c24-955a-4c58-a6a8-c811938df530\",\n" +
            "    \"handle\": \"@nargisfakhri\",\n" +
            "    \"is_following\": false,\n" +
            "    \"createdOn\": 1446559001561\n" +
            "}, {\n" +
            "    \"description\": \"Celebrating Black Friday in this lovely black cut out romper and floral accessories. I love black and i think it is the easiest thing to wear when i am in doubt. So a black romper solves my dilemma of what to wear when i am short of time to decide and outfit. When you wear black add some fun accessories to keep the outfit fun and lively.\",\n" +
            "    \"id\": \"fa6d9bdf-eae3-4d6c-a668-9be94cfaf980\",\n" +
            "    \"verb\": \"created this story on 25 October\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/throwback-/fa6d9bdf-eae3-4d6c-a668-9be94cfaf980\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1445748867804_800_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Throwback \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 1099,\n" +
            "    \"comment_count\": 10\n" +
            "}, {\n" +
            "    \"description\": \"Today's outfit! #ootd Sheer shirts are always so sexy. Team it up with a bustier inside and it looks elegant and sexy at the same time. I wore my purple sheer shirt with an embellished maxi skirt and statement necklace. Sheer shirts looks fabulous with statement necklaces or layered chains.\",\n" +
            "    \"id\": \"066f2bac-1fa8-44f8-b2b6-780df3324c71\",\n" +
            "    \"verb\": \"created this story on 21 October\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/red-riding-hood-/066f2bac-1fa8-44f8-b2b6-780df3324c71\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1445413834463_443_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Red riding hood \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 896,\n" +
            "    \"comment_count\": 19\n" +
            "}, {\n" +
            "    \"description\": \"\",\n" +
            "    \"id\": \"1a799ff8-89c8-41e6-a73e-0be387b40496\",\n" +
            "    \"verb\": \"created on 21 January\",\n" +
            "    \"db\": \"cda99c24-955a-4c58-a6a8-c811938df530\",\n" +
            "    \"url\": \"http://www.roposo.com/story/love-this-outfit-by-anita-dongre/1a799ff8-89c8-41e6-a73e-0be387b40496\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1453385755839_945_cda99c24-955a-4c58-a6a8-c811938df530.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Love this outfit by Anita dongre\",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 234,\n" +
            "    \"comment_count\": 5\n" +
            "}, {\n" +
            "    \"description\": \"DVF dress , Patric Kaiser bag ands Reiss shoes\",\n" +
            "    \"id\": \"37d334b0-c35a-4d6a-96bc-bacaae6e3c79\",\n" +
            "    \"verb\": \"created this story 1 day ago\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/black-/37d334b0-c35a-4d6a-96bc-bacaae6e3c79\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1449202499818_285_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"So roposo \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 2280,\n" +
            "    \"comment_count\": 38\n" +
            "}, {\n" +
            "    \"description\": \"\",\n" +
            "    \"id\": \"74598462-abcc-4170-92ea-901b32dcf433\",\n" +
            "    \"verb\": \"updated this story on 7 November\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/so-roposo-/74598462-abcc-4170-92ea-901b32dcf433\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1446695866824_645_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Happy Karvachauth !! \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 858,\n" +
            "    \"comment_count\": 9\n" +
            "}, {\n" +
            "    \"description\": \"The Karvachauth look :-) \",\n" +
            "    \"id\": \"386b11c2-24bb-40ec-958f-788b2e743db3\",\n" +
            "    \"verb\": \"created this story on 30 October\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/happy-karvachauth-/386b11c2-24bb-40ec-958f-788b2e743db3\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1446203590488_320_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Happy Karvachauth !! \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 1322,\n" +
            "    \"comment_count\": 58\n" +
            "}, {\n" +
            "    \"description\": \"At Amitji's Diwali party wearing a lovely #manishmalhotra and jewellery by #anmol \",\n" +
            "    \"id\": \"ae30f007-9922-47c0-8fab-934b61d8cc96\",\n" +
            "    \"verb\": \"created this story on 14 November\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/diwali-look-/ae30f007-9922-47c0-8fab-934b61d8cc96\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1447479080598_229_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Diwali Look! :D\",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 3267,\n" +
            "    \"comment_count\": 49\n" +
            "}, {\n" +
            "    \"description\": \"\",\n" +
            "    \"id\": \"c6f4d22c-fec8-4161-8c89-80c3e7956aaf\",\n" +
            "    \"verb\": \"created this story on 14 November\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/at-jagdamba-saree-25yrs-celebration-in-surat-/c6f4d22c-fec8-4161-8c89-80c3e7956aaf\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1446441786706_684_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"At Jagdamba saree 25yrs celebration in surat \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 1404,\n" +
            "    \"comment_count\": 33\n" +
            "}, {\n" +
            "    \"description\": \"Wearing a stunning #zulekhashariff styled by #sanjanabatra \",\n" +
            "    \"id\": \"1bc4d79a-1dc9-46fb-ba1c-4f88a25c55e2\",\n" +
            "    \"verb\": \"created this story on 1 November\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/at-the-sony-event-last-night-/1bc4d79a-1dc9-46fb-ba1c-4f88a25c55e2\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1446355020803_843_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"At the sony event last night \",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 958,\n" +
            "    \"comment_count\": 22\n" +
            "}, {\n" +
            "    \"description\": \"\",\n" +
            "    \"id\": \"75b460ed-e3a5-4282-bc60-d1537110fb7d\",\n" +
            "    \"verb\": \"created on 20 January\",\n" +
            "    \"db\": \"cda99c24-955a-4c58-a6a8-c811938df530\",\n" +
            "    \"url\": \"http://www.roposo.com/story/love-my-new-nail-color-purple-magic/75b460ed-e3a5-4282-bc60-d1537110fb7d\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1453385755839_945_cda99c24-955a-4c58-a6a8-c811938df530.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Love my new nail color! Purple magic\",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 234,\n" +
            "    \"comment_count\": 5\n" +
            "}, {\n" +
            "    \"description\": \"Customized jacket by #masabagupta and do not miss the SSK bracelet :-) \",\n" +
            "    \"id\": \"1258dbda-b7d4-473b-9e4c-dc6b421ddd1b\",\n" +
            "    \"verb\": \"created this story on 1 November\",\n" +
            "    \"db\": \"238bb4ca-606d-4817-afad-78bee2898264\",\n" +
            "    \"url\": \"http://www.roposo.com/story/ethnic-look/1258dbda-b7d4-473b-9e4c-dc6b421ddd1b\",\n" +
            "    \"si\": \"http://img0.ropose.com/story/1446090876919_374_238bb4ca-606d-4817-afad-78bee2898264.jpeg\",\n" +
            "    \"type\": \"story\",\n" +
            "    \"title\": \"Ethnic look\",\n" +
            "    \"like_flag\": false,\n" +
            "    \"likes_count\": 1266,\n" +
            "    \"comment_count\": 18\n" +
            "}]";

    ArrayList<String> descriptionList = new ArrayList<>();
    ArrayList<String> verbList = new ArrayList<>();
    ArrayList<String> imageList = new ArrayList<>();
    ArrayList<String> authorList = new ArrayList<>();
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> isFollowing = new ArrayList<>();
    ArrayList<String> profileUrl = new ArrayList<>();
    ArrayList<JSONObject> listOfAuthors = new ArrayList<>();
    ArrayList<JSONObject> stories = new ArrayList<>();
    HashMap<String,Boolean> authorMap = new HashMap<>();

    private JSONArray mArray;
    private JSONObject author1;
    private JSONObject author2;
    private RecyclerView recList;
    private CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveFromJSON();
        setRecyclerView();

    }

    private void setRecyclerView() {
        recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        recList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent i = new Intent(MainActivity.this, NewStoryActivity.class);
                        i.putExtra("position", position);
                        i.putExtra("file",file);
                        i.putExtra("authorMap", authorMap);
                        startActivityForResult(i, 0);
                        overridePendingTransition(R.anim.slidebottomtop, R.anim.stillanim);
                    }
                })
        );

        ca = new CustomAdapter(createList(mArray.length()-2),this);
        recList.setAdapter(ca);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                authorMap = (HashMap<String, Boolean>) data.getSerializableExtra("authorMap");
                updateFollowingList();
                ca = new CustomAdapter(createList(mArray.length()-2),this);
                ca.notifyDataSetChanged();
                recList.setAdapter(ca);
            }
        }
    }

    private void updateFollowingList() {
        for (int i = 0; i < isFollowing.size(); i++) {
            try {
                isFollowing.set(i,authorMap.get(stories.get(i).getString("db")).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void retrieveFromJSON() {
        try {
            mArray = new JSONArray(file);
            author1 = (JSONObject) mArray.get(0);
            author2 = (JSONObject) mArray.get(1);
            authorMap.put(author1.getString("id"),author1.getBoolean("is_following"));
            authorMap.put(author2.getString("id"),author2.getBoolean("is_following"));
            listOfAuthors.add(author1);
            listOfAuthors.add(author2);
            for(int i = 2;i < mArray.length();i++) {
                JSONObject story = mArray.getJSONObject(i);
                stories.add(story);
                descriptionList.add(story.getString("description"));
                titleList.add(story.getString("title"));
                if(author1.getString("id").equals(story.getString("db"))){
                    authorList.add(author1.getString("username"));
                    profileUrl.add(author1.getString("image"));
                    if(authorMap.get(author1.getString("id"))) {
                        isFollowing.add("true");
                    } else {
                        isFollowing.add("false");
                    }
                } else {
                    authorList.add(author2.getString("username"));
                    profileUrl.add(author2.getString("image"));
                    if(authorMap.get(author2.getString("id"))) {
                        isFollowing.add("true");
                    } else {
                        isFollowing.add("false");
                    }
                }
                verbList.add(story.getString("verb"));
                imageList.add(story.getString("si"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<StoryObject> createList(int size) {

        List<StoryObject> result = new ArrayList<StoryObject>();
        for (int i=0; i < size; i++) {
            StoryObject ci = new StoryObject();
            ci.author = authorList.get(i);
            ci.verb = verbList.get(i);
            ci.title = titleList.get(i);
            ci.description = descriptionList.get(i);
            ci.imageURL = imageList.get(i);
            ci.isFollowing = isFollowing.get(i);
            ci.profileUrl = profileUrl.get(i);
            result.add(ci);
        }
        return result;
    }
}
