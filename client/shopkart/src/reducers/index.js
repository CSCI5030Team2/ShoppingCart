import { combineReducers } from "redux";
import users from "./userReducer";
import products from "./productReducer";
import ads from "./adsReducer"
import admin from "./AdminReducer"

export default combineReducers({
  userReducer: users,
  productReducer: products,
  adsReducer:ads,
  adminReducer:admin
});
