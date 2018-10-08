package co.idwall.iddog.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.idwall.iddog.R;

public class ListaFeedAdapter extends RecyclerView.Adapter<ListaFeedAdapter.RepositorioViewHolder>{

    private final List<String> urlsDog;
    private final Context context;

    public ListaFeedAdapter(Context context, List<String> urlsDog){
        this.context = context;
        this.urlsDog = urlsDog;
    }

    @Override
    public ListaFeedAdapter.RepositorioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dogfund, parent, false);
        return new RepositorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaFeedAdapter.RepositorioViewHolder holder, int position) {
        String urlDog = urlsDog.get(position);
        holder.vincula(urlDog);
    }

    @Override
    public int getItemCount() {
        return urlsDog.size();
    }


    class RepositorioViewHolder extends RecyclerView.ViewHolder {

        private final ImageView avatar;

        public RepositorioViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.item_dogfun);
        }

        public void vincula(final String urlDog){
            Picasso.get().load(urlDog).into(avatar);
        }

    }
}
