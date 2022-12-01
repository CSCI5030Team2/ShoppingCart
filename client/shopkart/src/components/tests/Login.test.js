import React from "react";
import { mount } from "enzyme";
import Login from "../Login";
import { Provider } from "react-redux";
import store from "../../../src/store";
import { BrowserRouter } from "react-router-dom";

const login = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <Login login={login} />
    </BrowserRouter>
  </Provider>
);

describe("Test Login Component", () => {
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

