"use strict"

import Immutable from 'immutable';
const { Map } = require('immutable')
const moment = require('moment');
import Firm from './Firm';
import FirmDetail from './FirmDetail';
class FirmApi {


  constructor() {
    console.log("FirmAPI");
    // create a List of Maps
    this.ports= Immutable.fromJS([
      { id: 1, name: "ABC2", port: "CRABC1", ring: "FOO" },
      { id: 2, name: "ABC2", port: "CRABC2", ring: "FOO" },
      { id: 3, name: "ABC2", port: "CRABC3", ring: "FOO" },
      { id: 4, name: "ABC2", port: "CRABC4", ring: "FOO" },

      { id: 5, name: "POP1", port: "CRPOP1", ring: "FOO" },
      { id: 6, name: "POP1", port: "CRPOP2", ring: "FOO" },

      { id: 7, name: "DEF1", port: "CRDEF1", ring: "FOO" },
      { id: 8, name: "DEF1", port: "CRDEF2", ring: "FOO" },
      { id: 9, name: "DEF2", port: "CRDEF1", ring: "FOO" },
      { id: 10, name: "DEF2", port: "CRDEF2", ring: "FOO" },
      { id: 11, name: "XYZ7", port: "CRXYZ1", ring: "FOO" },
      { id: 12, name: "XYZ7", port: "CRXYZ2", ring: "FOO" },
      { id: 13, name: "XYZ7", port: "CRXYZ3", ring: "FOO" },
      { id: 14, name: "XYZ7", port: "CRXYZ4", ring: "FOO" },
      { id: 15, name: "XYZ7", port: "CRXYZ5", ring: "FOO" },
      { id: 16, name: "XYZ7", port: "CRXYZ6", ring: "FOO" },
      
    ]);

  }

  randInt(low, high) {
    return Math.floor(Math.random() * high) + low;
  }

  firmEvent() {
    let randIdx = Math.floor(Math.random() * (this.ports.count()));
    let port = this.ports.get(randIdx);

    let evt = {
      last: moment().format('hh:mm:ss.SSS'), // last
      firm: port.get("name"),
      port: port.get("port"),
      ring: port.get("ring"),
      numBlocks:this.randInt(0, 10),             // blocks
      rateCurrent:.08,
      rate1Min:.11,
      rate5Min:.432,
      qlCurrent:23,
      ql1Min:44,
      limitCurrent:442,
      limit1Min:24342,
      limit5Min:22,
      quotes:this.randInt(0, 10),
      blocks:this.randInt(0, 10),
      purges:this.randInt(0, 10),
      undPurges:this.randInt(0, 10)};
      
    return evt;
  }
  start() {
       
  }

  stop() {

  }
  
  spinPortFirms() {
    return this.ports;
  }
}

export default new FirmApi();
