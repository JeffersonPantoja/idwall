package co.idwall.iddog.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import co.idwall.iddog.R;
import co.idwall.iddog.ui.listener.OnItemClickListener;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class ListaFeedAdapter extends RecyclerView.Adapter<ListaFeedAdapter.DogViewHolder>{

    private final List<String> urlsDog;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListaFeedAdapter(Context context, List<String> urlsDog){
        this.context = context;
        this.urlsDog = urlsDog;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dogfund, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        String urlDog = urlsDog.get(position);
        holder.vincula(urlDog);
    }

    @Override
    public int getItemCount() {
        return urlsDog.size();
    }


    class DogViewHolder extends RecyclerView.ViewHolder {

        private final ImageView avatar;
        private String urlDog;

        public DogViewHolder(final View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.item_dogfun);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(urlDog);
                }
            });
        }

        public void vincula(final String urlDog){
            this.urlDog = urlDog;
            Picasso.get().load(urlDog).into(avatar);
        }

    }
}
