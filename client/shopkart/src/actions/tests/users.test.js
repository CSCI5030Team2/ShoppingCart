import * as action from "../users";
import { GET_USERS } from "../types";
import moxios from "moxios";
import configureMockStore from "redux-mock-store";
import thunk from "redux-thunk";

const middlewares = [thunk];
const mockStore = configureMockStore(middlewares);

describe("Testing contacts actions", () => {
  beforeEach(() => {
    moxios.install();
  });
  afterEach(() => {
    moxios.uninstall();
  });

  it("should create an action with type GET_USERS and the payload sgould be same as the API response when the response is 20*", () => {
    const responseofAPI = [{}, {}, {}];
    moxios.stubRequest("http://localhost:7000/api/users/users", {
      status: 200,
      response: { data: responseofAPI }
    });

    const store = mockStore({});
    const expectedActions = [
      {
        type: GET_USERS,
        payload: responseofAPI
      }
    ];
    return store.dispatch(action.getUsers()).then(() => {
      expect(store.getActions()).toEqual(expectedActions);
    });
  });

  it("should go into catch with type GET_USERS and the payload should be same as the API response when the response is 40*", () => {
    const responseofAPI = [{}, {}, {}];
    moxios.stubRequest("http://localhost:7000/api/users/users", {
      status: 400,
      response: { data: responseofAPI }
    });

    const store = mockStore({});
    const expectedActions = [];
    return store.dispatch(action.getUsers()).then(() => {
      expect(store.getActions()).toEqual(expectedActions);
    });
  });
});
