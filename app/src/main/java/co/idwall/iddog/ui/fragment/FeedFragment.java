package co.idwall.iddog.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.idwall.iddog.R;

public class FeedFragment extends Fragment {

    public static final String PAGINA = "ARG_PAGE";

    private String tituloPagina;

    public static FeedFragment newInstance(String pagina) {
        Bundle args = new Bundle();
        args.putString(PAGINA, pagina);
        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tituloPagina = getArguments().getString(PAGINA);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        TextView textView = view.findViewById(R.id.feed_fragment_textview);
        textView.setText("Fragment #" + tituloPagina);
        return view;
    }

}
