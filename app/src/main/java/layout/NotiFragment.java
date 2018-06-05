package layout;

import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.example.pc.bottomnav.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import Adapter.InfoAdapter;
import Model.Food;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NotiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // RECYCLER VIEW
    private RecyclerView recyclerView;
    private InfoAdapter adapter;
    private List<Food> info_list;


    public NotiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment NotiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotiFragment newInstance() {
        NotiFragment fragment = new NotiFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noti, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.info_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        info_list = new ArrayList<>();
        info_list.add(new Food("Số tin đã đăng","", "5"));
        info_list.add(new Food("Số tin đã đọc","", "5"));

        adapter = new InfoAdapter(info_list);
        recyclerView.setAdapter(adapter);
        return view;
    }



}
