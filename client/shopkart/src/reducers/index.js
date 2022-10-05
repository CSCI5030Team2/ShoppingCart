import { combineReducers } from "redux";
import users from "./userReducer";
import products from "./productReducer";

export default combineReducers({
  userreducer: users,
  productReducer: products
});
