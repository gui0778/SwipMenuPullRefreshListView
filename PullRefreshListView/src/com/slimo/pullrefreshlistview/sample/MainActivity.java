package com.slimo.pullrefreshlistview.sample;

import java.util.ArrayList;
import java.util.List;

import com.slimo.pullrefreshlistview.R;
import com.slimo.pullrefreshlistview.R.id;
import com.slimo.pullrefreshlistview.R.layout;
import com.slimo.pullrefreshlistview.R.menu;
import com.slimo.pullrefreshlistview.SwipeMenu;
import com.slimo.pullrefreshlistview.SwipeMenuCreator;
import com.slimo.pullrefreshlistview.SwipeMenuItem;
import com.slimo.pullrefreshlistview.SwipeMenuPullRefreshListView;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	protected static final String TAG = MainActivity.class.getSimpleName();

	private SwipeMenuPullRefreshListView listview;
	
	ArrayAdapter<String> mAdapter;
	private int mStartIndex=0;
	List<String> mData=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(SwipeMenuPullRefreshListView)findViewById(R.id.listview);
		addSwipMenu();
		listview.setOnRefreshListener(new SwipeMenuPullRefreshListView. OnRefreshListener() {

			@Override
			public void onRefresh() {
				Log.e(TAG, "setOnRefreshListener");
				listview.postDelayed(new Runnable() {

					
					@Override
					public void run() {
						refresView();
						listview.onRefreshComplete();
					}
				}, 2000);
			}
		});
		initView();
	}

	private void addSwipMenu()
	{
		SwipeMenuCreator creator = new SwipeMenuCreator() {

		    @Override
		    public void create(SwipeMenu menu) {
		        // create "open" item
		    	SwipeMenuItem openItem = new SwipeMenuItem(
		                getApplicationContext());
		        // set item background
//		        openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//		                0xCE)));
		        // set item width
		        openItem.setWidth(dp2px(90));
		        // set item title
		       // openItem.setTitle("朗读");
		        // set item title fontsize
		        //openItem.setTitleSize(18);
		        // set item title font color
		        openItem.setIcon(R.drawable.ic_launcher);
		        // add to menu
		        menu.addMenuItem(openItem);

		        // create "delete" item
		        SwipeMenuItem deleteItem = new SwipeMenuItem(
		                getApplicationContext());
		        // set item background
		        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
		                0x3F, 0x25)));
		        // set item width
		        deleteItem.setWidth(dp2px(90));
		        // set a icon
		        deleteItem.setIcon(R.drawable.ic_launcher);
		        // add to menu
		        menu.addMenuItem(deleteItem);
		    }
		};
		listview.setMenuCreator(creator);
		
	}
	public   int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				this.getResources().getDisplayMetrics());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
private void initView()
{
	mAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
	listview.setAdapter(mAdapter);
	listview.setOnMenuItemClickListener(new SwipeMenuPullRefreshListView.OnMenuItemClickListener() {
		
		@Override
		public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
			// TODO Auto-generated method stub
			Log.e(TAG, "index:"+index);
			return false;
		}
	});
	refresView();
}
private void refresView()
{
	for(int i=0;i<5;i++)
	{
		mData.add("test:"+mStartIndex);
		mStartIndex++;
	}
	mAdapter.notifyDataSetChanged();
}

}
