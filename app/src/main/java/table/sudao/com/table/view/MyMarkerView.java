package table.sudao.com.table.view;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import table.sudao.com.table.R;

/**
 * Created by pcdalao on 2017/9/16.  点击事件
 */

public class MyMarkerView  extends MarkerView {
    private Context context;
    private TextView textView;
    public MyMarkerView (Context context, int layoutResource) {
        super(context, layoutResource);
        this.context=context;
        textView=findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        Log.d("TAG","X:  "+e.getX()+"Y:   "+e.getY());
        //   Toast.makeText(context,"X:  "+e.getX()+"Y:   "+e.getY(),Toast.LENGTH_LONG).show();
        // textView.setText("X:  "+e.getX()+"Y:   "+e.getY());
        textView.setText("2013年期中考试试卷\n2017.09.09\n第一名 60分");
        super.refreshContent(e, highlight);
    }

    /*
* offset 是以點到的那個點作為 (0,0) 中心然後往右下角畫出來 该方法是让markerview现实到坐标的上方
* 所以如果要顯示在點的上方
* X=寬度的一半，負數
* Y=高度的負數
*/
    //设置布局显示在柱状图之上，默认在左下角
    @Override
    public MPPointF getOffset() {
        // Log.e("ddd", "width:" + (-(getWidth() / 2)) + "height:" + (-getHeight()));
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

}