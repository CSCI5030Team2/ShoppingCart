import {
  GET_USERS,
  CREATE_USERS,
  RESET_PASSWORD,
  UPDATE_USERS,
  DELETE_USERS,
<<<<<<< HEAD
  EMAIL_VERIFICATION,
  OTP_VERIFICATION,
  LOGIN,
  PUT_CHECKOUT
=======
  LOGIN
>>>>>>> origin/bohan
} from "../actions/types";

const initialstate = {
  users: []
};

export default function(state = initialstate, action) {
  switch (action.type) {
    case GET_USERS:
      return { ...state, users: action.payload };
    case CREATE_USERS:
      return state;
    case RESET_PASSWORD:
      return state;
    case UPDATE_USERS:
      return state;
    case DELETE_USERS:
      return state;
    case LOGIN:
      return state;
    default:
      return state;
  }
}
