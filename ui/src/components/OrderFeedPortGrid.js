"use strict"

import React from 'react'
const {Table, Column, Cell} = require('fixed-data-table-2')
import FirmStore from '../stores/FirmStore'
import TextCell from './TextCell'
import StockMonitorCell from './StockMonitorCell'
import OrderFeedGridHeaderCell from './OrderFeedGridHeaderCell'
import SortDir from './SortDir';

class OrderFeedPortGrid extends React.Component {
  constructor(props) {
    super(props);
    this.changeStyle = {
      background: 'red'
    };
    this._onSortChange = this._onSortChange.bind(this);
    this._rowClassNameGetter = this._rowClassNameGetter.bind(this);
  }

  componentDidMount() {
    console.log("OrderFeedPortGrid::componentDidMount");
  }

  _onSortChange(colKey, colDir) {
    console.log("OrderFeedPortGrid::onSort [colKey=%s, colDir=%s]", colKey, colDir);
    
    this.props.actions.sortPorts(colKey, colDir);
  }

  _rowClassNameGetter(rowindex) {
    return 'myrow';
  }

  render() {
    console.log("OrderFeedPortGrid::render");
    if (this.props.ports== null) {
      return null;
    }
    if (this.props.ports.size === 0) {
      return null;
    }
    let rows = this.props.ports.valueSeq();
    return (
      <Table
        rowsCount={rows.size}
        rowHeight={30}
        width={1330}
        height={500}
        allowCellsRecycling={true}
        rowClassNameGetter={this._rowClassNameGetter}
        headerHeight={30} >
        <Column
          columnKey="last"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("last")}>Last</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="last" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} />
        <Column
          columnKey="name"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("name")}>Port</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="name" width={75} {...this.props}></StockMonitorCell>)
          }
          width={100} />
        <Column
          columnKey="ringName"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("ringName")}>Ring</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="ringName" width={75} {...this.props}></StockMonitorCell>)
          }
          width={100} />
        <Column columnKey="blocks"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("blocks")}>Blk</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="blocks" width={100} {...this.props}></StockMonitorCell>)
          }
          width={75} /> 
        <Column columnKey="rateCurrent"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("rateCurrent")}>Curr R</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="rateCurrent" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="rate1Min"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("rate1Min")}>1min R</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="rate1Min" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="rate5Min"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("rate5Min")}>5min R</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="rate5Min" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="qlCurrent"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("qlCurrent")}>Curr QL</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="qlCurrent" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="ql1Min"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("ql1Min")}>1min QL</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="ql1Min" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="limitCurrent"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("limitCurrent")}>Curr L</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="limitCurrent" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="limit1Min"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("limit1Min")}>1min L</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="limit1Min" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="limit5Min"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("limit5Min")}>5min L</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="limit5Min" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="numQuotes"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("numQuotes")}>Quotes</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="numQuotes" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="numBlocks"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("numBlocks")}>Blocks</OrderFeedGridHeaderCell >}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="numBlocks" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="numPurges"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("numPurges")}>Purges</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="numPurges" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
        <Column columnKey="numUndPurges"
          header={<OrderFeedGridHeaderCell onSortChange={this._onSortChange} sortDir={this.props.portSortInfo.get("numUndPurges")}>UndPurges</OrderFeedGridHeaderCell>}
          cell={
            ({rowIndex}) => (<StockMonitorCell rowIndex={rowIndex} rows={rows} field="numUndPurges" width={100} {...this.props}></StockMonitorCell>)
          }
          width={100} /> 
      </Table>
    );
  }
}

export default OrderFeedPortGrid;
