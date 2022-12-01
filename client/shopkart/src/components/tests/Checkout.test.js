import Checkout from "../Checkout";
import React from "react";
import { mount } from "enzyme";
import { Provider } from "react-redux";
import store from "../../../src/store";
import { BrowserRouter } from "react-router-dom";


const checkOut = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <Checkout checkOut={checkOut} />
    </BrowserRouter>
  </Provider>
);

describe("Test Checkout Component", () => {

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

it("should have Enter Card Number placeholder on CardNumber input field", () => {
    expect(
      wrapper
        .find("input")
        .at(0)
        .props().placeholder
    ).toBe("Enter Card Number");
  });

  it("should have Month MM placeholder on ExpiryMonth input field", () => {
    expect(
      wrapper
        .find("input")
        .at(1)
        .props().placeholder
    ).toBe("Month MM");
  });

  it("should have Year YYYY placeholder on ExpireYear input field", () => {
    expect(
      wrapper
        .find("input")
        .at(2)
        .props().placeholder
    ).toBe("Year YYYY");
  });


  it("should have Enter CVV placeholder on CVV input field", () => {
    expect(
      wrapper
        .find("input")
        .at(3)
        .props().placeholder
    ).toBe("Enter CVV");
  });

  it("should have Enter Card Holder Name placeholder on CardHolderName input field", () => {
    expect(
      wrapper
        .find("input")
        .at(4)
        .props().placeholder
    ).toBe("Enter Card Holder Name");
  });
});