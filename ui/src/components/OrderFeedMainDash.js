"use strict"

import React from 'react';
import TestLineChartWrapper from './TestLineChartWrapper';
import OrderFeedGrid from './OrderFeedGrid';
import OrderFeedPortGrid from './OrderFeedPortGrid';
import { Modal, Button } from 'react-bootstrap'; 
class OrderFeedMainDash extends React.Component {
  
  constructor(props) {
    super(props);
    this.closePorts= this.closePorts.bind(this);
  }

  closePorts() {
    console.log("OrderFeedMainDash::onClose");
    this.props.actions.onClosePorts();
  }

  render() {
    
//    let close = () => this.props.actions.onClosePorts();
    console.error(this.props.selectedGroup != '');
    return  (
      <div id="foo">
	<OrderFeedGrid width={800} {...this.props}></OrderFeedGrid>
        <TestLineChartWrapper latencyStream={this.props.latencyStream}/>
	<Modal
	  show={this.props.selectedGroup != ''}
	  onHide={this.closePorts}
	  aria-labelledby="contained-modal-title" 
          animation={false}
          dialogClassName="port-modal"
	>
	  <Modal.Header closeButton>
	    <Modal.Title id="contained-modal-title">Port Group: {this.props.selectedGroup}</Modal.Title>
	  </Modal.Header>
	  <Modal.Body>
	    <OrderFeedPortGrid ports={this.props.ports} {...this.props}></OrderFeedPortGrid>
	  </Modal.Body>
	  <Modal.Footer>
	    <Button onClick={this.closePorts}>Close</Button>
	  </Modal.Footer>
	</Modal>
      </div>
    );
  }
}

export default OrderFeedMainDash;
