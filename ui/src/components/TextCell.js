"use strict"

import React from 'react'
import { Cell } from 'fixed-data-table-2'
class TextCell extends React.PureComponent {
  render() {
    console.log("TextCell");
    const {data, rowIndex, field, ...props} = this.props;
    return (
      <Cell {...props}>
        {data[rowIndex][field]}
      </Cell>
    );
  }
}

export default TextCell;
