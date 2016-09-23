package com.baseproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.baseproject.R;
import com.baseproject.data.models.PostModel;
import java.util.List;

/**
 * Created by Nishant Shah on 21-Sep-16.
 */
public class PostAdapter extends ArrayAdapter<PostModel> {

	private List<PostModel> posts;
	public PostAdapter(Context ctx, List<PostModel> posts) {
		super(ctx, 0, posts);
		this.posts=posts;
	}

	@Override public PostModel getItem(int position) {
		return posts.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		PostModel post = getItem(position);
		ViewHolder holder;
		if(convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_post_item, parent, false);
			holder=new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.textViewItemTitle);
			convertView.setTag(holder);
		}
		holder= (ViewHolder) convertView.getTag();
		holder.title.setText(post.title);

		return convertView;
	}
	static class ViewHolder{
		 TextView title;
	}
}
