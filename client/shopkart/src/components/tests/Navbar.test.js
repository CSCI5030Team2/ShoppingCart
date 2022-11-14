import React from "react";
import { mount } from "enzyme";
import Navbar from "../Navbar";
import { Provider } from "react-redux";
import store from "../../store";
import { BrowserRouter } from "react-router-dom";

const navbar = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <Navbar navbar={navbar} />
    </BrowserRouter>
  </Provider>
);

describe("Test Navigation Component", () => {
  it("should render the component", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should have exactly two div tags", () => {
    expect(wrapper.find("div").length).toBe(2);
  });

  it("should have exactly three Link tags", () => {
    expect(wrapper.find("Link").length).toBe(3);
  });

  it("should have exactly three li tags", () => {
    expect(wrapper.find("li").length).toBe(3);
  });
});
