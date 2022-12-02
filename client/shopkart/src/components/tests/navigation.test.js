import React from "react";
import { mount } from "enzyme";
import Navigation from "../Navigation";
import { Provider } from "react-redux";
import store from "../../store";
import { BrowserRouter } from "react-router-dom";

const navigation =  {
  log:jest.fn(),
  error: console.error,
  warn: console.warn,
  info: console.info,
  debug: console.debug,
};
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <Navigation navigation={navigation} />
    </BrowserRouter>
  </Provider>
);

describe("Test Navigation Component", () => {
  beforeAll(() => {
    jest.spyOn(console, 'log').mockImplementation(() => {});
  });
afterAll(() => { 
    console.log.mockRestore();
  });
afterEach(() => {
    console.log.mockClear();
  });
  it("should render the component", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should have exactly two b tags", () => {
    expect(wrapper.find("b").length).toBe(2);
  });

  it("should have exactly one h2 tag", () => {
    expect(wrapper.find("h2").length).toBe(1);
  });

  it("should have exactly four div tags", () => {
    expect(wrapper.find("div").length).toBe(7);
  });
});
