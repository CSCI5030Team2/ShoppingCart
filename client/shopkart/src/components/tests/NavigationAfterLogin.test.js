import React from "react";
import { mount } from "enzyme";
import NavigationAfterLogin from "../NavigationAfterLogin";
import { Provider } from "react-redux";
import store from "../../store";
import { BrowserRouter } from "react-router-dom";

const Navigationafterlogin = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <NavigationAfterLogin Navigationafterlogin={Navigationafterlogin} />
    </BrowserRouter>
  </Provider>
);

describe("Test NavigationAfterLogin Component", () => {
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

  it("should have exactly one h2 tag", () => {
    expect(wrapper.find("h2").length).toBe(1);
  });

  it("should have exactly four div tags", () => {
    expect(wrapper.find("div").length).toBe(6);
  });
});
