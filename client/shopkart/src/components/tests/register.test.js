import React from "react";
import { mount } from "enzyme";
import Register from "../register";
import { Provider } from "react-redux";
import store from "../../../src/store";
import { BrowserRouter } from "react-router-dom";

const register = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <Register register={register} />
    </BrowserRouter>
  </Provider>
);

describe("Test Register Component", () => {
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

  it("should have exactly five input fields", () => {
    expect(wrapper.find("input").length).toBe(5);
  });

  it("should have one form component", () => {
    expect(wrapper.find("form").length).toBe(1);
  });

  it("should have exactly one button", () => {
    expect(wrapper.find("button").length).toBe(1);
  });

  it("should have Enter First Name placeholder on Name input field", () => {
    expect(
      wrapper
        .find("input")
        .at(0)
        .props().placeholder
    ).toBe("Enter First Name");
  });

  it("should have Enter Last Name placeholder on Name input field", () => {
    expect(
      wrapper
        .find("input")
        .at(1)
        .props().placeholder
    ).toBe("Enter Last Name");
  });

  it("should have Enter Email-Id placeholder on Email input field", () => {
    expect(
      wrapper
        .find("input")
        .at(2)
        .props().placeholder
    ).toBe("Enter Email-Id");
  });


  it("should have Enter Password placeholder on password input field", () => {
    expect(
      wrapper
        .find("input")
        .at(3)
        .props().placeholder
    ).toBe("Enter Password");
  });

  it("should have Confirm Password placeholder on password input field", () => {
    expect(
      wrapper
        .find("input")
        .at(4)
        .props().placeholder
    ).toBe("Re-Enter Password");
  });
});

