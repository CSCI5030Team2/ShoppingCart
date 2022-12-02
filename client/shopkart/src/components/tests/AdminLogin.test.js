import React from "react";
import { mount } from "enzyme";
import AdminLogin from "../AdminLogin";
import { Provider } from "react-redux";
import store from "../../../src/store";
import { BrowserRouter } from "react-router-dom";

const Adminlogin = {
  log:jest.fn(),
  error: console.error,
  warn: console.warn,
  info: console.info,
  debug: console.debug,
};
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <AdminLogin Adminlogin={Adminlogin} />
    </BrowserRouter>
  </Provider>
);

describe("Test Admin Login Component", () => {

  it("should render the component", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should have exactly two input fields", () => {
    expect(wrapper.find("input").length).toBe(2);
  });

  it("should have exactly one button", () => {
    expect(wrapper.find("button").length).toBe(1);
  });


  it("should have Enter Email-Id placeholder on Email input field", () => {
    expect(
      wrapper
        .find("input")
        .at(0)
        .props().placeholder
    ).toBe(" Enter Email");
  });


  it("should have Enter Password placeholder on password input field", () => {
    expect(
      wrapper
        .find("input")
        .at(1)
        .props().placeholder
    ).toBe(" Enter Password");
  });

});

