package com.example.parseagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvUser;
        private TextView tvCaption;
        private ImageView ivImage;
        private ImageButton ibLike;
        private ImageButton ivComment;
        private ImageButton ivDirect;
        private CircleImageView ivProfilePic;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUsername);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            ivImage = itemView.findViewById(R.id.ivPost);
            ibLike = itemView.findViewById(R.id.ivLike);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivDirect = itemView.findViewById(R.id.ivDirect);
            tvName = itemView.findViewById(R.id.tvName);
            ivProfilePic = itemView.findViewById(R.id.ivProfile);
        }

        public void bind(Post post){
            tvUser.setText(post.getUser().getUsername());
            tvName.setText(post.getUser().getUsername());
            tvCaption.setText(post.getDescription());
            ParseFile profile = post.getUser().getParseFile("profilePic");
            if(profile != null){
                Glide.with(context)
                        .load(profile.getUrl())
                        .circleCrop()
                        .fitCenter()
                        .into(ivProfilePic);
            }
            ParseFile image = post.getImage();
            if(image != null) {
                Glide.with(context)
                        .load(image.getUrl())
                        .into(ivImage);
            }
            if(post.getLikeStatus() == false){
                ibLike.setBackgroundResource(R.drawable.ufi_heart);
            }
            ibLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ibLike.setBackgroundResource(R.drawable.ufi_heart_icon);
                    post.setLike();
                    Log.i("OnClickIB", "clicked");
                }
            });
            ivComment.setBackgroundResource(R.drawable.ufi_comment);
            ivDirect.setBackgroundResource(R.drawable.direct);
        }
    }
}