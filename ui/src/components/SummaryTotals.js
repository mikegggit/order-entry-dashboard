"use strict"

import React from 'react'
import TextCell from './TextCell'
import SortDir from './SortDir';
const {Table} = require('react-bootstrap');

class SummaryTotals extends React.Component {


  constructor(props) {
    super(props);
    console.log("SummaryTotals::ctor");

  }

  componentDidMount() {
    console.log("SummaryTotals::componentDidMount");
  }

  render(props) {
    //console.log("SummaryTotals::render");
    const {summaryInfo} = this.props;
    return (
      <Table>
        <tbody>
          <tr>
            <td colSpan="3">{summaryInfo.get("last")}</td>
          </tr>
          <tr>
            <td>Rate:</td>
            <td colSpan="2"></td>
          </tr>
          <tr>
            <td>Block Size:</td>
            <td colSpan="2"></td>
          </tr>
          <tr>
            <td>Quotes</td>
            <td colSpan="2">{summaryInfo.get("totQuotes")}</td>
          </tr>
          <tr>
            <td>Blocks</td>
            <td colSpan="2">{summaryInfo.get("totBlocks")}</td>
          </tr>
          <tr>
            <td>Purges</td>
            <td colSpan="2">{summaryInfo.get("totPurges")}</td>
          </tr>
          <tr>
            <td>Und Purges</td>
            <td colSpan="2">{summaryInfo.get("totUndPurges")}</td>
          </tr>
          <tr>
            <td>Rejects</td>
            <td colSpan="2"></td>
          </tr>
        </tbody>
      </Table>
    );

  }
}

export default SummaryTotals;

