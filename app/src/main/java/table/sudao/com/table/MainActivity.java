package table.sudao.com.table;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LineChart mChart;

    private BarChart barchart;

    //折线图数据
    List<Entry>  pointlint;
   //柱状图数据
    List<BarEntry> pointbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChart = (LineChart) findViewById(R.id.spread_line_chart);
        barchart=(BarChart)findViewById(R.id.chart1);

        pointdata();

        LineChart(pointlint);//折线图

        initBarChart();

    }
    public synchronized void pointdata(){

        pointlint= new ArrayList<>();
        pointbar= new ArrayList<>();
        for (int x = 1; x< 10; x++) {
            int y = (int) (Math.random() * 20);
            pointlint.add(new Entry(x, y));
            pointbar.add(new BarEntry(x, y));
        }
    }

    private void initBarChart() {
        BarDataSet barDataSet = new BarDataSet(pointbar, "小明每月支出");
        barDataSet.setDrawValues(false);//是否显示柱子上面的数值
        barDataSet.setColor(Color.parseColor("#fa8072"));//设置第一组数据颜色
//        barDataSet.setBarSpacePercent(2f);


        ArrayList<IBarDataSet> threebardata = new ArrayList<>();//IBarDataSet 接口很关键，是添加多组数据的关键结构，LineChart也是可以采用对应的接口类，也可以添加多组数据
        threebardata.add(barDataSet);


        BarData bardata = new BarData(threebardata);
        bardata.setBarWidth(0.25f);  //设置柱子宽度

        barchart.setData(bardata);
        barchart.setDoubleTapToZoomEnabled(false);   //双击缩放
        barchart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);//设置注解的位置在左上方
        barchart.getLegend().setForm(Legend.LegendForm.CIRCLE);//这是左边显示小图标的形状

        barchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        barchart.getXAxis().setDrawGridLines(false);//不显示网格


        barchart.getAxisRight().setEnabled(false);//右侧不显示Y轴
        barchart.getAxisLeft().setAxisMinValue(0.8f);//设置Y轴显示最小值，不然0下面会有空隙
        barchart.getAxisLeft().setDrawGridLines(false);//不设置Y轴网格
//        barchart.setDescriptionPosition(100,20);//数据描述的位置
//        barchart.setDescriptionColor(Color.GRAY);//数据的颜色
//        barchart.setDescriptionTextSize(40);//数据字体大小
       // barchart.setDescription("No Deal");//设置描述
      //  barchart.setDescriptionTextSize(20.f);//设置描述字体
//        barchart.getXAxis().setSpaceBetweenLabels(50);
        barchart.getXAxis().setSpaceMax(1);
        barchart.animateXY(1000, 2000);//设置动画

    }


    public void LineChart(List<Entry> pointValues){

        LineDataSet lineDataSet = new LineDataSet(pointValues, "");

        lineDataSet.setColor(Color.parseColor("#40e0d0")); //设置该线的颜色

        lineDataSet.setCircleColor(Color.parseColor("#40e0d0")); //设置节点圆圈颜色

        lineDataSet.setDrawValues(false); //设置是否显示点的坐标值


        lineDataSet.setValueTextColor(Color.parseColor("#5abdfe"));

        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);// 设置平滑曲线

        //线的集合（可单条或多条线）

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        //把要画的所有线(线的集合)添加到LineData里
        LineData lineData = new LineData(dataSets);
//        mChart.getAxisLeft().setDrawGridLines(false);
//        mChart.getAxisRight().setDrawGridLines(false);
       // mChart.setDrawGridBackground(false);
        YAxis leftAxis = mChart.getAxisRight();  //得到图表的左侧Y轴实例

        leftAxis.enableGridDashedLine(10f, 20f, 0f); //设置横向表格为虚线
        leftAxis.setLabelCount(2,false);  //设置y轴显示的数量
        leftAxis.setGridLineWidth(0);
        leftAxis.setEnabled(false);

//        leftAxis.setDrawGridLines(false);   //不显示网格线


        XAxis xAxis = mChart.getXAxis();  //设置X轴
        xAxis.setDrawGridLines(false);  //不显示网格线

        mChart.setDrawGridBackground(false);
        mChart.setDrawGridBackground(false);
        //把最终的数据setData
        mChart.setData(lineData);

    }

}
