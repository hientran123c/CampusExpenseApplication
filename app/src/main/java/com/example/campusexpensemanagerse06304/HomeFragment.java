package com.example.campusexpensemanagerse06304;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.campusexpensemanagerse06304.adapter.ProductListAdapter;
import com.example.campusexpensemanagerse06304.model.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {

    ListView listView;
    SearchView searchView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.lvProductList);
        searchView = view.findViewById(R.id.searchView);
        //
        List<Products> products = new ArrayList<>();
        products.add(new Products(1, "iphone 16 256G pro max ","https://cdn.tgdd.vn/Products/Images/42/329149/iphone-16-pro-max-sa-mac-thumb-600x600.jpg", 32900000));
        products.add(new Products(2, "iphone 16 128G pro max","https://cdn.tgdd.vn/Products/Images/42/329143/iphone-16-pro-titan-tu-nhien.png", 27900000));
        products.add(new Products(3, "iphone 16 Plus","https://cdn.tgdd.vn/Products/Images/42/329138/iphone-16-plus-hong-thumb-600x600.jpg", 24900000));
        products.add(new Products(4, "iphone 16 128G ","https://cdn.tgdd.vn/Products/Images/42/329135/iphone-16-blue-600x600.png", 21900000));
        products.add(new Products(5, "iphone 16 Pro Max 512G ","https://cdn.tgdd.vn/Products/Images/42/329150/iphone-16-pro-max-sa-mac-thumb-600x600.jpg", 35900000));

        products.add(new Products(6, "iphone 16 256G pro max ","https://cdn.tgdd.vn/Products/Images/42/329149/iphone-16-pro-max-sa-mac-thumb-600x600.jpg", 32900000));
        products.add(new Products(7, "iphone 16 128G pro max","https://cdn.tgdd.vn/Products/Images/42/329143/iphone-16-pro-titan-tu-nhien.png", 27900000));
        products.add(new Products(8, "iphone 16 Plus","https://cdn.tgdd.vn/Products/Images/42/329138/iphone-16-plus-hong-thumb-600x600.jpg", 24900000));
        products.add(new Products(9, "iphone 16 128G ","https://cdn.tgdd.vn/Products/Images/42/329135/iphone-16-blue-600x600.png", 21900000));
        products.add(new Products(10, "iphone 16 Pro Max 512G ","https://cdn.tgdd.vn/Products/Images/42/329150/iphone-16-pro-max-sa-mac-thumb-600x600.jpg", 35900000));

        products.add(new Products(11, "iphone 16 256G pro max ","https://cdn.tgdd.vn/Products/Images/42/329149/iphone-16-pro-max-sa-mac-thumb-600x600.jpg", 32900000));
        products.add(new Products(12, "iphone 16 128G pro max","https://cdn.tgdd.vn/Products/Images/42/329143/iphone-16-pro-titan-tu-nhien.png", 27900000));
        products.add(new Products(13, "iphone 16 Plus","https://cdn.tgdd.vn/Products/Images/42/329138/iphone-16-plus-hong-thumb-600x600.jpg", 24900000));
        products.add(new Products(14, "iphone 16 128G ","https://cdn.tgdd.vn/Products/Images/42/329135/iphone-16-blue-600x600.png", 21900000));
        products.add(new Products(15, "iphone 16 Pro Max 512G ","https://cdn.tgdd.vn/Products/Images/42/329150/iphone-16-pro-max-sa-mac-thumb-600x600.jpg", 35900000));

        ProductListAdapter pdAdapter = new ProductListAdapter(getActivity(), products);
        listView.setAdapter(pdAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Products pd = (Products) listView.getItemAtPosition(position);
                String name = pd.get_name();
                int idPd = pd.get_id();
                double price = pd.get_price();
                String mess = idPd + " - " + name + " - " + price;
                Toast.makeText(getActivity(), mess, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}