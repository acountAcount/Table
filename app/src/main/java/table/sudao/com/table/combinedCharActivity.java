package table.sudao.com.table;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcdalao on 2017/9/15.
 */

public class CombinedCharActivity extends AppCompatActivity {
    private CombinedChart combineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);

        combineChart=(CombinedChart)findViewById(R.id.combineChart);

        settingCombineChart(initData());
    }
    /**
     * 设置柱线组合图样式，柱图依赖左侧y轴，线图依赖右侧y轴
     */
    public  ArrayList initData() {
        ArrayList  lineValues= new ArrayList<>();
        for (int x = 10; x< 50; x=x+5) {
            lineValues.add((float)(Math.random() * 20));
        }
        return  lineValues;

    }
    public  ArrayList linkinitData() {
        ArrayList  lineValues= new ArrayList<>();
        for (int x = 10; x< 50; x=x+5) {
            lineValues.add((float)(Math.random() * 20));
        }
        return  lineValues;

    }

    /**
     * 设置表格属性
     * @param lineValues
     */
    public void settingCombineChart(ArrayList lineValues){
        //设置组合图数据
        CombinedData data = new CombinedData();

        data.setData(generateBarData(lineValues, "分数"));
        data.setData(generateLineData(linkinitData(), "排名"));
        combineChart.setData(data);//设置组合图数据源

        //设置表格描述为空
        Description description=new Description();
        description.setText("");
        combineChart.setDescription(description);
        //设置表格描述为空 end

        combineChart.animateX(1500);//数据显示动画，从左往右依次显示

        combineChart.getXAxis().setDrawGridLines(false);//不显示网格

        combineChart.getAxisLeft().setDrawGridLines(false);//不设置Y轴网格

        combineChart.getAxisRight().setEnabled(false);   //隐藏右边 的坐标轴
        combineChart.getAxisLeft().setEnabled(false);//隐藏左边 的坐标轴
        combineChart.getXAxis().setEnabled(false);  //隐藏上方坐标轴

        combineChart.setDoubleTapToZoomEnabled(false);//启用双击缩放

        combineChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);//设置注解的位置在左上方


        MyMarkerView mv = new MyMarkerView(this, R.layout.pop);
        combineChart.setMarkerView(mv);

        YAxis leftAxis = combineChart.getAxisRight();  //得到图表的左侧Y轴实例
        leftAxis.enableGridDashedLine(20f, 20f, 0f); //设置横向表格为虚线
        leftAxis.setLabelCount(5,false);  //设置y轴显示的数量

        XAxis xAxis = combineChart.getXAxis();  //设置X轴
        xAxis.setAxisMinimum(-0.5f); //设置x轴坐标起始值为-0.5
        xAxis.setAxisMaximum(lineValues.size());
        xAxis.setDrawGridLines(false);  //不显示网格线
    }

    /**
     * 生成线图数据
     */
    private static LineData generateLineData(List<Float> lineValues, String lineTitle) {
        ArrayList<Entry> lineEntries = new ArrayList<>();

        for (int i = 0, n = lineValues.size(); i < n; ++i) {
            lineEntries.add(new Entry(i, lineValues.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, lineTitle);

        lineDataSet.setColor(Color.parseColor("#40e0d0")); //设置该线的颜色

        lineDataSet.setCircleColor(Color.parseColor("#40e0d0")); //设置节点圆圈颜色

        lineDataSet.setDrawValues(false); //设置是否显示点的坐标值


       // lineDataSet.setValueTextColor(Color.parseColor("#5abdfe"));

        lineDataSet.setForm(Legend.LegendForm.LINE);//左边显示小图标的形状

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);// 设置平滑曲线

        lineDataSet.setLineWidth(2); //折线粗细

        LineData lineData = new LineData(lineDataSet);
        lineData.setValueTextSize(10f);

        return lineData;
    }

    /**
     * 生成柱图数据
     *
     * @param barValues
     * @return
     */
    private static BarData generateBarData(List<Float> barValues, String barTitle) {

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        for (int i = 0, n = barValues.size(); i < n; ++i) {
            barEntries.add(new BarEntry(i, barValues.get(i)));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, barTitle);
        barDataSet.setColor(Color.parseColor("#fa8072"));//设置第一组数据颜色
        barDataSet.setDrawValues(false); //设置是否显示点的坐标值

        barDataSet.setForm(Legend.LegendForm.SQUARE);   //设置描述标题图标为柱体
        BarData barData = new BarData(barDataSet);

        barData.setBarWidth(0.25f);  //设置柱子宽度

        return barData;
    }
}
