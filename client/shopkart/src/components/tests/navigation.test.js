import React from "react";
import { mount } from "enzyme";
import Navigation from "../Navigation";
import { Provider } from "react-redux";
import store from "../../store";

const navigation = jest.fn();
const wrapper = mount(
  <Provider store={store}>
    <Navigation navigation={navigation} />
  </Provider>
);

describe("Test Navigation Component", () => {
  it("should render the component", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should have exactly two a tags", () => {
    expect(wrapper.find("a").length).toBe(2);
  });

  it("should have exactly one h2 tag", () => {
    expect(wrapper.find("h2").length).toBe(1);
  });

  it("should have exactly one table", () => {
    expect(wrapper.find("table").length).toBe(1);
  });
});
