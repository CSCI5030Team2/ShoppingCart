import React from "react";
import { mount } from "enzyme";
import AdminNavigationAfterLogin from "../AdminNavigationAfterLogin";
import { Provider } from "react-redux";
import store from "../../store";
import { BrowserRouter } from "react-router-dom";

const adminNavigationafterlogin = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <AdminNavigationAfterLogin adminNavigationafterlogin={adminNavigationafterlogin} />
    </BrowserRouter>
  </Provider>
);

describe("Test NavigationAfterLogin Component", () => {
  it("should render the component", () => {
    expect(wrapper).toMatchSnapshot();
  });

  it("should have exactly one h2 tag", () => {
    expect(wrapper.find("h2").length).toBe(1);
  });

  it("should have exactly four div tags", () => {
    expect(wrapper.find("div").length).toBe(4);
  });
});
