import React from "react";
import { mount } from "enzyme";
import AdminNavbar from "../AdminNavbar";
import { Provider } from "react-redux";
import store from "../../store";
import { BrowserRouter } from "react-router-dom";

const adminnavbar = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <AdminNavbar adminnavbar={adminnavbar} />
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

  it("should have exactly one div tag", () => {
    expect(wrapper.find("div").length).toBe(1);
  });

  it("should have exactly four Link tags", () => {
    expect(wrapper.find("Link").length).toBe(2);
  });

  it("should have exactly four li tags", () => {
    expect(wrapper.find("li").length).toBe(3);
  });
});