import { combineReducers } from "redux";
import users from "./userReducer";
import products from "./productReducer";
import ads from "./adsReducer"

export default combineReducers({
  userReducer: users,
  productReducer: products,
  adsReducer:ads
});
