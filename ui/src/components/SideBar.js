import React from 'react';
import { Button } from 'react-bootstrap';
import SummaryTotals from './SummaryTotals';
import OrderFeedPortEventsGrid from './OrderFeedPortEventsGrid';
 
function SideBar(props) {
  const wellStyles = {
    width: 340,
    margin: '0 auto 10px', 
    padding: 0,
    position: 'fixed',
    left: 1100,
    top: 0
  };


  return (
    <div className="well" style={wellStyles}>
      {
      /*
	<Button bsStyle="primary" bsSize="large" block>Block level button</Button>
	<Button bsStyle="primary" bsSize="large" block>Block level button</Button>
      */
      }
      <SummaryTotals summaryInfo={props.summaryInfo}></SummaryTotals>
      <OrderFeedPortEventsGrid portEvents={props.portEvents}></OrderFeedPortEventsGrid>    
    </div>
  );  
}

export default SideBar;
