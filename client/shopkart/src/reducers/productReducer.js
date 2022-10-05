import {
    GET_PRODUCTS,
    CREATE_PRODUCTS,
    UPDATE_PRODUCTS,
    DELETE_PRODUCTS
  } from "../actions/types";
  
  const initialstate = {
    products: [],
    product: ""
  };
  
  // eslint-disable-next-line import/no-anonymous-default-export
  export default function(state = initialstate, action) {
    switch (action.type) {
      case GET_PRODUCTS:
        return { ...state, products: action.payload };
      case CREATE_PRODUCTS:
        return state;
      case UPDATE_PRODUCTS:
        return state;
      case DELETE_PRODUCTS:
        return state;
      default:
        return state;
    }
  }
  