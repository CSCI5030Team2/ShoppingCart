import users from "../userReducer";
import { GET_USERS } from "../../actions/types";

describe("Testing Users Reducers", () => {
  it("should return a state object with contacts array equal to the payload in the action when the action type is GET_USERS (when the returned state is initial state", () => {
    const action = {
      type: GET_USERS,
      payload: [{}, {}, {}]
    };
    const returnedState = users(undefined, action);
    expect(returnedState).toEqual({ users: action.payload });
  });

  it("should return a state object with USERS array equal to the payload in the action when the action type is GET_USERS (when the returned state is not an initial state", () => {
    const initialState = {
      users: [1, 2, 3, 4, 5]
    };
    const action = {
      type: GET_USERS,
      payload: [{}, {}, {}]
    };
    const returnedState = users(initialState, action);
    expect(returnedState).toEqual({ users: action.payload });
  });

  it("should return the initial state object when the action type is not mentioned or doesn't concern the reducer (when the returned state is initial state", () => {
    let action = {
      payload: [{}, {}, {}]
    };
    let returnedState = users(undefined, action);
    expect(returnedState).toEqual({ users: [] });
    action = {
      type: "SOME_TYPE",
      payload: [{}, {}, {}]
    };
    returnedState = users(undefined, action);
    expect(returnedState).toEqual({ users: [] });
  });

  it("should return a state object with contacts array equal to the payload in the action when the action type is GET_USERS (when the returned state is not an initial state", () => {
    const initialState = {
      users: [1, 2, 3, 4, 5]
    };
    let action = {
      payload: [{}, {}, {}]
    };
    let returnedState = users(initialState, action);
    expect(returnedState).toEqual({ users: initialState.users });
    action = {
      type: "SOME_TYPE",
      payload: [{}, {}, {}]
    };
    returnedState = users(initialState, action);
    expect(returnedState).toEqual({ users: initialState.users });
  });
});
