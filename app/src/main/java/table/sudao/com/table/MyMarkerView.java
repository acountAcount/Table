package table.sudao.com.table;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * Created by pcdalao on 2017/9/16.  点击事件
 */

public class MyMarkerView  extends MarkerView {
    private Context context;

    public MyMarkerView (Context context, int layoutResource) {
        super(context, layoutResource);
        this.context=context;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        Log.d("TAG","X:  "+e.getX()+"Y:   "+e.getY());
     //   Toast.makeText(context,"X:  "+e.getX()+"Y:   "+e.getY(),Toast.LENGTH_LONG).show();

        super.refreshContent(e, highlight);
    }

}