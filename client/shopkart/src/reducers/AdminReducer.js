import {
    GET_ADMIN,
    LOGIN
  } from "../actions/types";
  
  const initialstate = {
    admin: []
  };
  
  export default function(state = initialstate, action) {
    switch (action.type) {
      case GET_ADMIN:
        return { ...state, admin: action.payload };
      case LOGIN:
        return state;
      default:
        return state;
    }
  }
  