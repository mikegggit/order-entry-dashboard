import React from 'react';
const {Cell} = require('fixed-data-table-2')
import SortDir from './SortDir';
/*
 * Supports sorting. 
 *
 * props:
 * sortCol
 * sortDir
 * onSortChange
 */
class OrderFeedGridHeaderCell extends React.Component{
  constructor(props) {
    super(props);
    console.log("OrderFeedGridHeaderCell::ctor");
    console.log(props.sortDir);
    this._onSortChange = this._onSortChange.bind(this) 
  }

  reverseSortDirection(sortDir) {
    return sortDir === SortDir.DESC ? SortDir.ASC : SortDir.DESC;
  }

  _onSortChange(e) {
    console.log("OrderFeedGridHeaderCell::_onSortChange");
    e.preventDefault();
       
    const {onSortChange, columnKey, sortDir} = this.props; 
    
    onSortChange(
      columnKey, 
      this.props.sortDir ?
        this.reverseSortDirection(this.props.sortDir) :
        SortDir.ASC);
  }

  render() {
    //console.log("OrderFeedGridHeaderCell::render");
    var { children, sortDir, ...props } = this.props;
    return (<Cell className="sortable-header" {...props}>
      <a onClick={this._onSortChange}>{children} { sortDir ? (sortDir === SortDir.ASC) ? String.fromCharCode( "9650") : String.fromCharCode("9660") : '' }</a>
    </Cell>); 

  }
}

export default OrderFeedGridHeaderCell;
