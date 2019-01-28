import ActionTypes from './ActionTypes';
import AppDispatcher from '../AppDispatcher'

const Actions = {
  initialize() {
    console.log("Actions::initialize");
    AppDispatcher.dispatch({
      type: ActionTypes.INITIALIZE,
    });
  },
  firmEvent(firm) {
//    console.log("Actions::firmEvent");
    AppDispatcher.dispatch({
      type: ActionTypes.ON_PORT_UPDATE,
      firm,
    });
  },
  sortGroups(sorted, sortCol, sortDir) {
    console.log("Actions::sortGroups [sortCol=%s, sortDir=%s]", sortCol, sortDir);
    AppDispatcher.dispatch({
      type:    ActionTypes.SORT_GROUPS,
      rows:    sorted,
      sortCol: sortCol,
      sortDir: sortDir,
    });
  },
  sortPorts(sortCol, sortDir) {
    console.log("Actions::sortPorts [sortCol=%s, sortDir=%s]", sortCol, sortDir);
    AppDispatcher.dispatch({
      type:    ActionTypes.SORT_PORTS,
      sortCol: sortCol,
      sortDir: sortDir,
    });
  },
  clickGroupRow(rowIndex) {
    console.log("Actions::clickGroupRow [rowIndex=%s], rowIndex");
    AppDispatcher.dispatch({
      type: ActionTypes.ON_SELECT_GROUP,
      rowIndex:  rowIndex,
    });
  },
  onClosePorts() {
    console.log("Actions::onClosePorts");
    AppDispatcher.dispatch({
      type: ActionTypes.CLOSE_PORTS,
    });
  },
  onWSMessage(msg) {
    console.log("Actions::onWSMessage");
    console.log(msg.body);
    AppDispatcher.dispatch({
      type: ActionTypes.ON_WS_MESSAGE,
      msg:  JSON.parse(msg.body),
    });
  }
};

export default Actions;
