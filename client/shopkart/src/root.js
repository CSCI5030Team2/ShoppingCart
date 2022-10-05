import React from "react";
import store from "./store";
import { Provider } from "react-redux";

// eslint-disable-next-line import/no-anonymous-default-export
export default props => {
  return <Provider store={store}> {props.children}</Provider>;
};
