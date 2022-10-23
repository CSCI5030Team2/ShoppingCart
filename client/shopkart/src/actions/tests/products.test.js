import * as action from "../products";
import { GET_PRODUCTS } from "../types";
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

  it("should create an action with type GET_PRODUCTS and the payload should be same as the API response when the response is 20*", () => {
    const responseofAPI = [{}, {}, {}];
    moxios.stubRequest("http://localhost:8080/item", {
      status: 200,
      response: { data: responseofAPI }
    });

    const store = mockStore({});
    const expectedActions = [
      {
        type: GET_PRODUCTS,
        payload: responseofAPI
      }
    ];
    return store.dispatch(action.getProducts()).then(() => {
      expect(store.getActions()).toEqual(expectedActions);
    });
  });

  it("should go into catch with type GET_PRODUCTS and the payload should be same as the API response when the response is 40*", () => {
    const responseofAPI = [{}, {}, {}];
    moxios.stubRequest("http://localhost:8080/items", {
      status: 400,
      response: { data: responseofAPI }
    });

    const store = mockStore({});
    const expectedActions = [];
    return store.dispatch(action.getProducts()).then(() => {
      expect(store.getActions()).toEqual(expectedActions);
    });
  });
});
