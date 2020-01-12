package com.zql.lib_main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zql.base.utils.StringUtil;
import com.zql.comm.UserSeting;
import com.zql.comm.bean.Means;
import com.zql.comm.bean.NoteBean;
import com.zql.comm.route.RouteUrl;
import com.zql.lib_main.R;
import com.zql.lib_main.view.MainPresenter;


import java.util.List;

/**
 * Created by 尽途 on 2018/4/8.
 */

public class ViewPagerCardAdapter extends PagerAdapter {
    private View currentView;
    public static ImageView menu_item_viewpagercard;
    private ImageView imageview_item_viewpagercard, lableview_item_viewpagercard;
    private TextView textview_item_viewpagercard, createtime_item_viewpagercard;
    private CardView viewpager_card;
    private List<NoteBean>list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int mChildCount = 0;
    private MainPresenter mainActivityImp;

    public ViewPagerCardAdapter(Context mcontext, List<NoteBean>mlist, MainPresenter mmainActivityImp){
        this.context=mcontext;
        this.list=mlist;
        this.mainActivityImp=mmainActivityImp;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentView=(View)object;
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String type = list.get(position).getNotetype();
        String noteinfo = StringUtil.getPureString(list.get(position).getNoteinfo());
        String photopath = list.get(position).getPhotopath();
        String createtime = list.get(position).getCreatetime();
        View view = layoutInflater.inflate(R.layout.item_viewpagercard,container,false);
        imageview_item_viewpagercard = view.findViewById(R.id.imageview_item_viewpagercard);
        lableview_item_viewpagercard = view.findViewById(R.id.lableview_item_viewpagercard);
        menu_item_viewpagercard = view.findViewById(R.id.menu_item_viewpagercard);
        textview_item_viewpagercard = view.findViewById(R.id.textview_item_viewpagercard);
        createtime_item_viewpagercard = view.findViewById(R.id.createtime_item_viewpagercard);
        viewpager_card = view.findViewById(R.id.viewPager_card);
        setMenuListener(menu_item_viewpagercard,list.get(position));
        startNoteinfoActivity(viewpager_card,list.get(position));
        textview_item_viewpagercard.setText( "\t\t\t\t" + Means.getNotetextOnViewPagerCard(noteinfo));
        createtime_item_viewpagercard.setText("——创建于:"+createtime);
        if (photopath.equals("<图片>")||photopath.equals("null")){
            imageview_item_viewpagercard.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }else {
            imageview_item_viewpagercard.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        readNoteonMainbyNotetype(lableview_item_viewpagercard);
        /**
         * 早期开发留下的弊端，必须满足老版本，所以添加photopath.equals("<图片>")||photopath.equals("null")两种状态判断条件
         */
        switch (type){
            case "生活":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_live);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_live);
                }else {
                    Glide.with(UserSeting.getApplication()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "工作":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_work);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_work);
                }else {
                    Glide.with(UserSeting.getApplication()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "日记":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_diary);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_dilary);
                }else {
                    Glide.with(UserSeting.getApplication()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "学习":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_study);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_study);
                }else {
                    Glide.with(UserSeting.getApplication()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            case "旅行":
                lableview_item_viewpagercard.setImageResource(R.drawable.icon_travel);
                if (photopath.equals("<图片>")||photopath.equals("null")){
                    imageview_item_viewpagercard.setImageResource(R.drawable.photo_travel);
                }else {
                    Glide.with(UserSeting.getApplication()).load(photopath).into(imageview_item_viewpagercard);
                }
                break;
            default:
                break;
        }
        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    private void setMenuListener(View view, final NoteBean noteBean){//对相应的按钮进行监听
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityImp.openSheetDialog(noteBean);
            }
        });
    }
    private void readNoteonMainbyNotetype(View view){//通过标签来浏览信息
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTypeDilog();
            }
        });
    }

    private void openTypeDilog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View centerview=layoutInflater.inflate(R.layout.dialog_main_type,null);
        final AlertDialog alertDialog=builder.setView(centerview).create();
        final LinearLayout add_dialog_typr_all,
                add_dialog_type_work,
                add_dialog_type_study,
                add_dialog_type_live,
                add_dialog_type_diary,
                add_dialog_type_travel;
        add_dialog_typr_all = centerview.findViewById(R.id.main_dialog_typenote_all);
        add_dialog_type_work = centerview.findViewById(R.id.main_dialog_typenote_work);
        add_dialog_type_diary = centerview.findViewById(R.id.main_dialog_typenote_diary);
        add_dialog_type_live = centerview.findViewById(R.id.main_dialog_typenote_live);
        add_dialog_type_study = centerview.findViewById(R.id.main_dialog_typenote_study);
        add_dialog_type_travel = centerview.findViewById(R.id.main_dialog_typenote_travel);
        add_dialog_typr_all.setOnClickListener(view -> {
            mainActivityImp.readNotefromDtabyType(0);
            alertDialog.dismiss();
        });
        add_dialog_type_work.setOnClickListener(view -> {
            mainActivityImp.readNotefromDtabyType(1);//调用接口实现标签加载
            alertDialog.dismiss();
        });
        add_dialog_type_study.setOnClickListener(view -> {
            mainActivityImp.readNotefromDtabyType(2);//调用接口实现标签加载
            alertDialog.dismiss();
        });
        add_dialog_type_live.setOnClickListener(view -> {
            mainActivityImp.readNotefromDtabyType(3);//调用接口实现标签加载
            alertDialog.dismiss();
        });
        add_dialog_type_diary.setOnClickListener(view -> {
            mainActivityImp.readNotefromDtabyType(4);//调用接口实现标签加载
            alertDialog.dismiss();
        });

        add_dialog_type_travel.setOnClickListener(view -> {
            mainActivityImp.readNotefromDtabyType(5);//调用接口实现标签加载
            alertDialog.dismiss();
        });
        alertDialog.show();
    }
    private void startNoteinfoActivity(View view,final NoteBean noteBean){
        view.setOnClickListener(view1 -> {
            Bundle bundle=new Bundle();
            bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
            ARouter.getInstance().build(RouteUrl.Url_NoteinfoActivity).withBundle("info",bundle).navigation();
        });
    }
    public View getPrimaryItem(){
        return currentView;
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
