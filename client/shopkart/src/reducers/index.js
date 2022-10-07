import { combineReducers } from "redux";
import users from "./userReducer";
import products from "./productReducer";

export default combineReducers({
  userReducer: users,
  productReducer: products
});
