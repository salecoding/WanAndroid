package com.lw.wanandroid.ui.hotsearch;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lw.wanandroid.R;
import com.lw.wanandroid.db.HistoryModel;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by lw on 2018/2/2.
 */

public class HistoryAdapter extends TagAdapter<HistoryModel> {
    private Context mContext;
    private LayoutInflater mInflater;

    public HistoryAdapter(Context context, List<HistoryModel> datas) {
        super(datas);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getView(FlowLayout parent, int position, HistoryModel model) {
        View view = mInflater.inflate(R.layout.item_history, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        int parseColor = 0;
        try {
            tvTitle.setText(model.getName());
            String str = Integer.toHexString((int) (Math.random() * 16777215));
            parseColor = Color.parseColor("#".concat(str));
            tvTitle.setTextColor(parseColor);
        } catch (Exception e) {
            e.printStackTrace();
            parseColor = mContext.getResources().getColor(R.color.colorAccent);
        }
        tvTitle.setTextColor(parseColor);
        return view;
    }
}
