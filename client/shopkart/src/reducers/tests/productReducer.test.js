import products from "../productReducer";
import { GET_PRODUCTS } from "../../actions/types";

describe("Testing Product Reducers", () => {
  it("should return a state object with contacts array equal to the payload in the action when the action type is GET_PRODUCTS (when the returned state is initial state", () => {
    const action = {
      type: GET_PRODUCTS,
      payload: [{}, {}, {}]
    };
    const returnedState = products(undefined, action);
    expect(returnedState).toEqual({ products: action.payload });
  });

  it("should return a state object with PRODUCTS array equal to the payload in the action when the action type is GET_Products (when the returned state is not an initial state", () => {
    const initialState = {
      products: [1, 2, 3, 4, 5]
    };
    const action = {
      type: GET_PRODUCTS,
      payload: [{}, {}, {}]
    };
    const returnedState = products(initialState, action);
    expect(returnedState).toEqual({ products: action.payload });
  });

  it("should return the initial state object when the action type is not mentioned or doesn't concern the reducer (when the returned state is initial state", () => {
    let action = {
      payload: [{}, {}, {}]
    };
    let returnedState = products(undefined, action);
    expect(returnedState).toEqual({ products: [] });
    action = {
      type: "SOME_TYPE",
      payload: [{}, {}, {}]
    };
    returnedState = products(undefined, action);
    expect(returnedState).toEqual({ products: [] });
  });

  it("should return a state object with products array equal to the payload in the action when the action type is GET_PRODUCTS (when the returned state is not an initial state", () => {
    const initialState = {
      products: [1, 2, 3, 4, 5]
    };
    let action = {
      payload: [{}, {}, {}]
    };
    let returnedState = products(initialState, action);
    expect(returnedState).toEqual({ products: initialState.products });
    action = {
      type: "SOME_TYPE",
      payload: [{}, {}, {}]
    };
    returnedState = products(initialState, action);
    expect(returnedState).toEqual({ products: initialState.products });
  });
});
