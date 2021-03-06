package www.foxcoders.com.photoclubbingme.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import www.foxcoders.com.photoclubbingme.ListSpacingDecoration;
import www.foxcoders.com.photoclubbingme.R;
import www.foxcoders.com.photoclubbingme.Util;
import www.foxcoders.com.photoclubbingme.adapter.FolderAdapter;
import www.foxcoders.com.photoclubbingme.adapter.ThumbnailAdapter;

public class FolderActivity extends AppCompatActivity implements View.OnClickListener, FolderAdapter.ItemClickListener,ThumbnailAdapter.ItemClickListener {

    private Toolbar toolbar;
    private ImageView  imageView,imageView1,imageView2,imageView3;
    Spinner spinner;
    ThumbnailAdapter thumbnailAdapter;
    private SharedPreferences sharedPref;
    private boolean isLoggedIn=false;
    View bottomBottom;
    ImageView adv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        spinner=(Spinner)findViewById(R.id.spnDate);


        sharedPref = this.getSharedPreferences("MyData",Context.MODE_PRIVATE);
        isLoggedIn=sharedPref.getBoolean("isLoggedIn",false);
        adv=(ImageView)findViewById(R.id.adv);
        bottomBottom=findViewById(R.id.nav);

        if(isLoggedIn)
        {
            adv.setVisibility(View.GONE);
        }
        else {
            bottomBottom.setVisibility(View.GONE);
        }


        String[] dateList={"Date","2 Jan, 2017","31 April, 2017","23 March, 1993"};
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] data2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "2", "3", "4", "5", "6", "7", "8", "9", "2", "3", "4", "5", "6", "7", "8", "9"};
        RecyclerView folder_rv = (RecyclerView) findViewById(R.id.folder_rv);
        RecyclerView thumb_rv = (RecyclerView) findViewById(R.id.thumb_rv);

        toolbar=(Toolbar)findViewById(R.id.folderToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Util.setTitleText("View",this);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spn_text,dateList);

        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        FolderAdapter folderAdapter=new FolderAdapter(this,data);
        folder_rv.setLayoutManager(linearLayoutManager);
        folder_rv.addItemDecoration(new ListSpacingDecoration(this,R.dimen.decor));
        folder_rv.setAdapter(folderAdapter);

        final GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        thumbnailAdapter=new ThumbnailAdapter(this,data2);
        thumb_rv.addItemDecoration(new ListSpacingDecoration(10));
        thumb_rv.setLayoutManager(gridLayoutManager);
        thumb_rv.hasFixedSize();

        thumb_rv.setAdapter(thumbnailAdapter);

        imageView=(ImageView)findViewById(R.id.iv1);
        imageView1=(ImageView)findViewById(R.id.iv2);
        imageView2=(ImageView)findViewById(R.id.iv3);
        imageView3=(ImageView)findViewById(R.id.iv4);

        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);


        spinner.setAdapter(adapter);



    }
    @Override
    protected void onResume() {
        thumbnailAdapter.is_in_action_mode=false;
        thumbnailAdapter.notifyDataSetChanged();
        toolbar.getMenu().clear();
        super.onResume();
    }
    public void goToSend()
    {
        startActivity(new Intent(this,SendInfoActivity.class));
    }

    @Override
    public void onClick(View v) {
        goToSend();
    }

    @Override
    public void onFolderClick(View view, int position) {

    }

    @Override
    public void onThumbnailClick(View view, int position) {
        startActivity(new Intent(this,ShowCaseActivity.class));
    }

    @Override
    public void onThumbnailLongClick(View view, int position) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.multi_selected_menu);
        thumbnailAdapter.is_in_action_mode=true;
        thumbnailAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.slide)
        {
            startActivity(new Intent(this,SlideShowActivity.class));
        }
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return true;
    }


}
