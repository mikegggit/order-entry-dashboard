import { ResponsiveContainer, LineChart, Line, XAxis, YAxis, ReferenceLine,
  ReferenceDot, Tooltip, CartesianGrid, Legend, Brush, ErrorBar, AreaChart, Area,
  Label, LabelList } from 'recharts';
import { scalePow, scaleLog } from 'd3-scale';
import React from 'react';
class TestLineChartWrapper extends React.Component {

  constructor(props) {
    super(props);
    console.log("TestLineChartWrapper::ctor");
  }

  render(props) {

    console.log("TestLineChartWrapper::render");

    return (<div className='line-chart-wrapper'>
      <LineChart width={1100} height={400} data={this.props.latencyStream} >
	<CartesianGrid stroke='#f5f5f5' fill="#e6e6e6" />
	<XAxis dataKey="time" height={40} label="Time"/>
	<YAxis type="number"/>
	<Line
	  type="monotone"
	  dataKey="val"
	  stroke="#ff7300"
	  dot={false}
	>
	</Line>
      </LineChart> 
    </div>);
  }
}

export default TestLineChartWrapper;
