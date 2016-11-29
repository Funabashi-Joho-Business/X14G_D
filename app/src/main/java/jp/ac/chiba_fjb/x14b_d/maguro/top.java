//package jp.ac.chiba_fjb.x14b_d.maguro;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class top extends Fragment {
//
//
//    public top() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
<<<<<<< HEAD
//
//        view view= inflater.inflate(R.layout.fragment_top, container, false);
=======
//        // Inflate the layout for this fragment
//       view view= inflater.inflate(R.layout.fragment_top, container, false);
>>>>>>> 85828e21f44ec8a3dd5b7873f9a99c919a435b63
//        view.findViewById(R.id.editname);
//        view.findViewById(R.id.imagesettei).setOnClickListener(this);
//
//        return view;
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.imagesettei:
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.fullscreen_content, new TitleFragment());
//                ft.commitAllowingStateLoss();
//                break;
//        }
//    }
//}